package com.datas.easyorder.controller.web.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.category.logic.CategoryLogic;
import com.datas.easyorder.controller.administrator.content.logic.ContentLogic;
import com.datas.easyorder.db.entity.Article;
import com.datas.utils.SearchForm;

/**
 * 
 * @author leo
 *
 */
@Controller
@RequestMapping(value = {"/brand"})
public class WebBrandController extends BaseController<Article> {

	@Autowired
	ContentLogic contentLogic;
	@Autowired
	CategoryLogic categoryLogic;
	
	/**
	 * 获取分类下的所有品牌
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return
	 */
	@RequestMapping(value=("/category/{categoryId}"))
	public ModelAndView categoryList(@PathVariable Long categoryId,@ModelAttribute(value = "searchForm") SearchForm searchForm) {

		ModelAndView modelAndView = new ModelAndView("web/brand/list");
		
		
		modelAndView.addObject("category", categoryLogic.getMenuById(categoryId));
		modelAndView.addObject("subCategoryList", categoryLogic.getMenuByFatherId(categoryId));
		
		return modelAndView;
	}

	
	/**
	 * 
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return
	 */
	@RequestMapping(value=("/{productAttrValueId}"))
	public ModelAndView categoryList(@PathVariable("productAttrValueId") Long productAttrValueId) {

		ModelAndView modelAndView = new ModelAndView("web/brand/detail");
		modelAndView.addObject("productAttrValueId", productAttrValueId);
		
		return modelAndView;
	}

}
