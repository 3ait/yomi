package com.datas.easyorder.controller.web.customer.view;

public class PlaceOrderForm {

	private Long customerId;
	private String productList;
	private String customerMsg;
	private String toCustomerName;
	private String toCustomerCompanyName;
	private String toTel;
	private String toPhone;
	private String toAddress;
	private String toShippingAddress;
	
	private String toProvince;
	private String toCity;
	private String toDistrict;
	
	private Long couponId;
	private Long salesId;
	
	
	
	public String getCustomerMsg() {
		return customerMsg;
	}
	public void setCustomerMsg(String customerMsg) {
		this.customerMsg = customerMsg;
	}
	public String getToCustomerName() {
		return toCustomerName;
	}
	public void setToCustomerName(String toCustomerName) {
		this.toCustomerName = toCustomerName;
	}
	public String getToCustomerCompanyName() {
		return toCustomerCompanyName;
	}
	public void setToCustomerCompanyName(String toCustomerCompanyName) {
		this.toCustomerCompanyName = toCustomerCompanyName;
	}
	public String getToTel() {
		return toTel;
	}
	public void setToTel(String toTel) {
		this.toTel = toTel;
	}
	public String getToPhone() {
		return toPhone;
	}
	public void setToPhone(String toPhone) {
		this.toPhone = toPhone;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public String getToShippingAddress() {
		return toShippingAddress;
	}
	public void setToShippingAddress(String toShippingAddress) {
		this.toShippingAddress = toShippingAddress;
	}
	public String getToProvince() {
		return toProvince;
	}
	public void setToProvince(String toProvince) {
		this.toProvince = toProvince;
	}
	public String getToCity() {
		return toCity;
	}
	public void setToCity(String toCity) {
		this.toCity = toCity;
	}
	public String getToDistrict() {
		return toDistrict;
	}
	public void setToDistrict(String toDistrict) {
		this.toDistrict = toDistrict;
	}
	public Long getSalesId() {
		return salesId;
	}
	public void setSalesId(Long salesId) {
		this.salesId = salesId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getProductList() {
		return productList;
	}
	public void setProductList(String productList) {
		this.productList = productList;
	}
	public Long getCouponId() {
		return couponId;
	}
	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}
	
	
}
