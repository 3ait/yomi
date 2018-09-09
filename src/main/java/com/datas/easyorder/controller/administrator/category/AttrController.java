package com.datas.easyorder.controller.administrator.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.category.logic.AttrLogic;

/**
 * 
 * @author leo
 *
 */
@RestController
@RequestMapping("/management/attribute")
public class AttrController extends BaseController {

	
	@Autowired
	AttrLogic attrLogic;
	
	/**
	 * list
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return
	 */
	@RequestMapping(value = { "", "/" })
	public ModelAndView index() {

		ModelAndView modelAndView = new ModelAndView("administrator/category/attr/index");

		modelAndView.addObject("productAttrKeyList", attrLogic.getProductAttrKeyList().getContent());
		return modelAndView;
	}

	/**
	 * new attrKey
	 * @param attrName
	 * @return
	 */
	@RequestMapping(value = {"/newkey" })
	public ModelAndView newAttrName(@RequestParam("attrKey") String attrKey) {

		ModelAndView modelAndView = new ModelAndView("administrator/category/attr/content :: attrKeys");

		attrLogic.newAttrKey(attrKey);
		modelAndView.addObject("productAttrKeyList", attrLogic.getProductAttrKeyList());
		return modelAndView;
	}
	
	
	/**
	 * load attrKey
	 * @param attrName
	 * @return
	 */
	@RequestMapping(value = {"/load/{attrKeyId}" })
	public ModelAndView loadByAttrId(@PathVariable("attrKeyId") Long attrKeyId) {

		ModelAndView modelAndView = new ModelAndView("administrator/category/attr/content :: attrValues");

		modelAndView.addObject("productAttrValueList", attrLogic.getValuesByAttrKeyId(attrKeyId));
		return modelAndView;
	}
	
	/**
	 * deleteAttrKey
	 * @param attrName
	 * @return
	 */
	@RequestMapping(value = {"/del/key/{attrKeyId}" })
	public ModelAndView deleteAttrKey(@PathVariable("attrKeyId") Long attrKeyId) {
		
		ModelAndView modelAndView = new ModelAndView("administrator/category/attr/content :: attrKeys");
		attrLogic.delAttrKey(attrKeyId);
		modelAndView.addObject("productAttrKeyList", attrLogic.getProductAttrKeyList());
		return modelAndView;
	}
	
	/**
	 * new attriValue
	 * @param attrName
	 * @return
	 */
	@RequestMapping(value = {"/newvalue" })
	public ModelAndView newAttrName(@RequestParam("attrkeyId") Long attrkeyId,@RequestParam("attrValue") String attrValue) {

		ModelAndView modelAndView = new ModelAndView("administrator/category/attr/content :: attrValues");

		attrLogic.newAttrValue(attrkeyId, attrValue);
		modelAndView.addObject("productAttrValueList", attrLogic.getValuesByAttrKeyId(attrkeyId));
		return modelAndView;
	}
	
	/**
	 * deleteAttrKey
	 * @param attrName
	 * @return
	 */
	@RequestMapping(value = {"/del/{attrKeyId}/{attrValueId}" })
	public ModelAndView deleteAttrValue(@PathVariable("attrKeyId") Long attrKeyId,@PathVariable("attrValueId") Long attrValueId) {
		
		ModelAndView modelAndView = new ModelAndView("administrator/category/attr/content :: attrValues");
		attrLogic.delAttrValue(attrValueId);
		modelAndView.addObject("productAttrValueList", attrLogic.getValuesByAttrKeyId(attrKeyId));
		return modelAndView;
	}
	
	
	/**
	 * 更新key属性
	 */
	@RequestMapping("/key/update/")
	public void keyUpate(
			@RequestParam("pk") Long  pk, 
			@RequestParam("name") String name , 
			@RequestParam("value") String value 
			){
		attrLogic.updateAttrKey(pk,name,value);
	}
	
	
	/**
	 * 更新value属性
	 */
	@RequestMapping("/value/update/")
	@ResponseBody
	public void valueUpdate(
			@RequestParam("pk") Long  pk, 
			@RequestParam("name") String name , 
			@RequestParam("value") String value 
			){
		attrLogic.updateAttrValue(pk,name,value);
	}
	

	/**
	 * 排序
	 */
	@RequestMapping("/key/sort")
	public void sort( @RequestParam("ids") String ids ){
		attrLogic.sort(ids);
	}
}
