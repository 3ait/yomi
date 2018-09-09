package com.datas.easyorder.controller.web.article;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.category.ArticleEditView;
import com.datas.easyorder.controller.administrator.content.logic.ContentLogic;
import com.datas.easyorder.db.entity.Article;
import com.datas.utils.SearchForm;

/**
 * 
 * @author leo
 *
 */
@Controller
@RequestMapping(value = {"/article"})
public class WebArticleController extends BaseController<Article> {

	@Autowired
	ContentLogic contentLogic;
	
	/**
	 * 
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return ModelAndView
	 */
	@RequestMapping(value=("/category/{categoryId}"))
	public ModelAndView categoryList(@PathVariable Long categoryId,@ModelAttribute(value = "searchForm") SearchForm searchForm) {

		ModelAndView modelAndView = new ModelAndView("web/article/list");
		
		searchForm.setSortBy("position");
		Pageable pageable = new PageRequest(0, searchForm.getSize(),Direction.fromString(searchForm.getSort()),searchForm.getSortBy());
		
		Page<Article> articlePage = contentLogic.listArticle(categoryId,(byte)1,pageable);

		modelAndView.addObject("articleList", articlePage.getContent());

		return modelAndView;
	}
	
	
	/**
	 * 
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return ModelAndView
	 */
	@RequestMapping(value=("/title/{categoryId}"))
	public ModelAndView categoryList(@PathVariable String urlTitle,@ModelAttribute(value = "searchForm") SearchForm searchForm) {

		ModelAndView modelAndView = new ModelAndView("web/article/list");
		
		Pageable pageable = new PageRequest(0, searchForm.getSize(),Direction.fromString(searchForm.getSort()),searchForm.getSortBy());
		
		ArticleEditView articleEditView = contentLogic.getArtitleByUrlTitle(urlTitle, (byte)1, pageable);

		modelAndView.addObject("article", articleEditView.getArticle().getContent());
		
		return modelAndView;
	}
	
	/**
	 * 根据Url title 获取文章组件跳转
	 * @return
	 */
	@RequestMapping(value=("/api/urlTitle/redirect"))
	public ModelAndView urlTitleDirect(){
		ModelAndView modelAndView = new ModelAndView("web/component/Article");
		return modelAndView;
	}
	
	/**
	 * 根据Url title 获取文章
	 * @return ResponseEntity<ArticleEditView>
	 */
	@RequestMapping("/api/urlTitle/{urlTitle}")
	public ResponseEntity<ArticleEditView> urlTitle(@PathVariable String urlTitle,@ModelAttribute(value = "searchForm") SearchForm searchForm) {
		Pageable pageable = new PageRequest(0, searchForm.getSize(),Direction.fromString(searchForm.getSort()),searchForm.getSortBy());
		
		ArticleEditView articleEditView = contentLogic.getArtitleByUrlTitle(urlTitle, (byte)1, pageable);
		return new ResponseEntity<ArticleEditView>(articleEditView, HttpStatus.OK);
	}
	
	/**
	 * 获取HOT的品牌
	 * @return ResponseEntity<ArticleEditView>
	 */
	@RequestMapping("/api/hot")
	public ResponseEntity<List<Article>> getHotArticle(@ModelAttribute(value = "searchForm") SearchForm searchForm) {
		Pageable pageable = new PageRequest(0, searchForm.getSize(),Direction.fromString(searchForm.getSort()),searchForm.getSortBy());
		
		List<Article> list = contentLogic.getHotArticle(pageable);
		return new ResponseEntity<List<Article>>(list, HttpStatus.OK);
	}
	
	/**
	 * 获取HOT的品牌
	 * @return ResponseEntity<ArticleEditView>
	 */
	@RequestMapping("/api/recommend")
	public ResponseEntity<List<Article>> getRecommendArticle(@ModelAttribute(value = "searchForm") SearchForm searchForm) {
		Pageable pageable = new PageRequest(0, searchForm.getSize(),Direction.fromString(searchForm.getSort()),searchForm.getSortBy());
		
		List<Article> list = contentLogic.getRecommendArticle(pageable);
		return new ResponseEntity<List<Article>>(list, HttpStatus.OK);
	}

}
