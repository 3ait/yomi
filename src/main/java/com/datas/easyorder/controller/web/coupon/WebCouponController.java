package com.datas.easyorder.controller.web.coupon;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.coupon.CouponView;
import com.datas.easyorder.controller.administrator.coupon.logic.CouponLogic;
import com.datas.easyorder.controller.administrator.customer.logic.CustomerLogic;
import com.datas.easyorder.db.dao.CouponRepository;
import com.datas.easyorder.db.entity.Coupon;
import com.datas.easyorder.db.entity.Customer;
import com.datas.utils.SearchForm;

/**
 * 
 * @author leo
 * MobileCouponController
 *
 */
@RestController
@RequestMapping("/coupons")
public class WebCouponController extends BaseController<Coupon> {

	@Autowired
	CouponLogic couponLogic;
	@Autowired
	CustomerLogic customerLogic;

	/**
	 * ResponseEntity<List<CouponView>>
	 * @param searchForm
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "", "/" })
	public ModelAndView index(@ModelAttribute(value = "searchForm") SearchForm searchForm , HttpServletRequest request) {
		
		
		ModelAndView mv = new ModelAndView("web/index/coupon");

		return mv;
	}
	
	/**
	 * ResponseEntity<List<CouponView>>
	 * @param searchForm
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/list" })
	public ResponseEntity<List<CouponView>> getCoupons(@ModelAttribute(value = "searchForm") SearchForm searchForm , HttpServletRequest request) {
		
		
		Pageable pageable = new PageRequest(searchForm.getPage()-1<1?0:searchForm.getPage()-1, searchForm.getSize(),Direction.fromString(searchForm.getSort()),searchForm.getSortBy());
		
		searchForm.setStatus(CouponRepository.STATUS_ACTIVE);
		
		Customer customer = (Customer) request.getSession().getAttribute(SESSION_CUSTOMER);
		List<CouponView> couponViewList = couponLogic.webListAll(searchForm,customer,pageable);

		return new ResponseEntity<List<CouponView>>(couponViewList, HttpStatus.OK);
	}
	
	

	/**
	 * 根据code获取
	 * @param code
	 * @return
	 */
	@RequestMapping(value = { "/api/code/{code}" })
	public ResponseEntity<Coupon> getByCode(@PathVariable("code")String code) {
		Coupon coupon = couponLogic.getByCode(code);
		return new ResponseEntity<Coupon>(coupon, HttpStatus.OK);
	}

	
	/**
	 * 
	 * 根据ID获取
	 * @param code
	 * @return ResponseEntity<Coupon>
	 */
	@RequestMapping(value = { "/api/id/{id}" })
	public ResponseEntity<Coupon> getById(@PathVariable("id")Long id) {
		Coupon coupon = couponLogic.getById(id);
		return new ResponseEntity<Coupon>(coupon, HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * 根据ID领取优惠券
	 * @param id
	 * @return ResponseEntity<Coupon>
	 * @throws IOException 
	 */
	@RequestMapping(value = { "/collect/id/{id}" })
	public ResponseEntity<CouponView> collectCouponById(HttpServletRequest request,HttpServletResponse response,  @PathVariable("id") Long id) throws IOException {
		
		Customer customer = (Customer) request.getSession().getAttribute(SESSION_CUSTOMER);
		
		if(customer==null){
			return new ResponseEntity<CouponView>(HttpStatus.METHOD_NOT_ALLOWED);
		}else{
			CouponView couponView = couponLogic.collectCouponById(id,customer);
			return new ResponseEntity<CouponView>(couponView, HttpStatus.OK);
		}
	}
	
	/**
	 * 
	 * 根据CODE领取优惠券
	 * @param code
	 * @return ResponseEntity<Coupon>
	 * @throws IOException 
	 */
	@RequestMapping(value = {"/collect/code/{code}"})
	public ResponseEntity<CouponView> collectCouponByCode(HttpServletRequest request, @PathVariable("code") String code) throws IOException {
		
		Customer customer = (Customer) request.getSession().getAttribute(SESSION_CUSTOMER);
		if(customer==null){
			return new ResponseEntity<CouponView>(HttpStatus.METHOD_NOT_ALLOWED);
		}else{
			CouponView couponView = couponLogic.collectCouponByCode(code,customer);
			return new ResponseEntity<CouponView>(couponView, HttpStatus.OK);
		}
	}

	
}
