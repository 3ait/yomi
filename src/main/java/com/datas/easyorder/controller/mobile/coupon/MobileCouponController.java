package com.datas.easyorder.controller.mobile.coupon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/m/api/coupons")
public class MobileCouponController extends BaseController<Coupon> {

	@Autowired
	CouponLogic couponLogic;
	@Autowired
	CustomerLogic customerLogic;
	
	/**
	 * coupon list 列表
	 * @param customerId 不传递表示用户都为领取，传递会检查领取状态
	 * @param searchForm HttpEntity<List<CouponView>>
	 * @return
	 */
	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	@ResponseBody
	public HttpEntity<List<CouponView>> list(@RequestParam(value="customerId",required=false,defaultValue="0") Long customerId, @ModelAttribute(value = "searchForm") SearchForm searchForm) {

		Pageable pageable = new PageRequest(searchForm.getPage()-1<1?0:searchForm.getPage()-1, searchForm.getSize(),Direction.fromString(searchForm.getSort()),searchForm.getSortBy());
		
		searchForm.setStatus(CouponRepository.STATUS_ACTIVE);
		
		Customer customer = customerLogic.getCustomerById(customerId);
		List<CouponView> page = couponLogic.webListAll(searchForm,customer,pageable);

		return new ResponseEntity<List<CouponView>>(page, HttpStatus.OK);
	}
	
	/**
	 * 根据code获取
	 * @param code
	 * @return
	 */
	@RequestMapping(value = { "/code/{code}" },method=RequestMethod.GET)
	public ResponseEntity<Coupon> getByCode(@PathVariable(value="code") String code) {
		Coupon coupon = couponLogic.getByCode(code);
		return new ResponseEntity<Coupon>(coupon, HttpStatus.OK);
	}

	
	/**
	 * 
	 * 根据ID获取
	 * @param code
	 * @return ResponseEntity<Coupon>
	 */
	@RequestMapping(value = { "/{id}" },method=RequestMethod.GET)
	public ResponseEntity<Coupon> getById(@PathVariable("id")Long id) {
		Coupon coupon = couponLogic.getById(id);
		return new ResponseEntity<Coupon>(coupon, HttpStatus.OK);
	}
	

	
}
