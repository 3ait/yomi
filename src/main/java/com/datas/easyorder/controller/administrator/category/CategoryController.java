package com.datas.easyorder.controller.administrator.category;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.category.logic.CategoryLogic;

/**
 * 
 * @author leo
 *
 */
@RestController
@RequestMapping("/management/category")
public class CategoryController extends BaseController {

	
	@Autowired
	CategoryLogic categoryLogic;
	
	/**
	 * 
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return
	 */
	@RequestMapping(value = { "", "/" })
	public ModelAndView index() {

		ModelAndView modelAndView = new ModelAndView("administrator/category/index");

		modelAndView.addObject("menuList", categoryLogic.getMenuByLevel(1,new String[]{}));
		modelAndView.addObject("leve2FatherId", 0);
		modelAndView.addObject("leve3FatherId", 0);
		return modelAndView;
	}

	/**
	 * 增加新菜单
	 * @param nameEn
	 * @param fatherId
	 * @param level
	 * @return
	 */
	@RequestMapping(value = {"/save" })
	public ModelAndView newCagetory(
			@RequestParam(defaultValue = "", required = true) String nameEn,
			@RequestParam(defaultValue = "0", required = false) Long fatherId,
			@RequestParam(defaultValue = "product", required = false) String menuType,
			@RequestParam(defaultValue = "1", required = false) Integer level){
		ModelAndView mv = new ModelAndView("administrator/category/fragment/f-content :: categoryList"+level);
		
		categoryLogic.newMenu(nameEn, level, fatherId,menuType);
		if(fatherId==0){
			mv.addObject("menuList",  categoryLogic.getMenuByLevel(1,null));
		}else{
			mv.addObject("menuList", categoryLogic.getMenuByFatherId(fatherId));
		}
		
		return mv;
	}
	
	
	/**
	 * load新菜单
	 * @param level
	 * @param fatherId
	 * @return
	 */
	@RequestMapping(value = "/load/{level}/{fatherId}")
	public ModelAndView newCagetory(@PathVariable("level") Long level , @PathVariable("fatherId") Long fatherId ){
		ModelAndView mv = new ModelAndView("administrator/category/fragment/f-content :: categoryList"+level);
		
		mv.addObject("menuList", categoryLogic.getMenuByFatherId(fatherId));
		
		if(level==2){
			mv.addObject("leve2FatherId", fatherId);
		}else if(level==3){
			mv.addObject("leve3FatherId", fatherId);
		}
		mv.addObject("level",level);
		return mv;
	}
	
	
	/**
	 * 删除菜单
	 * @param menuId
	 * @param level
	 * @param fatherId
	 * @return
	 */
	@RequestMapping(value = "/del/")
	public ModelAndView delCagetory(
			@RequestParam("menuId") Long menuId,
			@RequestParam("level") Integer level,
			@RequestParam(defaultValue = "0", required = false) Long fatherId
			){
		
		ModelAndView mv = new ModelAndView("administrator/category/fragment/f-content :: categoryList"+level);
		
		categoryLogic.delMenuById(menuId);
		
		if(fatherId==0){
			mv.addObject("menuList",  categoryLogic.getMenuByLevel(1,null));
		}else{
			mv.addObject("menuList", categoryLogic.getMenuByFatherId(fatherId));
		}
		
		return mv;
	}
	
	/**
	 * 更新一个属性
	 */
	@RequestMapping("/update/")
	public void updateAttribute(
			@RequestParam("pk") Long  cateoryId, 
			@RequestParam("name") String name , 
			@RequestParam("value") String value 
			){
		categoryLogic.update(cateoryId,name,value);
	}
	
	
	/**
	 * 排序
	 */
	@RequestMapping("/sort")
	public void sort( @RequestParam("ids") String ids ){
		categoryLogic.sort(ids);
	}
	
	/**
	 * 刷新前端缓存
	 * @param request
	 */
	@RequestMapping(value={"/refresh"})
	public ModelAndView refresh(HttpServletRequest request){
		request.getSession().removeAttribute(SESSIONO_TOP_MENU);
		request.getSession().removeAttribute(SESSIONO_BOTTOM_MENU);
		return new ModelAndView(new RedirectView("/management/category/", true));
	}
	
}
