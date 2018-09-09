package com.datas.easyorder.controller.web.brand;

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
import org.springframework.web.servlet.ModelAndView;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.category.logic.AttrLogic;
import com.datas.easyorder.controller.administrator.content.logic.ContentLogic;
import com.datas.easyorder.db.dao.ArticleRepository;
import com.datas.easyorder.db.entity.Article;
import com.datas.utils.SearchForm;

/**
 * 
 * @author leo
 *
 */
@RestController
@RequestMapping("/api/brand")
public class ApiWebBrandController extends BaseController{

	@Autowired
	AttrLogic attrLogic;
	@Autowired
	ContentLogic contentLogic;

	/**
	 * 获取所有品牌
	 * @param key
	 * @param searchForm
	 * @return
	 */
	@RequestMapping("/list")
	public HttpEntity<Page<Article>> getBrandList(@RequestParam(value="categoryId", defaultValue="-1",required = false) Long categoryId,@ModelAttribute("searchForm") SearchForm searchForm) {

		searchForm.setSortBy("position");
		Pageable pageable = new PageRequest(0, searchForm.getSize(),Direction.fromString(searchForm.getSort()),searchForm.getSortBy());
		
		Page<Article> articlePage = contentLogic.listAllBrand(ArticleRepository.status_active, pageable);

		return new ResponseEntity<Page<Article>>(articlePage, HttpStatus.OK);
	}
	
	/**
	 * 获取单个品牌
	 * @param key
	 * @param searchForm
	 * @return
	 */
	@RequestMapping("/{productAttrValueId}")
	public HttpEntity<Page<Article>> getBrandDetail(@PathVariable("productAttrValueId") Long productAttrValueId,@ModelAttribute("searchForm") SearchForm searchForm) {
		
		Pageable pageable = new PageRequest(0, searchForm.getSize(),Direction.fromString(searchForm.getSort()),searchForm.getSortBy());
		
		Page<Article> articlePage = contentLogic.getArticleByProductAttrValueId(productAttrValueId,ArticleRepository.status_active,pageable);
		
		return new ResponseEntity<Page<Article>>(articlePage, HttpStatus.OK);
	}	
	
	/**
	 * 获取推荐品牌
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return
	 */
	@RequestMapping(value=("/recommend/{categoryId}"))
	public HttpEntity<Page<Article>>  getRecommendArticle(@PathVariable Long categoryId,@ModelAttribute(value = "searchForm") SearchForm searchForm) {

		Pageable pageable = new PageRequest(searchForm.getPage()-1<1?0:searchForm.getPage()-1, searchForm.getSize(),Direction.fromString(searchForm.getSort()),searchForm.getSortBy());
		
		Page<Article> articlePage = contentLogic.getRecommendArticle(categoryId,ArticleRepository.recommend, ArticleRepository.status_active, pageable);

		return new ResponseEntity<Page<Article>>(articlePage, HttpStatus.OK);
	}
	
	/**
	 * 获取推荐品牌 Slider 图片
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return
	 */
	@RequestMapping(value=("/recommend/{categoryId}/slider"))
	public HttpEntity<Page<Article>>  getRecommendArticleSlider(@PathVariable Long categoryId,@ModelAttribute(value = "searchForm") SearchForm searchForm) {

		Pageable pageable = new PageRequest(searchForm.getPage()-1<1?0:searchForm.getPage()-1, searchForm.getSize(),Direction.fromString(searchForm.getSort()),searchForm.getSortBy());
		
		Page<Article> articlePage = contentLogic.getRecommendArticle(categoryId,ArticleRepository.recommend, ArticleRepository.status_active, pageable);

		return new ResponseEntity<Page<Article>>(articlePage, HttpStatus.OK);
	}
	
}
