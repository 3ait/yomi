package com.datas.easyorder.controller.administrator.content;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.category.ArticleEditView;
import com.datas.easyorder.controller.administrator.category.logic.AttrLogic;
import com.datas.easyorder.controller.administrator.category.logic.CategoryLogic;
import com.datas.easyorder.controller.administrator.content.logic.ContentLogic;
import com.datas.easyorder.controller.administrator.product.ProductAttrKeyValue;
import com.datas.easyorder.db.dao.MenuRepository;
import com.datas.easyorder.db.entity.Article;
import com.datas.easyorder.db.entity.Menu;
import com.datas.utils.SearchForm;
import com.plugin.utils.DateHelper;

@Controller
@RequestMapping("/management/content")
public class ContentController extends BaseController<Article>{

	@Autowired
	ContentLogic contentLogic;
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
	public ModelAndView index(@Valid @ModelAttribute(value = "searchForm") SearchForm searchForm,
			BindingResult bindingResult) {
		
		ModelAndView modelAndView = new ModelAndView("administrator/content/index");
		
		if (StringHelper.isEmpty(searchForm.getDateFrom())) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -36);
			searchForm.setDateFrom(DateHelper.getYYYYMMDD(calendar.getTime().getTime()));
		}
		if (StringHelper.isEmpty(searchForm.getDateTo())) {
			searchForm.setDateTo(DateHelper.getYYYYMMDD());
		}

		Pageable pageable = new PageRequest(searchForm.getPage()-1<1?0:searchForm.getPage()-1, searchForm.getSize(), Direction.fromString(searchForm.getSort()), searchForm.getSortBy());
		
		Page<Article> articlePage = contentLogic.articleSearch(searchForm, pageable);

		modelAndView.addObject("articleList", articlePage.getContent());

		super.setPage(modelAndView, articlePage, searchForm);
		//加载菜单
		modelAndView.addObject("menuList", categoryLogic.getMenuByLevel(1,MenuRepository.MENU_TYPE_ARTICLE,MenuRepository.MENU_TYPE_BRAND));
		modelAndView.addObject("leve2FatherId", 0);
		modelAndView.addObject("leve3FatherId", 0);
		
		
				
		return modelAndView;
	}
	
	/**
	 * new aritcle redirect
	 * @param level
	 * @param fatherId
	 * @return
	 */
	@RequestMapping(value = "/new")
	public ModelAndView newRedirect(){
		ModelAndView mv = new ModelAndView("administrator/content/new"); 
		//加载菜单
		mv.addObject("searchForm", new SearchForm());
		mv.addObject("menuList", categoryLogic.getMenuByLevel(1,MenuRepository.MENU_TYPE_ARTICLE,MenuRepository.MENU_TYPE_BRAND));
		mv.addObject("leve2FatherId", 0);
		mv.addObject("leve3FatherId", 0);
		
		//加载属性
		List<ProductAttrKeyValue> attrKeyValueList = attrLogic.getProductKeyValueList();
		mv.addObject("attrKeyValueList", attrKeyValueList);
		
		return mv;
	}
	
	/**
	 * new  aritcle save
	 * @param level
	 * @param fatherId
	 * @return
	 */
	@RequestMapping(value = "/new/save")
	public ModelAndView newSave(@ModelAttribute("articleForm") ArticleForm articleForm ){
		ModelAndView mv = new ModelAndView(new RedirectView("/management/content/new",true)); 
		
		contentLogic.newSave(articleForm);
		
		return mv;
	}
	
	/**
	 * edit redirect
	 * @param level
	 * @param fatherId
	 * @return
	 */
	@RequestMapping(value = "/edit/{articleId}")
	public ModelAndView edit(@PathVariable("articleId") Long articleId){
		ModelAndView mv = new ModelAndView("administrator/content/edit"); 
		
		
		
		//加载文章
		ArticleEditView articleEditView = contentLogic.getArticleEditViewById(articleId);
		mv.addObject("articleEditView", articleEditView);

		//加载菜单
		SearchForm searchForm = new SearchForm();
		if(articleEditView.getArticle().getMenu()!=null){
			Menu menu = categoryLogic.getMenuById(articleEditView.getArticle().getMenu().getId());
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
		mv.addObject("menuList", categoryLogic.getMenuByLevel(1,MenuRepository.MENU_TYPE_ARTICLE,MenuRepository.MENU_TYPE_BRAND));
		mv.addObject("leve2FatherId", 0);
		mv.addObject("leve3FatherId", 0);
		
		//加载属性
		List<ProductAttrKeyValue> attrKeyValueList = attrLogic.getProductKeyValueList();
		mv.addObject("attrKeyValueList", attrKeyValueList);
						
		
		return mv;
	}
	
	/**
	 * edit  product save
	 * @param level
	 * @param fatherId
	 * @return
	 */
	@RequestMapping(value = "/edit/save")
	public ModelAndView editSave(@ModelAttribute("articleForm") ArticleForm articleForm ){
		ModelAndView mv = new ModelAndView(new RedirectView("/management/content/edit/" + articleForm.getArticle().getId(),true)); 
		
		contentLogic.editSave(articleForm);
		
		return mv;
	}
	
	
	/**
	 * Delete  aritcle 
	 * @param level
	 * @param fatherId
	 * @return
	 */
	@RequestMapping(value = "/del/{articleId}")
	@ResponseBody
	public void del(@PathVariable("articleId") Long articleId ){
		contentLogic.delAritcleById(articleId);;
	}
	
	/**
	 * Delete  aritcle 
	 * @param level
	 * @param fatherId
	 * @return
	 */
	@RequestMapping(value = "/duplicate/{articleId}")
	@ResponseBody
	public void duplicate(@PathVariable("articleId") Long articleId ){
		contentLogic.duplicate(articleId);;
	}
	
	/**
	 * 更新value属性
	 */
	@RequestMapping("/update/colomn/")
	@ResponseBody
	public void valueUpdate(
			@RequestParam("pk") Long  pk, 
			@RequestParam("name") String name , 
			@RequestParam("value") String value ,
			HttpServletRequest request
			){
		contentLogic.update(pk,name,value);
	}
	
}
