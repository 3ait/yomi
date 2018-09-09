package com.datas.easyorder.controller.mobile.category;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.datas.easyorder.controller.administrator.category.logic.AttrLogic;
import com.datas.easyorder.controller.administrator.category.logic.CategoryLogic;
import com.datas.easyorder.controller.administrator.product.logic.ProductLogic;
import com.datas.easyorder.controller.web.index.MenuView;
import com.datas.easyorder.db.dao.MenuRepository;
import com.datas.easyorder.db.entity.Menu;
import com.datas.easyorder.db.entity.Product;
import com.datas.utils.SearchForm;

/**
 * 
 * @author leo
 *
 */
@RestController
@RequestMapping("/m/api/categories")
public class MobileCategoryController{

	
	@Autowired
	CategoryLogic categoryLogic;
	@Autowired
	ProductLogic productLogic;
	@Autowired
	AttrLogic attrLogic;
	
	
	/**
	 * 顶部菜单列表
	 * 
	 * @param searchForm
	 * @param bindingResult
	 * @param page
	 * @return
	 */
	@RequestMapping(value = { "/","" },method=RequestMethod.GET)
	public ResponseEntity<List<MenuView>> getCagegory() {
		List<MenuView> menuViewList = categoryLogic.getWebTopIndexMenu(new ArrayList<>(), true);
		return new ResponseEntity<List<MenuView>>(menuViewList, HttpStatus.OK);
	}

	/**
	 * 根据ID获取菜单
	 * @param categoryId
	 * @return
	 */
	@RequestMapping(value="/{categoryId}",method=RequestMethod.GET)
	public ResponseEntity<Menu> getMenuById(@PathVariable("categoryId") Long categoryId) {
		return  new ResponseEntity<Menu>(categoryLogic.getMenuById(categoryId), HttpStatus.OK);
	}
	
	

	/**
	 * 根据父菜单获取子菜单
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/{fatherId}/subcategories" },method=RequestMethod.GET)
	public ResponseEntity<List<Menu>> getSubCategores(@PathVariable("fatherId") Long fatherId){
		List<Menu> list = categoryLogic.getMenuByFatherId(fatherId);
		
		return new ResponseEntity<List<Menu>>(list, HttpStatus.OK);
	}
	
	
	/**
	 * 根据菜单级别获取菜单
	 * @param level
	 * @return ResponseEntity<List<Menu>>
	 */
	@RequestMapping(value = { "/level/{level}" },method=RequestMethod.GET)
	public ResponseEntity<List<Menu>> getLevel1Menu(@PathVariable("level") Integer level){
		List<Menu> list = categoryLogic.getMenuByLevelAndStatus(level, MenuRepository.MENU_TYPE_PRODUCT);
		return new ResponseEntity<List<Menu>>(list, HttpStatus.OK);
	}
	
	
	/**
	 * 根据菜单获取产品
	 * @param categoryId
	 * @param searchForm
	 * @return
	 */
	@RequestMapping(value="/{categoryId}/products",method=RequestMethod.GET)
	public ResponseEntity<Page<Product>> getProductByCategory(@PathVariable("categoryId") Long categoryId,
							@ModelAttribute(value = "searchForm") SearchForm searchForm
			) {
		Pageable pageable = new PageRequest(searchForm.getPage(),searchForm.getSize(),Direction.fromString(searchForm.getSort()),searchForm.getSortBy());
		Page<Product> productPage = productLogic.getProductByMenuId(categoryId,pageable);
		return new ResponseEntity<Page<Product>>(productPage,HttpStatus.OK);
	}
	
	
	
}
