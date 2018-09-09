package com.datas.easyorder.controller.administrator.order.logic;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.datas.easyorder.controller.BaseLogic;
import com.datas.easyorder.db.dao.OrderItemRepository;
import com.datas.easyorder.db.dao.OrderRepository;
import com.datas.easyorder.db.dao.ProductRepository;
import com.datas.easyorder.db.entity.Order;
import com.datas.easyorder.db.entity.OrderItem;
import com.datas.easyorder.db.entity.Product;

@Component
public class OrderItemLogic extends BaseLogic<OrderItem> {

	@Autowired
	OrderItemRepository orderItemRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	ProductRepository productRepository;

	@Override
	public CrudRepository<OrderItem, Long> getRepository() {
		return orderItemRepository;
	}
	
	/**
	 * 删除订单的产品
	 * @param orderId
	 * @param orderItemId
	 */
	@Transactional(rollbackOn = Exception.class)
	public void removeOrderItem(Long orderItemId) {
		
		Order order = orderRepository.findOne(orderItemRepository.findOne(orderItemId).getOrder().getId());
		orderItemRepository.delete(orderItemId);
		
		Double sumPrice = order.getOrderItems().stream().collect(Collectors.summingDouble(oi -> oi.getProductPrice()*oi.getNum())).doubleValue();
		order.setTotalProductPrice(sumPrice);
		order.setModifyTime(Calendar.getInstance().getTime());
		orderRepository.save(order);
	}




	@Override
	@Transactional(rollbackOn = Exception.class)
	public OrderItem update(Long id, String atrributeName, String attributeValue) {
		OrderItem orderItem = super.update(id, atrributeName, attributeValue);
		Order order = orderRepository.findOne(orderItem.getOrder().getId());
		
		Double sumPrice = order.getOrderItems().stream().collect(Collectors.summingDouble(oi -> oi.getProductPrice()*oi.getNum())).doubleValue();
		order.setTotalProductPrice(sumPrice);
		order.setModifyTime(Calendar.getInstance().getTime());
		orderRepository.save(order);
		
		return orderItem;
	}


	/**
	 * 后台给订单添加一个产品，默认数量1
	 * @param orderId
	 * @param productId
	 */
	@Transactional(rollbackOn = Exception.class)
	public void addProductToOrder(Long orderId, Long productId) {
		
		Product product = productRepository.findOne(productId);
		OrderItem orderItem = new OrderItem();
		orderItem.setProduct(product);
		orderItem.setNum(1);
		
		Order order = orderRepository.findOne(orderId);
		
		orderItem.setOrder(order);
		orderItem.setProductDefaultSrc(product.getDefaultSrc());
		orderItem.setProductNameCn(product.getProductName());
		orderItem.setProductNameEn(product.getProductNameAlias());
		orderItem.setProductPrice(product.getPrice2()>0?product.getPrice2():product.getPrice1());
		orderItemRepository.save(orderItem);
		
		//跟新Order总价格
		Double sumPrice = order.getOrderItems().stream().collect(Collectors.summingDouble(oi -> oi.getProductPrice()*oi.getNum())).doubleValue();
		order.setTotalProductPrice(sumPrice);
		order.setModifyTime(Calendar.getInstance().getTime());
		orderRepository.save(order);
		
	}

	public List<OrderItem> getItemsByOrderId(Long orderId) {
		return orderItemRepository.findByOrderId(orderId, new PageRequest(0, Integer.MAX_VALUE)).getContent();
	}
	
	
}
