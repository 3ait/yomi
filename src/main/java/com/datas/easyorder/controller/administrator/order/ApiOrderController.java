package com.datas.easyorder.controller.administrator.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.order.logic.OrderItemLogic;
import com.datas.easyorder.controller.administrator.order.logic.OrderLogic;
import com.datas.easyorder.controller.administrator.product.logic.ProductLogic;
import com.datas.easyorder.controller.administrator.product.view.ProductMulitPrice;
import com.datas.easyorder.db.entity.Product;
import com.datas.utils.SearchForm;

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
	
	/**
	 * 
	 * @param searchForm
	 * @return
	 */
	@RequestMapping("/new/product/q")
	public ResponseEntity<List<ProductMulitPrice>> getProductAndMulitiplePrice(@ModelAttribute("searchForm") SearchForm searchForm){
		Pageable pageable = new PageRequest(searchForm.getPage()< 1?0:searchForm.getPage()-1, searchForm.getSize(), Direction.fromString(searchForm.getSort()), searchForm.getSortBy());
		return new ResponseEntity<List<ProductMulitPrice>>(productLogic.getProductAndMulitiplePrice(searchForm, pageable),HttpStatus.OK);
	}
}
