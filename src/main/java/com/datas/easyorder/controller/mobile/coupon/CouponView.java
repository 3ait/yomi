package com.datas.easyorder.controller.mobile.coupon;

import com.datas.easyorder.db.entity.Coupon;

public class CouponView {

	private Coupon Coupon;
	private boolean collected;
	public Coupon getCoupon() {
		return Coupon;
	}
	public void setCoupon(Coupon coupon) {
		Coupon = coupon;
	}
	public boolean isCollected() {
		return collected;
	}
	public void setCollected(boolean collected) {
		this.collected = collected;
	}
	
	
	
}
