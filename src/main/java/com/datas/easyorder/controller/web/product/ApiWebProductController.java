package com.datas.easyorder.controller.web.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.category.logic.AttrLogic;
import com.datas.easyorder.controller.administrator.category.logic.CategoryLogic;
import com.datas.easyorder.controller.administrator.product.logic.ProductLogic;
import com.datas.easyorder.db.entity.Menu;
import com.datas.easyorder.db.entity.Product;
import com.datas.easyorder.db.entity.ProductAttrValue;
import com.datas.utils.SearchForm;


/**
 * 
 * @author leo
 *
 */
@RestController
@RequestMapping("/api/product")
public class ApiWebProductController extends BaseController{

	@Autowired
	AttrLogic attrLogic;
	@Autowired
	ProductLogic productLogic;
	@Autowired
	CategoryLogic categoryLogic;

	/**
	 * 获取属性
	 * @param key
	 * @param searchForm
	 * @return
	 */
	@RequestMapping("/attribute/{key}")
	public HttpEntity<Page<ProductAttrValue>> getAttribteValues( @PathVariable("key") String key,
			@ModelAttribute("searchForm") SearchForm searchForm) {

		searchForm.setSize(Integer.MAX_VALUE);
		searchForm.setSortBy("attrValue");
		Pageable pageable = new PageRequest(searchForm.getPage() - 1 < 1 ? 0 : searchForm.getPage() - 1, searchForm.getSize(), Direction.ASC, searchForm.getSortBy());

		Page<ProductAttrValue> page = attrLogic.findAllValuesByKey(key,pageable);

		return new ResponseEntity<Page<ProductAttrValue>>(page, HttpStatus.OK);
	}	
	
	/**
	 * 获取子菜单
	 * @param key
	 * @param searchForm
	 * @return
	 */
	@RequestMapping("/category/fatherId/{fatherId}")
	public HttpEntity<List<Menu>> getSubCategory( @PathVariable("fatherId") Long fatherId) {
		
		
		List<Menu> subCategoryList = categoryLogic.getMenuByFatherId(fatherId);
		return new ResponseEntity<List<Menu>>(subCategoryList, HttpStatus.OK);
	}	
	
	/**
	 * 品牌列表
	 * @param categoryId
	 * @param searchForm
	 * @return
	 */
	@RequestMapping("/list")
	public HttpEntity<Page<Product>> getProducts(@RequestParam(value="categoryId", defaultValue="-1",required = false) Long categoryId, 
			@RequestParam(value="productAttributeValueIds",required = false) Long[] productAttributeValueIds,
			@ModelAttribute("searchForm") SearchForm searchForm,HttpServletRequest request) {

		Pageable pageable = new PageRequest(searchForm.getPage(), searchForm.getSize(), Direction.fromString(searchForm.getSort()), searchForm.getSortBy());

		Page<Product> productPage = null;
		if(categoryId>0){
			productPage = productLogic.getCustomerRankPrice(productLogic.searchProductByAttrAndCategory(categoryId, productAttributeValueIds, pageable), super.getLoginCustomer(request));
		}
		
		if(searchForm.getQ() != null && searchForm.getQ().length() > 0){
			productPage = productLogic.getCustomerRankPrice(productLogic.searchProductByAttrAndQ(searchForm.getQ(), productAttributeValueIds, pageable), super.getLoginCustomer(request));
		}

		return new ResponseEntity<Page<Product>>(productPage, HttpStatus.OK);
	}
	
	/**
	 * 根据productAttrValueId 获取产品
	 * @param key
	 * @param searchForm
	 * @return
	 */
	@RequestMapping("/attrvalue/{productAttrValueId}")
	public HttpEntity<Page<Product>> getProductByProductAttrValueId( @PathVariable("productAttrValueId") Long productAttrValueId,
			@ModelAttribute("searchForm")  SearchForm searchForm,HttpServletRequest request) {
		
		Pageable pageable = new PageRequest(searchForm.getPage(), searchForm.getSize(), Direction.fromString(searchForm.getSort()), searchForm.getSortBy());

		Page<Product> page = productLogic.getCustomerRankPrice(productLogic.getProductByAttrValueId(productAttrValueId, pageable), super.getLoginCustomer(request));
		return new ResponseEntity<Page<Product>>(page, HttpStatus.OK);
	}	
	
	
	/**
	 *  热销
	 * @param searchForm
	 * @return
	 */
	@RequestMapping("/list/hot")
	public ResponseEntity<Page<Product>> hotProduct(@ModelAttribute SearchForm searchForm,HttpServletRequest request) {
		
		Pageable pageable = new PageRequest(0, searchForm.getSize(),Direction.fromString(searchForm.getSort()),searchForm.getSortBy());
		Page<Product> pageProduct = productLogic.getCustomerRankPrice(productLogic.getHotProduct(pageable), super.getLoginCustomer(request));
		return new ResponseEntity<Page<Product>>(pageProduct, HttpStatus.OK);
	}
	
	/**
	 * 推荐产品
	 * @param searchForm
	 * @return
	 */
	@RequestMapping("/list/recommend")
	public ResponseEntity<Page<Product>> recommendProduct(@ModelAttribute SearchForm searchForm,HttpServletRequest request) {
		Pageable pageable = new PageRequest(0, searchForm.getSize(),Direction.fromString(searchForm.getSort()),searchForm.getSortBy());
		
		Page<Product> pageProduct = productLogic.getCustomerRankPrice(productLogic.getRecommendProduct(pageable), super.getLoginCustomer(request)) ;
		return new ResponseEntity<Page<Product>>(pageProduct, HttpStatus.OK);
	}
	
	/**
	 * 首页frontpage
	 * @param searchForm
	 * @return
	 */
	@RequestMapping("/list/frontpage")
	public ResponseEntity<Page<Product>> frontPage(@ModelAttribute SearchForm searchForm,HttpServletRequest request) {
		Pageable pageable = new PageRequest(0, searchForm.getSize(),Direction.fromString(searchForm.getSort()),searchForm.getSortBy());
		
		Page<Product> pageProduct = productLogic.getCustomerRankPrice(productLogic.getFrontPageProduct(pageable), super.getLoginCustomer(request));
		return new ResponseEntity<Page<Product>>(pageProduct, HttpStatus.OK);
	}
	
	/**
	 * new Arrvails
	 * @param searchForm
	 * @return
	 */
	@RequestMapping("/list/promote")
	public ResponseEntity<Page<Product>> newArrivals(@ModelAttribute SearchForm searchForm,HttpServletRequest request) {
		Pageable pageable = new PageRequest(0, searchForm.getSize(),Direction.fromString(searchForm.getSort()),searchForm.getSortBy());
		
		Page<Product> pageProduct = productLogic.getCustomerRankPrice(productLogic.getPromoteProduct(pageable), super.getLoginCustomer(request));
		return new ResponseEntity<Page<Product>>(pageProduct, HttpStatus.OK);
	}
}
