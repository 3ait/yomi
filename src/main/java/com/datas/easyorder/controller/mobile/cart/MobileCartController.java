package com.datas.easyorder.controller.mobile.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datas.easyorder.controller.administrator.order.logic.OrderLogic;
import com.datas.easyorder.controller.web.cart.logic.CartLogic;

/**
 * 
 * @author leo
 *
 */
@RestController
@RequestMapping("/m/api/cart")
public class MobileCartController{

	@Autowired
	OrderLogic orderLogic;
	@Autowired
	CartLogic cartLogic;
	
}
