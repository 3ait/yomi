package com.datas.easyorder.controller.administrator.customer.view;

import com.datas.easyorder.db.entity.Customer;
import com.datas.easyorder.db.entity.RankCustomer;

public class CustomerRankView {

	private RankCustomer rankCustomer;
	private Customer customer;
	
	
	
	public RankCustomer getRankCustomer() {
		return rankCustomer;
	}
	public void setRankCustomer(RankCustomer rankCustomer) {
		this.rankCustomer = rankCustomer;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
}
