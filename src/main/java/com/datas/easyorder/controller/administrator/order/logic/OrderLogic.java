package com.datas.easyorder.controller.administrator.order.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import com.datas.easyorder.controller.BaseLogic;
import com.datas.easyorder.controller.administrator.order.OrderPdfView;
import com.datas.easyorder.db.dao.CouponOrderRepository;
import com.datas.easyorder.db.dao.CustomerRepository;
import com.datas.easyorder.db.dao.InvoiceItemRepository;
import com.datas.easyorder.db.dao.InvoiceRepository;
import com.datas.easyorder.db.dao.OrderItemRepository;
import com.datas.easyorder.db.dao.OrderRepository;
import com.datas.easyorder.db.dao.OrderSpecifications;
import com.datas.easyorder.db.dao.ProductRepository;
import com.datas.easyorder.db.dao.UserCompanyRepository;
import com.datas.easyorder.db.entity.Coupon;
import com.datas.easyorder.db.entity.CouponOrder;
import com.datas.easyorder.db.entity.Customer;
import com.datas.easyorder.db.entity.Invoice;
import com.datas.easyorder.db.entity.InvoiceItem;
import com.datas.easyorder.db.entity.Order;
import com.datas.easyorder.db.entity.OrderItem;
import com.datas.easyorder.db.entity.Product;
import com.datas.easyorder.db.entity.User;
import com.datas.easyorder.db.entity.UserCompany;
import com.datas.utils.SearchForm;
import com.plugin.mail.logic.SendMail;
import com.plugin.pdf.HtmlPDF;
import com.plugin.utils.DateHelper;

@Component
public class OrderLogic extends BaseLogic<Order> {

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	InvoiceRepository invoiceRepository;
	@Autowired
	InvoiceItemRepository invoiceItemRepository;
	@Autowired
	UserCompanyRepository userCompanyRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	OrderItemRepository orderItemRepository;
	@Autowired
	TemplateEngine templateEngine;
	@Autowired
	HtmlPDF htmlPDF;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	SendMail sendMail;
	@Autowired
	CouponOrderRepository couponOrderRepository;
	
	private String orderPrintPdfTempatePath = "administrator/order/pdf/print";

	@Value("${gst}")
	private Double gstPercentage;
	@Value("${invoice.pre}")
	private String invoicePre;

	/**
	 * 
	 * @param searchForm
	 * @param pageable
	 * @return
	 */
	public Page<Order> getOrderList(SearchForm searchForm, Pageable pageable) {
		return orderRepository.findAll(OrderSpecifications.getSearchSpecification(searchForm), pageable);
	}

	/**
	 * web
	 * 
	 * @param customer
	 * @param pageable
	 * @return
	 */
	public Page<Order> getOrderList(Customer customer, Pageable pageable) {
		return orderRepository.findByCustomerByCustomerIdIdOrCustomerBySalesIdId(customer.getId(),customer.getId(), pageable);
	}

	/**
	 * 更新属性
	 * 
	 * @param cateoryId
	 * @param atrributeName
	 * @param attributeValue
	 */
	@Transactional(rollbackOn = Exception.class)
	public void update(Long id, String atrributeName, String attributeValue, User user) {

		Order order = orderRepository.findOne(id);
		try {
			Field field = Order.class.getDeclaredField(atrributeName);
			field.setAccessible(true);
			if (field.getType() == String.class) {
				field.set(order, attributeValue);
			} else if (field.getType().equals(Double.class) || field.getType().equals(double.class)) {
				field.set(order, Double.valueOf(attributeValue));
			} else if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
				field.set(order, Integer.valueOf(attributeValue));
			} else if (field.getType().equals(Byte.class) || field.getType().equals(byte.class)) {
				field.set(order, Byte.valueOf(attributeValue));
			} else if (field.getType().equals(Long.class) || field.getType().equals(long.class)) {
				field.set(order, Byte.valueOf(attributeValue));
			} else if (field.getType().equals(Customer.class)) {
				if ("".equals(attributeValue)) {
					field.set(order, null);
				} else {
					Customer customer = new Customer();
					customer.setId(Long.valueOf(attributeValue));
					field.set(order, customer);
				}
			}
			order.setModifyTime(Calendar.getInstance().getTime());
			order.setUser(user);
			orderRepository.save(order);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param orderId
	 * @return
	 */
	public Order getOrderById(Long orderId) {
		return orderRepository.findOne(orderId);
	}

	@Override
	public CrudRepository<Order, Long> getRepository() {
		return orderRepository;
	}

	/**
	 * order 转成Invoice
	 * 
	 * @param orderId
	 */
	@Transactional(rollbackOn = Exception.class)
	public Invoice transferOrderToInvoice(Long orderId, User user) {

		Order order = orderRepository.findOne(orderId);

		Customer customer = order.getCustomerByCustomerId();
		// 当inovice没有生成的时候才会重新生成,并更新用户balance，先减去旧balance然后再加上新的
		Invoice invoiceFromDb = invoiceRepository.findOneByAliasId(invoicePre + order.getId());
		Double balance = customer.getBalance() == null ? 0D :customer.getBalance();
		if (invoiceFromDb != null) {
			
			balance = balance - invoiceFromDb.getTotal();
			invoiceItemRepository.delete(invoiceFromDb.getInvoiceItems());
			invoiceRepository.delete(invoiceFromDb);
		}

		Invoice invoice = new Invoice();

		invoice.setCustomerCompanyName(order.getToCustomerCompanyName());
		invoice.setCustomerId(order.getCustomerByCustomerId().getId());
		invoice.setCustomerCompanyAddress(order.getToShippingAddress());
		invoice.setCustomerCompanyPhone(order.getToTel());
		invoice.setCustomerCompanyMobile(order.getToPhone());
		invoice.setCustomerEmail(order.getToEmail());
		invoice.setCustomerCompanyAddress(order.getToShippingAddress());

		UserCompany userCompany = userCompanyRepository.findOne(user.getUserCompany().getId());

		invoice.setUserCompany(userCompany);
		invoice.setUserCompanyFax(userCompany.getFax());
		invoice.setUserCompanyAddress(userCompany.getAddress());
		invoice.setUserCompanyBankAccount(userCompany.getBankAccount());
		invoice.setUserCompanyEmail(userCompany.getEmail());
		invoice.setUserCompanyFax(userCompany.getFax());
		invoice.setUserCompanyGstNo(userCompany.getGstNo());
		invoice.setUserCompanyMobile(userCompany.getMobile());
		invoice.setUserCompanyName(userCompany.getName());
		invoice.setUserCompanyTel(userCompany.getTel());
		invoice.setUserCompanyWebsite(userCompany.getWebsite());

		invoice.setCreateTime(Calendar.getInstance().getTime());
		invoice.setModifyTime(Calendar.getInstance().getTime());
		invoice.setInvoiceTermsConditions(userCompany.getInvoiceTermsConditions());
		invoice.setUser(user);
		invoice.setFreight(order.getTotalFreight());
		
		Double totalPrice = order.getOrderItems().stream().map(doi -> doi.getNum()*doi.getProductPrice()).reduce(0D, (a,b) -> a+b).doubleValue()*customer.getDiscount();
//		invoice.setTotal(totalPrice + order.getTotalFreight() + invoice.getGst());
		invoice.setTotal(totalPrice + order.getTotalFreight());
		if(order.getIsPaid().equals(OrderRepository.ispaid_yes)){
			invoice.setPaid(InvoiceRepository.paid_paid);
		}else{
			invoice.setPaid(InvoiceRepository.paid_unpaid);
		}
		
		invoice.setGst((order.getTotalProductPrice() + order.getTotalFreight()) * gstPercentage);

		invoice.setPaid(InvoiceRepository.paid_unpaid);
		invoice.setStatus(InvoiceRepository.status_new);
		invoice.setOrder(order);

		invoice.setBalance(invoice.getTotal());
		invoice.setAliasId(invoicePre + order.getId());
		invoice.setAdminInfo(order.getAdminMsg());
		invoiceRepository.save(invoice);
		//balance
		balance = balance + invoice.getTotal();
		customer.setBalance(balance);
		customer.setModifyTime(Calendar.getInstance().getTime());
		customerRepository.save(customer);

		List<InvoiceItem> invoiceItemList = new ArrayList<>();
		order.getOrderItems().stream().forEach(orderItem -> {
			InvoiceItem invoiceItem = new InvoiceItem();

			invoiceItem.setInvoice(invoice);
			invoiceItem.setQuantity(orderItem.getNum());
			invoiceItem.setTitle(orderItem.getProductNameCn());
			invoiceItem.setUnitPrice(orderItem.getProductPrice());
			invoiceItemList.add(invoiceItem);
		});
		invoiceItemRepository.save(invoiceItemList);

		byte invoiced = 1;
		order.setInvoiced(invoiced);
		order.setModifyTime(Calendar.getInstance().getTime());
		order.setUser(user);
		if(totalPrice!=order.getTotalProductPrice()){
			order.setTotalProductPrice(totalPrice);
		}
		orderRepository.save(order);

		return invoice;

	}

	/**
	 * Duplicate Order
	 * 
	 * @param orderId
	 * @param user
	 * @return Order
	 */
	@Transactional(rollbackOn = Exception.class)
	public Order duplicate(Long orderId, User user) {

		Order newOrder = this.duplicateCreateNewOrder(orderId);
		Order backOrder = this.duplicateCreateBackOrder(orderId);
		
		return newOrder.getId()!=null?newOrder:backOrder;
	}
	
	/**
	 * 拷贝生成新订单
	 * @return
	 */
	@Transactional(rollbackOn = Exception.class)
	private Order duplicateCreateNewOrder(Long orderId){
		Order order = orderRepository.findOne(orderId);
		Order duplicatedOrder = new Order();
		if (order != null) {
			BeanUtils.copyProperties(order, duplicatedOrder);
			duplicatedOrder.setCreateTime(Calendar.getInstance().getTime());
			duplicatedOrder.setModifyTime(Calendar.getInstance().getTime());
			duplicatedOrder.setIsPaid(OrderRepository.ispaid_no);
			duplicatedOrder.setId(null);
			duplicatedOrder.setOrderItems(null);
			duplicatedOrder.setInvoices(null);
			duplicatedOrder.setCouponOrders(null);
			duplicatedOrder.setCustomerPaymentHistories(null);
			duplicatedOrder.setOrderItems(null);
			duplicatedOrder.setInvoices(null);
			
			
			List<OrderItem> duplicateOrderItemList = new ArrayList<>();
			List<Product> productList = new ArrayList<>();

			order.getOrderItems().forEach(oi -> {
				OrderItem duplicateItem = new OrderItem();
				BeanUtils.copyProperties(oi, duplicateItem);
				
				if(oi.getProduct().getStock()-duplicateItem.getNum()>=0){
					duplicateItem.setId(null);
					duplicateOrderItemList.add(duplicateItem);
					oi.getProduct().setStock(oi.getProduct().getStock()-duplicateItem.getNum());
					productList.add(oi.getProduct());
				}
				
			});
			
			
			if(duplicateOrderItemList.size()>0){
				duplicatedOrder.setTotalProductPrice(duplicateOrderItemList.stream().map(doi -> doi.getNum()*doi.getProductPrice()).reduce(0D, (a,b) -> a+b).doubleValue());
				orderRepository.save(duplicatedOrder);
				duplicateOrderItemList.forEach(oi -> oi.setOrder(duplicatedOrder));
				orderItemRepository.save(duplicateOrderItemList);
				
				productRepository.save(productList);
			}
		}

		return duplicatedOrder;
	}
	
	

	/**
	 * 
	 * 生成backOrder
	 * @return Order
	 * 
	 */
	@Transactional(rollbackOn = Exception.class)
	private Order duplicateCreateBackOrder (Long orderId){
		Order order = orderRepository.findOne(orderId);
		Order duplicatedOrder = new Order();
		if (order != null) {
			BeanUtils.copyProperties(order, duplicatedOrder);
			duplicatedOrder.setCreateTime(Calendar.getInstance().getTime());
			duplicatedOrder.setModifyTime(Calendar.getInstance().getTime());
			duplicatedOrder.setStatus(OrderRepository.status_backorder);
			duplicatedOrder.setIsPaid(OrderRepository.ispaid_no);
			duplicatedOrder.setId(null);
			duplicatedOrder.setOrderItems(null);
			duplicatedOrder.setInvoices(null);
			duplicatedOrder.setCouponOrders(null);
			duplicatedOrder.setCustomerPaymentHistories(null);
			duplicatedOrder.setOrderItems(null);
			duplicatedOrder.setInvoices(null);
			
			
			List<OrderItem> duplicateOrderItemList = new ArrayList<>();
			List<Product> productList = new ArrayList<>();
			order.getOrderItems().forEach(oi -> {
				OrderItem duplicateItem = new OrderItem();
				BeanUtils.copyProperties(oi, duplicateItem);
				
				if(oi.getProduct().getStock()-duplicateItem.getNum()<0){
					duplicateItem.setId(null);
					duplicateItem.setNum(Math.abs(oi.getNum()));
					duplicateOrderItemList.add(duplicateItem);
					oi.getProduct().setStock(oi.getProduct().getStock()-duplicateItem.getNum());
					productList.add(oi.getProduct());
				}
			});
			
			if(duplicateOrderItemList.size()>0){
				duplicatedOrder.setTotalProductPrice(duplicateOrderItemList.stream().map(doi -> doi.getNum()*doi.getProductPrice()).reduce(0D, (a,b) -> a+b).doubleValue());
				orderRepository.save(duplicatedOrder);
				duplicateOrderItemList.forEach(oi -> oi.setOrder(duplicatedOrder));
				orderItemRepository.save(duplicateOrderItemList);
				productRepository.save(productList);
			}
			
		}

		return duplicatedOrder;
	}

	/**
	 * get sales {value: -2, text: '--Select--'},
	 * 
	 * @return JSONArray
	 */
	@Transactional(rollbackOn = Exception.class)
	public JSONArray getSales() {
		JSONArray array = new JSONArray();

		/*
		 * JSONObject select = new JSONObject(); select.put("value", "");
		 * select.put("text", "--select--"); array.put(select); JSONObject empty
		 * = new JSONObject(); empty.put("value", ""); empty.put("text",
		 * "Empty"); array.put(empty);
		 */
		customerRepository
				.findAllByCustomerTypeAndStatus(CustomerRepository.customerType_sales, CustomerRepository.status_active)
				.forEach(c -> {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("value", c.getId());
					jsonObject.put("text", c.getName());
					array.put(jsonObject);
				});
		return array;
	}

	/**
	 * 根据orderId和SalesId获取订单
	 * 
	 * @param orderId
	 * @param id
	 * @return Order
	 */
	public Order getOrderByIdAndSalesId(Long orderId, Long customerSalesId) {
		return orderRepository.findOneByIdAndCustomerBySalesIdId(orderId, customerSalesId);
	}
	
	
	/**
	 * 根据orderId和CustomerId获取订单
	 * @param orderId
	 * @param id
	 * @return Order
	 */
	public Order getOderByIdAndCustomerId(Long orderId,Long customerId) {
		return orderRepository.findOneByIdAndCustomerByCustomerIdId(orderId,customerId);
	}

	/**
	 * 打印订单
	 * 
	 * @param orderIds
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void pdfOutputStream(Long[] orderIds, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String html = templateEngine.process(orderPrintPdfTempatePath, this.createWebContext(orderIds, request, response));

		htmlPDF.pdfOutputStream(html.toString(), response.getOutputStream());

	}

	/**
	 * 创建webContext
	 * 
	 * @param invoiceId
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional(rollbackOn = Exception.class)
	private WebContext createWebContext(Long[] orderIds, HttpServletRequest request, HttpServletResponse response) {
		List<Order> orderList = orderRepository.findByIdIn(orderIds);

		List<OrderPdfView> list = new ArrayList<>();

		for (Order order : orderList) {
			OrderPdfView orderPdfView = new OrderPdfView();
			orderPdfView.setOrder(order);
			orderPdfView.setOrderItemList(
					order.getOrderItems().stream().sorted((it1, it2) -> it1.getId().intValue() - it2.getId().intValue())
							.collect(Collectors.toList()));
			orderPdfView.getOrderItemList().forEach(oi -> {
				oi.setProductDefaultSrc(request.getServletPath() + oi.getProductDefaultSrc());
			});
			list.add(orderPdfView);
		}

		WebContext webContext = new WebContext(request, response, request.getServletContext());
		webContext.setVariable("orderPdfViewList", list);
		return webContext;
	}

	/**
	 * 获取销售列表
	 * @return List<Customer>
	 */ 
	public List<Customer> geSalesList(){
		return customerRepository.findAllByCustomerTypeAndStatus(CustomerRepository.customerType_sales, CustomerRepository.status_active);
	}

	/**
	 * 批量打印数据
	 * @param orderIds
	 * @return List<OrderPdfView> 
	 */
	public List<OrderPdfView> batchPrint(Long[] orderIds){
		List<Order> orderList = orderRepository.findByIdIn(orderIds);

		List<OrderPdfView> list = new ArrayList<>();

		for (Order order : orderList) {
			OrderPdfView orderPdfView = new OrderPdfView();
			orderPdfView.setOrder(order);
			orderPdfView.setOrderItemList(
					order.getOrderItems().stream().sorted((it1, it2) -> it1.getId().intValue() - it2.getId().intValue())
							.collect(Collectors.toList()));
			
			list.add(orderPdfView);
		}

		return list;
	}

	/**
	 * 
	 * @param orderId
	 * @param user
	 */
	@Transactional(rollbackOn = Exception.class)
	public void emailOrder(Long orderId,User user) {
		
		Order order  = orderRepository.findOne(orderId);
		//发送邮件
		UserCompany userCompany = userCompanyRepository.findOne(user.getUserCompany().getId());
		sendMail.init(userCompany, order.getCustomerByCustomerId().getEmail());
		
		String content = "#Order:" + order.getId() + " has dispatched.<br/>" + order.getAdminMsg();
		try {
			 sendMail.send(userCompany.getSendingMailSubject() + " #Order: "+ order.getId(), content);
		} catch (Exception e) {
			System.err.println("order email faild " + order.getId());
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 根据orderID 更新产品库存
	 * @param id
	 */
	@Transactional(rollbackOn = Exception.class)
	public void updateProductStock(Long id) {
		List<OrderItem> list = orderItemRepository.findAllByOrderId(id);
		
		List<Product> pList = new ArrayList<>();
		list.forEach(oi ->{
			
			Product p = oi.getProduct();
			p.setStock(p.getStock()-oi.getNum());
			pList.add(p);
		});
		productRepository.save(pList);
	}
	
	/**
	 * 根据ID 获取点订单明细
	 * @param orderId
	 * @return
	 */
	public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
		return orderItemRepository.findAllByOrderId(orderId);
	}

	/**
	 * 根据订单ID获取coupon
	 * @param orderId
	 * @return Coupon
	 */
	@Transactional(rollbackOn = Exception.class)
	public Coupon getCouponByOrderId(Long orderId) {
		
		Coupon coupon = null;
		CouponOrder co = couponOrderRepository.findByOrderId(orderId);
		if(co!=null){
			coupon = co.getCoupon();
		}
		return coupon;
	}
	

	/**
	 * admin new order
	 * @param customerId
	 * @param products
	 */
	@Transactional(rollbackOn = Exception.class)
	public void saveOrder(Long customerId, String[] products,Double gst,String adminMsg) {
		UserCompany userCompany = userCompanyRepository.findOne(1L);
		
		Order order = new Order();
		
		
		Customer customer = customerRepository.findOne(customerId);
		
		List<OrderItem> orderItemList = new ArrayList<>();
		for(int i=0;i<products.length;i++){
			//productId:qty
			String[] pQty = products[i].split(":");
			Product product = productRepository.findOneByIdAndStatusNot(Long.valueOf(pQty[0]), ProductRepository.status_cancelled);
			if(product==null){
				continue;
			}
			
			OrderItem oi = new OrderItem();
			oi.setProduct(product);
			oi.setProductDefaultSrc(product.getDefaultSrc());
			oi.setProductNameCn(product.getProductName());
			oi.setProductNameEn(product.getProductNameAlias());
			oi.setProductPrice(Double.valueOf(pQty[2]));
			oi.setNum(Integer.valueOf(pQty[1]));
			orderItemList.add(oi);
			
			
			product.setSoldNum(product.getSoldNum()+Integer.valueOf(pQty[1]));
			product.setStock(product.getStock()-Integer.valueOf(pQty[1]));
			
			productRepository.save(product);
			
		}
		
		if(orderItemList.size()>0){
			order.setCustomerByCustomerId(customer);
			
			order.setIsPaid(OrderRepository.ispaid_no);
			order.setSettled(0);
			order.setTotalProductPrice(orderItemList.stream().map(doi -> doi.getNum()*doi.getProductPrice()).reduce(0D, (a,b) -> a+b).doubleValue()*customer.getDiscount());
			order.setTotalFreight(0D);
			order.setDiscount(customer.getDiscount());
			order.setStatus(OrderRepository.status_new);
			order.setCreateTime(Calendar.getInstance().getTime());
			order.setModifyTime(Calendar.getInstance().getTime());
			
			order.setFromName(userCompany.getName());
			order.setFromPhone(userCompany.getMobile());
			order.setFromAddress(userCompany.getAddress());
			
			order.setToCustomerName(customer.getName());
			order.setToCustomerCompanyName(customer.getCompanyName());
			order.setToTel(customer.getPhone());
			order.setToPhone(customer.getPhone());
			order.setToAddress(customer.getAddress());
			order.setToShippingAddress(customer.getShippingAddress());
			order.setAdminMsg(adminMsg);
			order.setToEmail(customer.getCompanyEmail());
			order.setGst(gst);
			//save order
			orderRepository.save(order);
			//Save item
			orderItemList.forEach(oi -> oi.setOrder(order));
			orderItemRepository.save(orderItemList);
		}
		
	}
	
	/**
	 * 
	 * @param customerId
	 * @param pageable
	 * @return
	 */
	public Page<Order> getOrderByCustomerId(Long customerId, Pageable pageable) {
		return orderRepository.findByCustomerByCustomerIdId(customerId, pageable);
	}
	
	/**
	 * 导出报表
	 * 
	 * @param orderIds
	 * @return
	 * @throws FoundException
	 */
	@Transactional(rollbackOn = Exception.class)
	public ResponseEntity<byte[]> getOrderExport(Long[] orderIds) throws Exception {

		List<Order> list = orderRepository.findByIdIn(orderIds);

		File file = new File(absoultPath + "/order/" + DateHelper.getYYYYMMDD() + ".csv");

		PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file)));
		pw.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB,(byte) 0xBF })); 
		StringBuffer title = new StringBuffer();
		title.append("序号").append(",");
		title.append("NAL No.").append(",");
		title.append("地址").append(",");
		title.append("姓名").append(",");
		title.append("省").append(",");
		title.append("市").append(",");
		title.append("区").append(",");
		title.append("SKU products").append(",");
		title.append("产品名称").append(",");
		title.append("产品规格 CODE").append(",");
		title.append("手机").append(",");
		title.append("电话").append(",");
		title.append("QTY").append(",");
		title.append("PO No.").append(",");
		title.append("售价").append(",");
		
		pw.println(title.toString());
		
		
		for (Order order : list) {
			order.getOrderItems().forEach( oi ->{
				StringBuffer row = new StringBuffer();
				//序号
				row.append(order.getId()).append(",");
				//NAL No.
				row.append("").append(",");
				//地址
				row.append(order.getToProvince() + order.getToDistrict() + order.getToDistrict() + order.getToShippingAddress()).append(",");
				//姓名
				row.append(order.getToCustomerName()).append(",");
				//省
				row.append(order.getToProvince()).append(",");
				//市
				row.append(order.getToCity()).append(",");
				//区
				row.append(order.getToDistrict()).append(",");
				//SKU products
				row.append("").append(",");
				//产品名称
				row.append(oi.getProductNameCn()).append(",");
				//产品规格 CODE
				row.append(oi.getProduct().getMpn()).append(",");
				//手机
				row.append(order.getToPhone()).append(",");
				//电话
				row.append(order.getToPhone()).append(",");
				//QTY
				row.append(oi.getNum()).append(",");
				//PO No.
				row.append("").append(",");
				//售价
				row.append("").append(",");
				pw.println(row.toString());
			});
		}
		pw.flush();
		pw.close();
		
		
		InputStream in = new FileInputStream(file);
		byte[] body =new byte[in.available()];
        in.read(body);
		in.close();
		

		HttpHeaders headers = new HttpHeaders();// 设置响应头
		headers.add("Content-Disposition", "attachment;filename=" + DateHelper.getYYYYMMDD() + ".csv" );
		HttpStatus statusCode = HttpStatus.OK;// 设置响应吗
		return new ResponseEntity<byte[]>(body, headers, statusCode);

	}
}
