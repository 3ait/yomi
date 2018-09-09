package com.datas.easyorder.controller.administrator.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.order.logic.OrderItemLogic;
import com.datas.easyorder.controller.administrator.order.logic.OrderLogic;
import com.datas.easyorder.controller.administrator.product.logic.ProductLogic;
import com.datas.easyorder.db.entity.Product;

/**
 * 
 * @author leo
 *
 */
@RestController
@RequestMapping("/api/management/order")
public class ApiOrderController extends BaseController{

	@Autowired
	OrderLogic orderLogic;
	@Autowired
	OrderItemLogic orderItemLogic;
	@Autowired
	ProductLogic productLogic;
	@RequestMapping("/add/item/{orderId}/{productId}")
	public ResponseEntity<Product> addProductToOrder(@PathVariable("orderId") Long orderId, @PathVariable("productId") Long productId){
		
		orderItemLogic.addProductToOrder(orderId,productId);
		
		return new ResponseEntity<Product>(productLogic.getProdcutById(productId),HttpStatus.OK);
	}
	
}
