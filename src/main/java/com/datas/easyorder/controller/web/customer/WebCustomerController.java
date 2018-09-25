package com.datas.easyorder.controller.web.customer;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.branch.logic.BranchLogic;
import com.datas.easyorder.controller.administrator.coupon.logic.CouponLogic;
import com.datas.easyorder.controller.administrator.customer.CommissionView;
import com.datas.easyorder.controller.administrator.customer.logic.CustomerCommissionLogic;
import com.datas.easyorder.controller.administrator.customer.logic.CustomerLogic;
import com.datas.easyorder.controller.administrator.order.logic.OrderLogic;
import com.datas.easyorder.controller.web.cart.logic.CartLogic;
import com.datas.easyorder.db.dao.CustomerRepository;
import com.datas.easyorder.db.dao.OrderRepository;
import com.datas.easyorder.db.entity.Branch;
import com.datas.easyorder.db.entity.Customer;
import com.datas.easyorder.db.entity.Order;
import com.datas.utils.SearchForm;
import com.google.zxing.WriterException;
import com.plugin.utils.DateHelper;
import com.plugin.utils.QrCode;

/**
 * 
 * @author leo
 *
 */
@RestController
@RequestMapping("/customer")
public class WebCustomerController extends BaseController{
	private static Logger logger = LogManager.getLogger(WebCustomerController.class);
	@Autowired
	CustomerLogic customerLogic;
	@Autowired
	CartLogic cartLogic;
	@Autowired
	OrderLogic orderLogic;
	@Autowired
	CustomerCommissionLogic customerCommissionLogic;
	@Autowired
	CouponLogic couponLogic;
	@Autowired
	BranchLogic branchLogic;
	/**
	 * 
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return
	 */
	@RequestMapping(value={""})
	public ModelAndView index(@ModelAttribute(value = "searchForm") SearchForm searchForm) {
		
		ModelAndView modelAndView = new ModelAndView("web/customer/registration");
		
		Pageable pageable = new PageRequest(searchForm.getPage(), searchForm.getSize());
		List<Branch> list = branchLogic.getAllBranch(pageable).getContent();
		
		modelAndView.addObject("branchList", list);
		return modelAndView;
	}


	/**
	 * login
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return
	 */
	@RequestMapping(value={"/login"},method=RequestMethod.POST)
	public ModelAndView login(@RequestParam(value="username",required=true) String username,
			@RequestParam(value="password",required=true) String loginPassword,
			HttpServletRequest request
			) {
		
		ModelAndView modelAndView = new ModelAndView("web/customer/registration");
		
		Customer customer = customerLogic.login(username, loginPassword);
		
		if(customer!=null){
			if(customer.getStatus()==(byte)0){
				modelAndView.addObject("msg", "Please contact us to active your account.");
				return modelAndView;
			}
			request.getSession().setAttribute(SESSION_CUSTOMER, customer);
			return new ModelAndView(new RedirectView("/cart",true) );
		}
		modelAndView.addObject("msg", "email or password error.");
		return modelAndView;
	}

	
	
	/**
	 * register
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return
	 */
	@RequestMapping(value={"/register"})
	public ModelAndView register(@Valid @ModelAttribute("customerView") CustomerView customerView, BindingResult bindingResult,HttpServletRequest request) {
		
		if(bindingResult.hasErrors()){
			return new ModelAndView(new RedirectView("/customer",true));
		}
		Branch branch = new Branch();
		branch.setId(customerView.getBranchId());
		customerView.getCustomer().setBranch(branch);
		Customer customer = customerLogic.save(customerView);
		request.getSession().setAttribute(SESSION_CUSTOMER, customer);
		
		return new ModelAndView(new RedirectView("/",true));
	}
	
	
	/**
	 * Logout
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return
	 */
	@RequestMapping(value={"/logout"})
	public ModelAndView logout(HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView(new RedirectView("/",true) );
		
		request.getSession().removeAttribute(SESSION_CUSTOMER);
		
		return modelAndView;
	}

	
	/**
	 * cart next
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return
	 */
	@RequestMapping(value="/cart/next")
	public ModelAndView cartNext(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("web/cart/order");
		Customer customer = (Customer) request.getSession().getAttribute(SESSION_CUSTOMER);
		modelAndView.addObject("customer", customer);
		return modelAndView;
	}
	
	/**
	 * place Order
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return
	 */
	@RequestMapping(value="/cart/order/place")
	@ResponseBody
	public Order createOrder(HttpServletRequest request,
			@ModelAttribute("PlaceOrderForm") PlaceOrderForm placeOrderForm) {
		
		Customer customer = (Customer)request.getSession().getAttribute(SESSION_CUSTOMER);
		placeOrderForm.setCustomerId(customer.getId());
		Order order = cartLogic.createOrder(placeOrderForm);
		
		return order;
	}
	
	
	/**
	 * place Order
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return
	 */
	@RequestMapping(value="/cart/order/place/{orderId}")
	public ModelAndView finishOrder(@PathVariable("orderId") Long orderId,HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("web/cart/order-success");
		
		Customer customer = (Customer) request.getSession().getAttribute(SESSION_CUSTOMER);
		Order order = cartLogic.getOrderById(orderId);
		modelAndView.addObject("customer", customer);
		modelAndView.addObject("order", order);
		modelAndView.addObject("rate", super.getRate(request));
		return modelAndView;
	}

	
	
	/**
	 * customer info
	 * session 中获取Id
	 * @return ModelAndView
	 */
	@RequestMapping(value="/info")
	public ModelAndView info(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("web/customer/info");
		
		Customer customer = (Customer) request.getSession().getAttribute(SESSION_CUSTOMER);
		modelAndView.addObject("customer", customerLogic.getCustomerById(customer.getId()));
		
		return modelAndView;
	}
	
	
	/**
	 * customer info
	 * session 中获取Id
	 * @return ModelAndView
	 */
	@RequestMapping(value="/history")
	public ModelAndView orderHistory(HttpServletRequest request,@ModelAttribute(value = "searchForm") SearchForm searchForm) {
		ModelAndView modelAndView = new ModelAndView("web/customer/history");
		
		Customer customer = (Customer) request.getSession().getAttribute(SESSION_CUSTOMER);
		
		if (StringHelper.isEmpty(searchForm.getDateFrom())) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -6);
			searchForm.setDateFrom(DateHelper.getYYYYMMDD(calendar.getTime().getTime()));
		}
		if (StringHelper.isEmpty(searchForm.getDateTo())) {
			searchForm.setDateTo(DateHelper.getYYYYMMDD());
		}
		
		Pageable pageable = new PageRequest(searchForm.getPage()-1<1?0:searchForm.getPage()-1, searchForm.getSize(),Direction.DESC,"id");
		Page<Order> orderPage = orderLogic.getOrderList(customer, pageable);
		
		modelAndView.addObject("orderList", orderPage.getContent());
		modelAndView.addObject("totalPages", orderPage.getTotalPages());
		modelAndView.addObject("number", searchForm.getPage() > orderPage.getTotalPages() ? 0 : orderPage.getNumber() + 1);
		modelAndView.addObject("customer", customer);
		modelAndView.addObject("searchForm", searchForm);
		
		
		modelAndView.addObject("totalPrice", orderPage.getContent().stream().map(order -> order.getTotalProductPrice()).reduce(0D,(a,b)->a+b).doubleValue());
		modelAndView.addObject("totalCommission", orderPage.getContent().stream().map(order -> order.getTotalProductPrice()*(order.getSalesCommissionPercentage()==null?0:order.getSalesCommissionPercentage())).reduce(0D,(a,b)->a+b).doubleValue());
			
		return modelAndView;
	}
	
	/**
	 * commission
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/commission")
	public ModelAndView commission(HttpServletRequest request,@ModelAttribute(value = "searchForm") SearchForm searchForm) {
		ModelAndView modelAndView = new ModelAndView("web/customer/commission");
		
		Customer customer = (Customer) request.getSession().getAttribute(SESSION_CUSTOMER);
		
		if (StringHelper.isEmpty(searchForm.getDateFrom())) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -6);
			searchForm.setDateFrom(DateHelper.getYYYYMMDD(calendar.getTime().getTime()));
		}
		if (StringHelper.isEmpty(searchForm.getDateTo())) {
			searchForm.setDateTo(DateHelper.getYYYYMMDD());
		}
		
		List<CommissionView> commissionViewList = customerCommissionLogic.getCommissionPayment(customer.getId(),searchForm);
		modelAndView.addObject("commissionViewList", commissionViewList);
		modelAndView.addObject("searchForm", searchForm);
		
		modelAndView.addObject("customer", customer);
		
		
		return modelAndView;
	}
	
	
	
	/**
	 * orderId
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/commission/{orderId}")
	public ModelAndView viewOrder(HttpServletRequest request,@PathVariable("orderId") Long orderId) {
		
		ModelAndView modelAndView = new ModelAndView("web/customer/fragment/order-detail");
		Customer customer = (Customer) request.getSession().getAttribute(SESSION_CUSTOMER);
		
		Order order = orderLogic.getOderByIdAndCustomerId(orderId,customer.getId());
		modelAndView.addObject("order", order);
		
		return modelAndView;
	}
	
	
	
	/**
	 * 更新value属性
	 */
	@RequestMapping("/update/colomn/")
	@ResponseBody
	public void valueUpdate(
			@RequestParam("pk") Long  pk, 
			@RequestParam("name") String name , 
			@RequestParam("value") String value ,
			HttpServletRequest request
			){
		
		if(name.equalsIgnoreCase("balance")){
			return ;
		}
		Customer customer = customerLogic.update(pk,name,value);
		
		request.getSession().setAttribute(SESSION_CUSTOMER, customer);
	}

	/**
	 * 关联词查询customer
	 */
	@RequestMapping("/q")
	@ResponseBody
	public ResponseEntity<List<Customer>> getCustomerList(@ModelAttribute SearchForm searchForm){
		
		Pageable pageable = new PageRequest(searchForm.getPage()-1<1?0:searchForm.getPage()-1, searchForm.getSize(), Direction.fromString(searchForm.getSort()), searchForm.getSortBy());
		Page<Customer> list = customerLogic.findAll(searchForm,CustomerRepository.customerType_customer, pageable);
		return new ResponseEntity<List<Customer>>(list.getContent(),HttpStatus.OK);
		
	}
	
	/**
	 * 微信获取支付链接
	 * @param searchForm
	 * @return
	 * @throws IOException 
	 * @throws WriterException 
	 */
	@RequestMapping("/payment/wechat/{orderId}")
	public void getWechatPayUrl(@PathVariable("orderId") Long orderId,HttpServletResponse response) throws IOException, WriterException{
		
		Order order = orderLogic.getOrderById(orderId);
		
		String payUrl = iEMoney.wechatPcPay(order.getTotalProductPrice()+"", order.getId()+"", order.getToCustomerName()+order.getToPhone(),order.getId()+ "-" + DateHelper.getYYYYMMDDhhmmss());
		
		QrCode.createZxing(payUrl, response.getOutputStream());
		response.flushBuffer();
		
		
	}
	/**
	 * 支付宝获取支付链接
	 * @param searchForm
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/payment/alipay/{orderId}")
	@ResponseBody
	public ResponseEntity<String> getAliPayUrl(@PathVariable("orderId") Long orderId) throws IOException{
		
		Order order = orderLogic.getOrderById(orderId);
		
		String payUrl = iEMoney.aliPcPay(order.getTotalProductPrice()+"", order.getId()+"", order.getToCustomerName()+order.getToPhone(),order.getId()+ "-" + DateHelper.getYYYYMMDDhhmmss());
		return new ResponseEntity<String>(payUrl,HttpStatus.OK);
		
	}
	
	
	/**
	 * 支付直接返回
	 * @param request
	 */
	@RequestMapping(value="/payment/return", method = RequestMethod.GET)
	public ResponseEntity<String>  latipayReturn(HttpServletRequest request){
		
		String orderId = request.getParameter("merchant_reference");
		String ret = "faild";
		if(iEMoney.notifyCheck(request)){
			orderLogic.update(Long.valueOf(orderId), "isPaid", OrderRepository.ispaid_yes + "");
			orderLogic.updateProductStock(Long.valueOf(orderId));
			ret = "success";
		}
		return new ResponseEntity<String>(ret,HttpStatus.OK);
	}
	
	/**
	 * 支付通知
	 * @param request
	 */
	@RequestMapping(value="/payment/notice", method = RequestMethod.GET)
	@ResponseBody
	public String latipayCallback(HttpServletRequest request){
		String orderId = request.getParameter("merchant_reference");
		String ret = "faild";
		if(iEMoney.callBackCheck(request)){
			orderLogic.update(Long.valueOf(orderId), "isPaid", OrderRepository.ispaid_yes + "");
			orderLogic.updateProductStock(Long.valueOf(orderId));
			ret = "success";
		}
		return ret;
	}
	
	
}
