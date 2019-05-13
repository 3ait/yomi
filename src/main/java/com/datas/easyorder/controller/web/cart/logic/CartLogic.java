package com.datas.easyorder.controller.web.cart.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.datas.easyorder.controller.administrator.coupon.logic.CouponLogic;
import com.datas.easyorder.controller.web.customer.view.PlaceOrderForm;
import com.datas.easyorder.db.dao.CustomerRepository;
import com.datas.easyorder.db.dao.CustomerToAddressRepository;
import com.datas.easyorder.db.dao.OrderItemRepository;
import com.datas.easyorder.db.dao.OrderRepository;
import com.datas.easyorder.db.dao.ProductRepository;
import com.datas.easyorder.db.dao.RankProductPriceRepository;
import com.datas.easyorder.db.dao.UserCompanyRepository;
import com.datas.easyorder.db.entity.Customer;
import com.datas.easyorder.db.entity.CustomerToAddress;
import com.datas.easyorder.db.entity.Order;
import com.datas.easyorder.db.entity.OrderItem;
import com.datas.easyorder.db.entity.Product;
import com.datas.easyorder.db.entity.RankProductPrice;
import com.datas.easyorder.db.entity.UserCompany;

@Component("cartLogic")
public class CartLogic{

	@Autowired
	ProductRepository productRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	OrderItemRepository orderItemRepository;
	@Autowired
	UserCompanyRepository userCompanyRepository;
	@Autowired
	CustomerToAddressRepository customerToAddressRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CouponLogic couponLogic;
	@Autowired
	RankProductPriceRepository rankProductPriceRepository;
	
	//default defaultCommissionPercentage
	private double defaultCommissionPercentage = 0.15D;
	
	@Value("${default.sales}")
	public String defaultSales;
	

	public Order getOrderById(Long orderId) {
		return orderRepository.findOne(orderId);
	}
	
	
	/**
	 * CustomerToAddress
	 * @param customerId
	 * @param retNun
	 * @return CustomerToAddress
	 */
	public CustomerToAddress getCustomerToAddressList(Long customerId){
		Pageable pageable = new PageRequest(0, Integer.MAX_VALUE,Direction.DESC,"id");
		Page<CustomerToAddress> page = customerToAddressRepository.findByCustomerId(customerId,pageable);
		
		CustomerToAddress customerToAddress = new CustomerToAddress();
		if(page.getContent().size()>0){
			customerToAddress = page.getContent().get(0);
		}
		return customerToAddress;
	}
	

	
	/**
	 * 保存收件地址
	 */
	public void saveToaddress(CustomerToAddress customerToAddress){
		List<CustomerToAddress> list = customerToAddressRepository.findByToNameAndToAddress(customerToAddress.getToName(),customerToAddress.getToAddress());
		if(list.size()==0){
			customerToAddressRepository.save(customerToAddress);
		}
	}

	/**
	 * 生成新订单
	 * 	 * {"cart":[{"id":10013,"productName":"113 productname","productNameAlias":"11","mpn":"11","defaultSrc":"attachment/img/20170914/1328635840977.jpg","price":1.99,"num":2},
	 * @param placeOrderForm
	 * @param customer
	 * @return
	 */
	@Transactional(rollbackOn=Exception.class)
	public Order createOrder(PlaceOrderForm placeOrderForm) {

		Order order = new Order();
		Customer customer = customerRepository.findOne(placeOrderForm.getCustomerId());
		boolean ret = false;
		if(placeOrderForm.getProductList()!=null){
			ret = true;
		}
		if(ret){
			try {
				//保存收件人地址
				CustomerToAddress customerToAddress = new CustomerToAddress();
				customerToAddress.setCustomer(customer);
				customerToAddress.setToProvince(placeOrderForm.getToProvince());
				customerToAddress.setToCity(placeOrderForm.getToCity());
				customerToAddress.setToDistrict(placeOrderForm.getToDistrict());
				customerToAddress.setToName(placeOrderForm.getToCustomerName());
				customerToAddress.setToPhone(placeOrderForm.getToPhone());
				customerToAddress.setToAddress(placeOrderForm.getToAddress());
				saveToaddress(customerToAddress);
				
				JSONArray products = new JSONArray(placeOrderForm.getProductList());
				
				Double totalPrice = 0D;
				List<OrderItem> orderItemList = new ArrayList<>();
				for(int i=0;i<products.length();i++){
					JSONObject pJson = products.getJSONObject(i);
					Product p = productRepository.findOneByIdAndStatusNot(pJson.getLong("id"), ProductRepository.status_cancelled);
					
					//价格计算,如果促销价大于0按照促销价，否则获取用户等级价格，如果用户没有等级，则默认价格
					double price = p.getPrice1();
					if(p.getPrice2()>0){
						price = p.getPrice2();
					}else{
						if(customer.getRankCustomer()!=null){
							RankProductPrice rankProductPrice = rankProductPriceRepository.findOneByProductIdAndRankCustomerId(p.getId(), customer.getRankCustomer().getId());
							if(rankProductPrice!=null){
								price = rankProductPrice.getPrice();
							}
						}
					}
					
					OrderItem oi = new OrderItem();
					oi.setNum(pJson.getInt("num"));
					oi.setOrder(order);
					oi.setProduct(p);
					oi.setProductDefaultSrc(p.getDefaultSrc());
					oi.setProductNameCn(p.getProductName());
					oi.setProductNameEn(p.getProductNameAlias());
					oi.setProductPrice(price);
					
					orderItemList.add(oi);
					
					totalPrice += pJson.getInt("num")*price;
					
					p.setSoldNum(p.getSoldNum()+pJson.getInt("num"));
					p.setStock(p.getStock()-oi.getNum());
					if(p.getStock()<=0){
						p.setStatus(ProductRepository.status_out_stock);
					}
					productRepository.save(p);
				}
				
				UserCompany userCompany = userCompanyRepository.findOne(1L);
				order.setDiscount(customer.getDiscount());
				order.setCustomerByCustomerId(customer);
				order.setIsPaid(OrderRepository.ispaid_no);
				order.setSettled(0);
				
				
				order.setStatus(OrderRepository.status_new);
				order.setCreateTime(Calendar.getInstance().getTime());
				order.setModifyTime(Calendar.getInstance().getTime());
				
				order.setFromName(customer.getName() + " " + customer.getCompanyName());
				order.setFromPhone(customer.getPhone()+ " " + customer.getMobile());
				order.setFromAddress(customer.getAddress());
				
				
				order.setToCustomerName(placeOrderForm.getToCustomerName());
				order.setToCustomerCompanyName(placeOrderForm.getToCustomerCompanyName());
				order.setToTel(placeOrderForm.getToTel());
				order.setToPhone(placeOrderForm.getToPhone());
				order.setToAddress(placeOrderForm.getToAddress());
				order.setToShippingAddress(placeOrderForm.getToShippingAddress());
				order.setCustomerMsg(placeOrderForm.getCustomerMsg());
				order.setToProvince(placeOrderForm.getToProvince());
				order.setToCity(placeOrderForm.getToCity());
				order.setToDistrict(placeOrderForm.getToDistrict());
				//default commision 0.15
				order.setSalesCommissionPercentage(defaultCommissionPercentage);
				
				if(placeOrderForm.getSalesId()!=null){
					Customer sales = customerRepository.findOne(placeOrderForm.getSalesId());
					if(sales!=null && sales.getCustomerType().equals(CustomerRepository.customerType_sales) && sales.getStatus()==CustomerRepository.status_active){
						sales.setId(placeOrderForm.getSalesId());
						order.setCustomerBySalesId(sales);
					}
				}else{
					Customer sales=customerRepository.findOneByEmail(defaultSales);
					if(sales!=null)
						order.setCustomerBySalesId(sales);
				}
				
				orderRepository.save(order);
				//SaveItem
				orderItemRepository.save(orderItemList);
				
				//检查coupon ID,如果有效则更新价格,
				if(placeOrderForm.getCouponId()!=null){
					order.setTotalProductPrice(couponLogic.getDiscountPirceByCouponId(placeOrderForm.getCouponId(),order,customer, totalPrice));
				}
				if(customer.getDiscount()>0 && customer.getDiscount()<1){
					order.setTotalProductPrice(totalPrice*customer.getDiscount());
					order.setTotalFreight(placeOrderForm.getTotalFreight()*customer.getDiscount());
				}else{
					order.setTotalProductPrice(totalPrice);
					order.setTotalFreight(placeOrderForm.getTotalFreight());
				}
				orderRepository.save(order);
				
			} catch (Exception e) {
				ret = false;
				e.printStackTrace();
			}
		}
		return order;
	
	}
	
	
}
