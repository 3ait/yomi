package com.datas.easyorder.controller.mobile.attribute;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.datas.easyorder.controller.administrator.category.logic.AttrLogic;
import com.datas.easyorder.controller.administrator.content.logic.ContentLogic;
import com.datas.easyorder.controller.administrator.product.logic.ProductLogic;
import com.datas.easyorder.db.dao.ArticleRepository;
import com.datas.easyorder.db.entity.Article;
import com.datas.easyorder.db.entity.Product;
import com.datas.utils.SearchForm;

/**
 * 
 * @author leo
 *
 */
@RestController
@RequestMapping("/m/api/attributes")
public class MobileAttributeController{

	
	@Autowired
	AttrLogic attrLogic;
	@Autowired	
	ContentLogic contentLogic;
	@Autowired
	ProductLogic productLogic;
	

	
	/**
	 * 获取所有品牌
	 * @param key
	 * @param searchForm
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET)
	public HttpEntity<Page<Article>> getBrandList(@ModelAttribute("searchForm") SearchForm searchForm) {

		Pageable pageable = new PageRequest(searchForm.getPage()-1<1?0:searchForm.getPage()-1, searchForm.getSize(),Direction.fromString(searchForm.getSort()),searchForm.getSortBy());

		Page<Article> articlePage = contentLogic.listAllBrand(ArticleRepository.status_active,pageable);

		return new ResponseEntity<Page<Article>>(articlePage, HttpStatus.OK);
	}
	
	/**
	 * 获取单个品牌
	 * @param key
	 * @param searchForm
	 * @return
	 */
	@RequestMapping(value="/{productAttrValueId}",method=RequestMethod.GET)
	public HttpEntity<Page<Article>> getBrandDetail(@PathVariable("productAttrValueId") Long productAttrValueId,@ModelAttribute("searchForm") SearchForm searchForm) {
		
		Pageable pageable = new PageRequest(0, searchForm.getSize(),Direction.fromString(searchForm.getSort()),searchForm.getSortBy());
		
		Page<Article> articlePage = contentLogic.getArticleByProductAttrValueId(productAttrValueId,ArticleRepository.status_active,pageable);
		
		return new ResponseEntity<Page<Article>>(articlePage, HttpStatus.OK);
	}	
	
	/**
	 * 根据productAttrValueId 获取品牌下的所有产品
	 * @param key
	 * @param searchForm
	 * @return
	 */
	@RequestMapping("/{productAttrValueId}/products")
	public HttpEntity<Page<Product>> getProductByProductAttrValueId( @PathVariable("productAttrValueId") Long productAttrValueId,
			@ModelAttribute("searchForm")  SearchForm searchForm) {
		
		Pageable pageable = new PageRequest(searchForm.getPage(), searchForm.getSize(), Direction.fromString(searchForm.getSort()), searchForm.getSortBy());

		Page<Product> page = productLogic.getProductByAttrValueId(productAttrValueId, pageable);
		return new ResponseEntity<Page<Product>>(page, HttpStatus.OK);
	}	
}
