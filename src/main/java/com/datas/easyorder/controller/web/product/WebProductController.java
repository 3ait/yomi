package com.datas.easyorder.controller.web.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.category.logic.AttrLogic;
import com.datas.easyorder.controller.administrator.category.logic.CategoryLogic;
import com.datas.easyorder.controller.administrator.product.logic.ProductLogic;
import com.datas.easyorder.controller.administrator.product.view.ProductAttrKeyValue;
import com.datas.easyorder.controller.administrator.product.view.ProductEditView;
import com.datas.easyorder.db.entity.Product;
import com.datas.utils.SearchForm;

/**
 * 
 * @author leo
 *
 */
@RestController
@RequestMapping("/product")
public class WebProductController extends BaseController<Product>{

	@Autowired
	ProductLogic productLogic;
	@Autowired
	AttrLogic attrLogic;
	@Autowired
	CategoryLogic categoryLogic;
	
	/**
	 * 根据菜单ID获取产品列表
	 * @param categoryId
	 * @param page
	 * @param attrValueIds
	 * @param isAttr 是否是属性查询
	 * @return ModelAndView
	 */ 
	@RequestMapping(value=("/category/{categoryId}"))
	public ModelAndView products(@PathVariable("categoryId") Long categoryId) {
		
		ModelAndView modelAndView = new ModelAndView("web/product/product-list");
		
		modelAndView.addObject("category", categoryLogic.getMenuById(categoryId));
		modelAndView.addObject("subCategoryList", categoryLogic.getMenuByFatherId(categoryId));
		
		modelAndView.addObject("searchForm", new SearchForm());
		return modelAndView;
	}
	
	
	
	/**
	 * 根据左侧列表过滤产品
	 * @param categoryId
	 * @param page
	 * @param attrValueIds
	 * @param isAttr
	 * @return ModelAndView
	 */
	@RequestMapping(value=("/attr"))
	public ModelAndView getProdcutByAttr(
			@RequestParam(value="categoryId", defaultValue="-1",required = false) Long categoryId,
			@ModelAttribute SearchForm searchform,
			@RequestParam(value="attrValueIds[]", required = false) Long[] attrValueIds
			) {
		
		ModelAndView modelAndView = new ModelAndView("web/product/fragment/list-content-right :: right");
		
		
		searchform.setSortBy("productName");
		searchform.setSort("ASC");
		Pageable pageable = new PageRequest(searchform.getPage()-1<1?0:searchform.getPage()-1, searchform.getSize(),Direction.fromString(searchform.getSort()),searchform.getSortBy());

		
		Page<Product> productPage = null;
		
		if(categoryId>0){
			productPage = productLogic.searchProductByAttrAndCategory(categoryId, attrValueIds, pageable);
		}
		
		if(searchform.getQ() != null && searchform.getQ().length() > 0){
			productPage = productLogic.searchProductByAttrAndQ(searchform.getQ(), attrValueIds, pageable);
		}
		
		modelAndView.addObject("productList", productPage.getContent());
		
		super.setPage(modelAndView, productPage, searchform);
		
		
		
		modelAndView.addObject("q", searchform.getQ());
		modelAndView.addObject("categoryId", categoryId);
		
		modelAndView.addObject("sortBy", searchform.getSortBy());
		modelAndView.addObject("sort", searchform.getSort());
		
		
		return modelAndView;
	}
	
	/**
	 * search
	 * @param q
	 * @param page
	 * @param attrValueIds
	 * @return
	 */
	@RequestMapping(value=("/q"))
	public ModelAndView q(
			@ModelAttribute SearchForm searchform
			) {
		
		ModelAndView modelAndView = new ModelAndView("web/product/product-list");
		
		modelAndView.addObject("categoryId", -1);

		
		return modelAndView;
	}

	/**
	 * 
	 * 获取列表左侧属性选项
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return
	 */
	@RequestMapping(value=("/left/attr/"))
	public ModelAndView productAttr(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("web/product/fragment/list-content-left :: left");
		
		
		List<ProductAttrKeyValue> attrKeyValueList = (List<ProductAttrKeyValue>)request.getSession().getAttribute("attrKeyValueList");
		if(attrKeyValueList!=null){
			modelAndView.addObject("attrKeyValueList",attrKeyValueList);
		}else{
 			attrKeyValueList = attrLogic.getProductKeyValueList();
			modelAndView.addObject("attrKeyValueList", attrKeyValueList);
			request.getSession().setAttribute("attrKeyValueList", attrKeyValueList);
		}
		
		return modelAndView;
	}
	
	
	/**
	 * 获取产品明细
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return
	 */
	@RequestMapping(value="/{productId}")
	public ModelAndView getProductById(@PathVariable(value="productId") Long productId,HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("web/product/product-detail");
		
		ProductEditView productEditView = productLogic.getSingleProductCustomerPrice(productLogic.getProdcutViewById(productId),super.getLoginCustomer(request));
		modelAndView.addObject("productEditView",productEditView);
		modelAndView.addObject("rate",super.getRate(request));
		
		return modelAndView;
	}
	
	/**
	 * 特别产品跳转
	 * @param coloumName
	 * @param sortBy
	 * @param order
	 * @param page
	 * @return
	 */
	@RequestMapping(value=("/list/{coloumName}"))
	public ModelAndView list(
			@PathVariable(value="coloumName") String coloumName
			) {
		ModelAndView modelAndView = new ModelAndView("web/product/special-product-list");
		modelAndView.addObject("coloumName", coloumName);
		modelAndView.addObject("number", 1);
		return modelAndView;
		
	}
	
	
	/**
	 * get hot,promote,front-page,recommend
	 * @param q
	 * @param page
	 * @param attrValueIds
	 * @return
	 */
	@RequestMapping(value=("/list/get/{coloumName}"))
	public ModelAndView specialProduct(
			@PathVariable(value="coloumName") String coloumName,
			@ModelAttribute SearchForm searchform,
			@RequestParam(value="attrValueIds[]", required = false) Long[] attrValueIds
			) {
		
		ModelAndView modelAndView = new ModelAndView("web/product/fragment/specail-list-content-right :: right");
		
		//productListPageSize
		searchform.setSortBy("productName");
		searchform.setSort("ASC");
		Pageable pageable = new PageRequest(searchform.getPage()-1<1?0:searchform.getPage()-1, searchform.getSize(),Direction.fromString(searchform.getSort()),searchform.getSortBy());

		
		SearchForm searchForm = new SearchForm();
		searchForm.setStatus(0);
		
		Page<Product> productPage = null;
		if(coloumName.equals("new-arrival")){
			productPage = productLogic.getNewArrivalProdcutsByAttrId(attrValueIds, pageable);
		}else if(coloumName.equals("promote")){
			productPage = productLogic.getPromoteProdcutsByAttrId(attrValueIds, pageable);
		}else if(coloumName.equals("specials")){
			productPage = productLogic.getSpecialProdcutsByAttrId(attrValueIds, pageable);
		}
		
		modelAndView.addObject("coloumName", coloumName);
		modelAndView.addObject("productList", productPage.getContent());
		
		super.setPage(modelAndView, productPage, searchForm);
		

		return modelAndView;
	}
	
	
}
