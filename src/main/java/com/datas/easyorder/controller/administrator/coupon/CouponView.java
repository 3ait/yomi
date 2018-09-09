package com.datas.easyorder.controller.administrator.coupon;

import com.datas.easyorder.db.entity.Coupon;

public class CouponView {

	public Coupon coupon;
	public boolean collected;

	public Coupon getCoupon() {
		return coupon;
	}
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
	public boolean isCollected() {
		return collected;
	}
	public void setCollected(boolean collected) {
		this.collected = collected;
	}
	
	
	
}
