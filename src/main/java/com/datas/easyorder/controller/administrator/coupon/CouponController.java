package com.datas.easyorder.controller.administrator.coupon;

import java.util.Calendar;

import javax.validation.Valid;

import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.coupon.logic.CouponLogic;
import com.datas.easyorder.controller.administrator.order.logic.OrderLogic;
import com.datas.easyorder.db.entity.Coupon;
import com.datas.easyorder.db.entity.CouponCustomer;
import com.datas.easyorder.db.entity.CouponOrder;
import com.datas.utils.SearchForm;
import com.plugin.utils.DateHelper;

/**
 * 
 * @author leo
 * Coupon management
 *
 */
@RestController
@RequestMapping("/management/coupon")
public class CouponController extends BaseController<Coupon> {

	@Autowired
	CouponLogic couponLogic;
	@Autowired
	OrderLogic orderLogic;
	
	/**
	 * index
	 * 
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return ModelAndView
	 */
	@RequestMapping(value = { "", "/" })
	public ModelAndView index(@Valid @ModelAttribute(value = "searchForm") SearchForm searchForm) {

		ModelAndView modelAndView = new ModelAndView("administrator/coupon/index");

		if (StringHelper.isEmpty(searchForm.getDateFrom())) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.YEAR, -3);
			searchForm.setDateFrom(DateHelper.getYYYYMMDD(calendar.getTime().getTime()));
		}
		if (StringHelper.isEmpty(searchForm.getDateTo())) {
			searchForm.setDateTo(DateHelper.getYYYYMMDD());
		}

		Pageable pageable = new PageRequest(searchForm.getPage()-1<1?0:searchForm.getPage()-1, searchForm.getSize(),Direction.fromString(searchForm.getSort()),searchForm.getSortBy());

		Page<Coupon> page = couponLogic.list(searchForm, pageable);

		modelAndView.addObject("couponList", page.getContent());
		super.setPage(modelAndView, page, searchForm);

		modelAndView.addObject("salesArray", orderLogic.getSales().toString());
		modelAndView.addObject("salesList", orderLogic.geSalesList());

		return modelAndView;
	}

	
	/**
	 * 更新value属性
	 */
	@RequestMapping("/update/colomn/")
	@ResponseBody
	public void valueUpdate(@RequestParam("pk") Long pk, @RequestParam("name") String name,
			@RequestParam("value") String value) {
		couponLogic.update(pk, name, value);
	}
	
	/**
	 * 新建
	 * @param searchForm
	 * @return
	 */
	@RequestMapping(value = { "/new" })
	public ModelAndView newCoupon() {

		ModelAndView modelAndView = new ModelAndView("administrator/coupon/fragment/new-container");

		return modelAndView;
	}
	
	/**
	 * 保存
	 * @param searchForm
	 * @return
	 */
	@RequestMapping(value = { "/save" })
	public ModelAndView newCoupon(@ModelAttribute Coupon coupon,@RequestParam("expiredDate") String expiredDate) {

		ModelAndView modelAndView = new ModelAndView("administrator/coupon/fragment/new-container");
		couponLogic.save(coupon,expiredDate);
		return modelAndView;
	}
	
	
	/**
	 * 根据优惠券ID 查看订单
	 * @param searchForm
	 * @return
	 */
	@RequestMapping(value = { "/view/order/{couponId}" })
	public ModelAndView couponOrder(@PathVariable("couponId") Long couponId) {

		ModelAndView modelAndView = new ModelAndView("administrator/coupon/coupon-order");
		Pageable pageable = new PageRequest(0,Integer.MAX_VALUE,Direction.DESC,"id");

		Page<CouponOrder> page = couponLogic.getCouponOrderByCouponId(couponId,pageable);
		modelAndView.addObject("couponOrderList", page.getContent());
		
		return modelAndView;
	}
	
	/**
	 * 根据优惠券ID 查看用户
	 * @param searchForm
	 * @return
	 */
	@RequestMapping(value = { "/view/user/{couponId}" })
	public ModelAndView couponUser(@PathVariable("couponId") Long couponId) {

		ModelAndView modelAndView = new ModelAndView("administrator/coupon/coupon-user");
		
		Pageable pageable = new PageRequest(0,Integer.MAX_VALUE,Direction.DESC,"id");
		Page<CouponCustomer> page = couponLogic.getCouponCustomerByCouponId(couponId,pageable);
		modelAndView.addObject("couponCustomerList", page.getContent());
		return modelAndView;
	}
	
}
