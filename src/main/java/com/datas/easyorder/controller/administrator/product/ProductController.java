package com.datas.easyorder.controller.administrator.product;

import java.util.Calendar;
import java.util.List;

import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.category.logic.AttrLogic;
import com.datas.easyorder.controller.administrator.category.logic.CategoryLogic;
import com.datas.easyorder.controller.administrator.product.logic.ProductLogic;
import com.datas.easyorder.controller.administrator.product.view.ProductAttrKeyValue;
import com.datas.easyorder.controller.administrator.product.view.ProductEditView;
import com.datas.easyorder.controller.administrator.product.view.ProductForm;
import com.datas.easyorder.db.dao.MenuRepository;
import com.datas.easyorder.db.entity.Menu;
import com.datas.easyorder.db.entity.Product;
import com.datas.utils.SearchForm;
import com.plugin.utils.DateHelper;

/**
 * 	
 * @author leo
 *
 */
@RestController
@RequestMapping("/management/product")
public class ProductController extends BaseController<Product>{

	@Autowired 
	ProductLogic productLogic;
	@Autowired 
	CategoryLogic categoryLogic;
	@Autowired
	AttrLogic attrLogic;
	
	/**
	 * 
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return
	 */
	@RequestMapping(value={"","/"})
	public ModelAndView index(@ModelAttribute(value = "searchForm") SearchForm searchForm) {
		
		ModelAndView modelAndView = new ModelAndView("administrator/product/index");
		
		if (StringHelper.isEmpty(searchForm.getDateFrom())) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -36);
			searchForm.setDateFrom(DateHelper.getYYYYMMDD(calendar.getTime().getTime()));
		}
		if (StringHelper.isEmpty(searchForm.getDateTo())) {
			searchForm.setDateTo(DateHelper.getYYYYMMDD());
		}

		Pageable pageable = new PageRequest(searchForm.getPage()-1<1?0:searchForm.getPage()-1, searchForm.getSize(),Direction.fromString(searchForm.getSort()),searchForm.getSortBy());
		
		Page<Product> productPage = productLogic.productSearch(searchForm, pageable);

		modelAndView.addObject("productList", productPage.getContent());
		//翻页
		super.setPage(modelAndView, productPage, searchForm);
		
		//加载菜单
		modelAndView.addObject("menuList", categoryLogic.getMenuByLevel(1,MenuRepository.MENU_TYPE_PRODUCT));
		modelAndView.addObject("leve2FatherId", 0);
		modelAndView.addObject("leve3FatherId", 0);
		modelAndView.addObject("user", getLognUser());
		//库存
		return modelAndView;
	}
	
	
	/**
	 * loadCagetory
	 * @param level
	 * @param fatherId
	 * @return
	 */
	@RequestMapping(value = "/category/load/{level}/{fatherId}")
	public ModelAndView loadCagetory(@PathVariable("level") Long level , @PathVariable("fatherId") Long fatherId ){
		ModelAndView mv = new ModelAndView("administrator/product/fragment/category-select :: menuDiv"+level);
		
		mv.addObject("menuList", categoryLogic.getMenuByFatherId(fatherId));
		SearchForm searchForm = new SearchForm();
		if(level==2){
			mv.addObject("leve2FatherId", fatherId);
			searchForm.setMenu2Id(fatherId);
		}else if(level==3){
			mv.addObject("leve3FatherId", fatherId);
			searchForm.setMenu3Id(fatherId);
		}
		mv.addObject("searchForm", searchForm);
		return mv;
	}
	
	/**
	 * new product redirect
	 * @param level
	 * @param fatherId
	 * @return
	 */
	@RequestMapping(value = "/new")
	public ModelAndView newRedirect(){
		ModelAndView mv = new ModelAndView("administrator/product/new"); 
		//加载菜单
		mv.addObject("searchForm", new SearchForm());
		mv.addObject("menuList", categoryLogic.getMenuByLevel(1,MenuRepository.MENU_TYPE_PRODUCT));
		mv.addObject("leve2FatherId", 0);
		mv.addObject("leve3FatherId", 0);
		
		//加载属性
		List<ProductAttrKeyValue> attrKeyValueList = attrLogic.getProductKeyValueList();
		mv.addObject("attrKeyValueList", attrKeyValueList);
		
		return mv;
	}
	
	/**
	 * new  product save
	 * @param level
	 * @param fatherId
	 * @return
	 */
	@RequestMapping(value = "/new/save")
	public ModelAndView newSave(@ModelAttribute("productForm") ProductForm productForm ){
		ModelAndView mv = new ModelAndView(new RedirectView("/management/product/new",true)); 
		
		productLogic.newSave(productForm);
		
		return mv;
	}
	
	
	/**
	 * edit redirect
	 * @param level
	 * @param fatherId
	 * @return
	 */
	@RequestMapping(value = "/edit/{productId}")
	public ModelAndView edit(@PathVariable("productId") Long productId){
		ModelAndView mv = new ModelAndView("administrator/product/edit"); 
		
		
		//加载属性
		List<ProductAttrKeyValue> attrKeyValueList = attrLogic.getProductKeyValueList();
		mv.addObject("attrKeyValueList", attrKeyValueList);
		
		
		//加载产品数据
		ProductEditView productEditView = productLogic.getProductEditView(productId);
		mv.addObject("productEditView", productEditView);

		//加载菜单
		SearchForm searchForm = new SearchForm();
		if(productEditView.getProduct().getMenu()!=null){
			Menu menu = categoryLogic.getMenuById(productEditView.getProduct().getMenu().getId());
			if(menu.getLevel()==1){
				searchForm.setMenu1Id(menu.getId());
			}else if(menu.getLevel()==2){
				searchForm.setMenu1Id(menu.getMenu().getId());
				searchForm.setMenu2Id(menu.getId());
			}else if(menu.getLevel()==3){
				searchForm.setMenu1Id(menu.getMenu().getMenu().getId());
				searchForm.setMenu2Id(menu.getMenu().getId());
				searchForm.setMenu3Id(menu.getId());
			}
		}
		
		mv.addObject("searchForm", searchForm);
		mv.addObject("menuList", categoryLogic.getMenuByLevel(1,MenuRepository.MENU_TYPE_PRODUCT));
		mv.addObject("leve2FatherId", 0);
		mv.addObject("leve3FatherId", 0);
		mv.addObject("user", getLognUser());
		return mv;
	}
	
	/**
	 * edit  product save
	 * @param level
	 * @param fatherId
	 * @return
	 */
	@RequestMapping(value = "/edit/save")
	public ModelAndView editSave(@ModelAttribute("productForm") ProductForm productForm ){
		ModelAndView mv = new ModelAndView(new RedirectView("/management/product/edit/" + productForm.getProduct().getId(),true)); 
		
		productLogic.editSave(productForm);
		
		return mv;
	}
	
	
	/**
	 * 更新value属性
	 */
	@RequestMapping("/update/colomn/")
	@ResponseBody
	public void valueUpdate(
			@RequestParam("pk") Long  pk, 
			@RequestParam("name") String name , 
			@RequestParam("value") String value 
			){
		productLogic.update(pk,name,value);
	}
	
	/**
	 * Delete  product 
	 * @param level
	 * @param fatherId
	 * @return
	 */
	@RequestMapping(value = "/del/{productId}")
	@ResponseBody
	public void del(@PathVariable("productId") Long productId ){
		productLogic.delProductById(productId);;
	}
	
	/**
	 * duplicate product
	 * @param productId
	 */
	@RequestMapping(value = "/duplicate/{productId}")
	@ResponseBody
	public void duplicate(@PathVariable("productId") Long productId ){
		productLogic.duplicate(productId);
	}
}
