package com.datas.easyorder.controller.administrator.customer;

import java.util.Date;

import com.datas.easyorder.db.entity.CustomerCommission;
import com.datas.easyorder.db.entity.Order;

public class CommissionView {

	private Order order;
	private CustomerCommission customerCommission;
	private Date createTime;
	
	

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public CustomerCommission getCustomerCommission() {
		return customerCommission;
	}

	public void setCustomerCommission(CustomerCommission customerCommission) {
		this.customerCommission = customerCommission;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
	
}
