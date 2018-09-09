package com.datas.easyorder.controller.web.customer;

import com.datas.easyorder.db.entity.Customer;

public class CustomerView {

	
	private Customer customer;
	private Long branchId;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	
	
	
	
}
