package com.datas.easyorder.controller.administrator.order;

import java.util.List;

import com.datas.easyorder.db.entity.Order;
import com.datas.easyorder.db.entity.OrderItem;

public class OrderPdfView {

	private Order order;
	private List<OrderItem> orderItemList;
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	
	
}
