package com.datas.easyorder.controller.mobile.my;

import com.datas.easyorder.db.entity.Customer;

public class CustomerToken {

	private String token;
	private Customer customer;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
