package com.datas.easyorder.controller.administrator.product;

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
import com.datas.easyorder.controller.administrator.product.logic.ProductLogic;
import com.datas.easyorder.db.entity.Product;
import com.datas.utils.SearchForm;

/**
 * 
 * @author leo
 *
 */
@RestController
@RequestMapping("/api/management/product")
public class ApiProductController extends BaseController{

	@Autowired 
	ProductLogic productLogic;

	@RequestMapping("/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable("productId")Long productId){
		
		return new ResponseEntity<Product>(productLogic.getProdcutById(productId),HttpStatus.OK);
	}
	
	@RequestMapping("/q")
	public ResponseEntity<Page<Product>> qProduct(@ModelAttribute("searchForm") SearchForm searchForm){
		Pageable pageable = new PageRequest(searchForm.getPage()< 1?0:searchForm.getPage()-1, searchForm.getSize(), Direction.fromString(searchForm.getSort()), searchForm.getSortBy());
		return new ResponseEntity<Page<Product>>(productLogic.productSearch(searchForm, pageable),HttpStatus.OK);
	}
}
