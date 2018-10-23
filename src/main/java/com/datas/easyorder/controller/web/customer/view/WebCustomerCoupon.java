package com.datas.easyorder.controller.web.customer.view;

import com.datas.easyorder.db.entity.Coupon;
import com.datas.easyorder.db.entity.CouponCustomer;

public class WebCustomerCoupon {

	private Coupon coupon;
	private CouponCustomer couponCustomer;
	
	
	public Coupon getCoupon() {
		return coupon;
	}
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
	public CouponCustomer getCouponCustomer() {
		return couponCustomer;
	}
	public void setCouponCustomer(CouponCustomer couponCustomer) {
		this.couponCustomer = couponCustomer;
	}
	
	
	
}
