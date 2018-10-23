package com.datas.easyorder.controller.mobile.my;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datas.easyorder.api.nzpost.TrackingNZPost;
import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.coupon.CouponView;
import com.datas.easyorder.controller.administrator.coupon.logic.CouponLogic;
import com.datas.easyorder.controller.administrator.customer.logic.CustomerLogic;
import com.datas.easyorder.controller.administrator.order.logic.OrderLogic;
import com.datas.easyorder.controller.mobile.my.interceptor.JwtToken;
import com.datas.easyorder.controller.web.cart.logic.CartLogic;
import com.datas.easyorder.controller.web.customer.view.CustomerView;
import com.datas.easyorder.controller.web.customer.view.PlaceOrderForm;
import com.datas.easyorder.db.dao.OrderRepository;
import com.datas.easyorder.db.entity.Coupon;
import com.datas.easyorder.db.entity.Customer;
import com.datas.easyorder.db.entity.Order;
import com.datas.easyorder.db.entity.OrderItem;
import com.datas.utils.SearchForm;
import com.plugin.utils.DateHelper;

/**
 * 
 * @author leo
 *
 */
@Controller
@RequestMapping("/m/api/customers")
public class MobileMyController extends BaseController {

	@Autowired
	CustomerLogic customerLogic;
	@Autowired
	CartLogic cartLogic;
	@Autowired
	OrderLogic orderLogic;
	TrackingNZPost trackingNZPost;
	@Autowired
	CouponLogic couponLogic;
	


	@RequestMapping(value="/{customerId}",method = RequestMethod.GET)
	public ResponseEntity<Customer> customer(@PathVariable("customerId") Long customerId) {
		Customer customer = customerLogic.getCustomerById(customerId);
		
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * @param loginEmail
	 * @param loginPassword
	 * @param request
	 * @return ResponseEntity<Customer>
	 * @throws Exception 
	 */
	@RequestMapping(value = { "/login" },method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password) throws Exception {
		String token = "";
		Customer customer = customerLogic.login(username, password);
		if (customer != null) {
			token = JwtToken.createToken(customer.getId());
		}
		
		return new ResponseEntity<String>(token,HttpStatus.OK);
	}

	/**
	 * register
	 * 
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = { "/register"} ,method = RequestMethod.POST)
	public ResponseEntity<CustomerToken> register(@Valid @ModelAttribute("customerView") CustomerView customerView,
			BindingResult bindingResult) throws Exception {

		Customer customer = customerLogic.save(customerView);
		
		CustomerToken ct = new CustomerToken();
		ct.setToken(JwtToken.createToken(customer.getId()));
		ct.setCustomer(customer);
		
		return new ResponseEntity<CustomerToken>(ct, HttpStatus.OK);
	}

	/**
	 * email check
	 * 
	 * @return String
	 */
	@RequestMapping(value = { "/email" },method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkEmail(
			@RequestParam(value = "customer.email", required = true) String registerEmail) {
		    Boolean ret = false;
			Customer customer = customerLogic.findByEmail(registerEmail);
			if (customer == null) {
				ret = true;
			}
			return new ResponseEntity<>(ret,HttpStatus.OK);
	}

	/**
	 * phone check
	 * 
	 * @return String
	 */
	@RequestMapping(value = { "/phone" },method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkPhone(
			@RequestParam(value = "customer.phone", required = true) String phone) {
		Boolean ret = false;
		Customer customer = customerLogic.findByPhone(phone);
		if (customer == null) {
			ret = true;
		}
		return new ResponseEntity<>(ret,HttpStatus.OK);
	}
	/**
	 * 编辑保存
	 * 
	 * @return String
	 */
	@RequestMapping(value = { "/{customerId}" },method = RequestMethod.PUT)
	public ResponseEntity<Object> save(@PathVariable("customerId") Long customerId,@ModelAttribute("customer") Customer customer) {
		Customer customerDb = customerLogic.getCustomerById(customerId);

		if (customerDb != null) {
			customerDb.setName(customer.getName());
			customerDb.setMobile(customer.getMobile());
			customerDb.setShippingAddress(customer.getShippingAddress());
			customerLogic.editSave(customerDb);
			return new ResponseEntity<>(customerDb, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 * 退出
	 * 
	 * @return String
	 */
	@RequestMapping(value = { "/logout" },method = RequestMethod.GET)
	public ResponseEntity<Object> logout() {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * 查询包裹
	 * @return String
	 * @throws IOException 
	 * @throws JSONException 
	 */
	@RequestMapping(value={"/tracking/{trackingNum}"},method = RequestMethod.GET)
	public ResponseEntity<Object> getTracking(@PathVariable("trackingNum") String trackingNum) throws JSONException, IOException {
		
		return new ResponseEntity<>(trackingNZPost.getTracking(trackingNum),HttpStatus.OK);
	}
	
	/**
	 * 更新value属性
	 */
	@RequestMapping(value = "/{customerId}/column",method = RequestMethod.PUT)
	public ResponseEntity<Customer> valueUpdate(
			@PathVariable("customerId") Long customerId,
			@RequestParam("pk") Long  pk, 
			@RequestParam("name") String name , 
			@RequestParam("value") String value ,
			HttpServletRequest request
			){
		
		Customer customer = customerLogic.update(pk,name,value);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
	/**
	 * 生成 Order
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/{customerId}/orders/place", method=RequestMethod.POST)
	public Order createOrder(@ModelAttribute("placeOrderForm") PlaceOrderForm placeOrderForm, @PathVariable("customerId") Long customerId) {
		
		placeOrderForm.setCustomerId(customerId);
		Order order = cartLogic.createOrder(placeOrderForm);
		
		return order;
	}
	
	/**
	 * 历史订单
	 * 
	 * @return String
	 */
	@RequestMapping(value={"/{customerId}/orders"}, method = RequestMethod.GET)
	public ResponseEntity<Page<Order>> orderhistory(@PathVariable("customerId") Long customerId,
			@ModelAttribute(value = "searchForm") SearchForm searchForm) {
		
		Customer customer = customerLogic.getCustomerById(customerId);
		
		if(customer!=null){
			Pageable pageable = new PageRequest(searchForm.getPage() - 1 < 1 ? 0 : searchForm.getPage() - 1,searchForm.getSize(),Direction.fromString(searchForm.getSort()),searchForm.getSortBy());
			Page<Order> orderPage = orderLogic.getOrderList(customer, pageable);
			return new ResponseEntity<>(orderPage,HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * 根据订单ID 获取 Coupon
	 * 
	 * @return ResponseEntity<Coupon>
	 */
	@RequestMapping(value={"/orders/{orderId}/coupons"})
	public ResponseEntity<Coupon> orderCoupon(@PathVariable("orderId") Long orderId) {

		Coupon coupon = orderLogic.getCouponByOrderId(orderId);
		return new ResponseEntity<>(coupon,HttpStatus.OK);
	}
	
	/**
	 * 订单明细
	 * 
	 * @return String
	 */
	@RequestMapping(value={"/{customerId}/orders/{orderId}/items"}, method = RequestMethod.GET)
	public ResponseEntity<List<OrderItem>> orderhistoryItems(@PathVariable("customerId") Long customerId,@PathVariable("orderId") Long orderId) {
		
		List<OrderItem> orderItemList = orderLogic.getOrderItemsByOrderId(orderId);
		return new ResponseEntity<>(orderItemList,HttpStatus.OK);
	}
	
	
	/**
	 * 获取微信支付链接 
	 * @param orderId
	 * @param returnUrl
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/payment/wechat/{orderId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> getIeMoneyPay(@PathVariable("orderId") Long orderId,@RequestParam("returnUrl") String returnUrl) throws IOException{
		
		Order order = orderLogic.getOrderById(orderId);
		
		String payUrl = iEMoney.wechatMobilePay(order.getTotalProductPrice()+"", order.getId()+"", order.getToCustomerName()+order.getToPhone(),order.getId()+ "-" + DateHelper.getYYYYMMDDhhmmss());
		return new ResponseEntity<String>(payUrl,HttpStatus.OK);
		
	}
	
	/**
	 * 获取支付链接 alipay
	 * @param searchForm
	 * @param paymentMethod payment_method	String	支付方式 wechat, alipay, or onlineBank
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/payment/alipay/{orderId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> getPayUrl(@PathVariable("orderId") Long orderId,@RequestParam("returnUrl") String returnUrl) throws IOException{
		
		Order order = orderLogic.getOrderById(orderId);
		
		String payUrl = iEMoney.aliMobilePay(order.getTotalProductPrice()+"", order.getId()+"", order.getToCustomerName()+order.getToPhone(),order.getId()+ "-" + DateHelper.getYYYYMMDDhhmmss());
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
	
	
	
	
	/**
	 * 我的有效优惠券
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/{customerId}/coupons/available"}, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Coupon>> couponCustomerListAvailable(@PathVariable("customerId") Long customerId) {
		List<Coupon> page = couponLogic.findAvailableCouponByCustomerId(customerId);
		return new ResponseEntity<List<Coupon>>(page, HttpStatus.OK);
	}
	
	/**
	 * 我的优惠券 快过期提醒
	 * @param request
	 * @return ResponseEntity<Boolean>
	 */
	@RequestMapping(value = { "/{customerId}/coupons/remind" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Boolean> couponRemind(@PathVariable("customerId") Long customerId,@RequestParam("dayNum") Integer dayNum) {
		List<Coupon> page = couponLogic.findCouponByCustomerIdAndExpiredDate(customerId,dayNum);
		return new ResponseEntity<>(page.size()>0, HttpStatus.OK);
	}
	
	/**
	 * 
	 * 根据ID领取优惠券
	 * @param id
	 * @return ResponseEntity<Coupon>
	 */
	@RequestMapping(value = { "/{customerId}/coupons/collect/id/{id}" }, method = RequestMethod.GET)
	public ResponseEntity<CouponView> collectCouponById(@PathVariable("customerId") Long customerId,@PathVariable("id") Long id) {
		
		Customer customer = customerLogic.getCustomerById(customerId);
		CouponView couponView = couponLogic.collectCouponById(id,customer);
		return new ResponseEntity<CouponView>(couponView, HttpStatus.OK);
	}
	
	/**
	 * 
	 * 根据CODE领取优惠券
	 * @param code
	 * @return ResponseEntity<Coupon>
	 */
	@RequestMapping(value = { "/{customerId}/coupons/collect/code/{code}" }, method = RequestMethod.GET)
	public ResponseEntity<CouponView> collectCouponByCode(@PathVariable("customerId") Long customerId,@PathVariable("code") String code) {
		
		Customer customer = customerLogic.getCustomerById(customerId);
		CouponView couponView = couponLogic.collectCouponByCode(code,customer);
		return new ResponseEntity<CouponView>(couponView, HttpStatus.OK);
	}


	/**
	 * 
	 * 修改密码
	 * 
	 */
	@RequestMapping(value="/{customerId}/password/", method = RequestMethod.GET)
	public ResponseEntity<Boolean> updatePassword(
			@PathVariable("customerId") Long customerId,
			@RequestParam("prePassword") String prePassword , 
			@RequestParam("newPassword") String newPassword ,
			HttpServletRequest request
			){
		
		boolean ret = customerLogic.updatePassword(customerId, prePassword, newPassword);
		
		return new ResponseEntity<Boolean>(ret, HttpStatus.OK);
	}
}
