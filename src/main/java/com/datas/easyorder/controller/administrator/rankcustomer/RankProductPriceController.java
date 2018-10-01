package com.datas.easyorder.controller.administrator.rankcustomer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.rankcustomer.logic.RankProductPriceLogic;
import com.datas.easyorder.db.entity.RankProductPrice;

/**
 * 
 * @author leo
 * Attachment management
 *
 */
@RestController
@RequestMapping("/management/rankproductprice")
public class RankProductPriceController extends BaseController<RankProductPrice> {

	
	@Autowired
	RankProductPriceLogic rankProductPriceLogic;

	
	/**
	 * 更新value属性
	 */
	@RequestMapping("/update/colomn/")
	@ResponseBody
	public void update(
			@RequestParam("pk") Long  pk, 
			@RequestParam("name") String name , 
			@RequestParam("value") String value ,
			HttpServletRequest request
			){
		rankProductPriceLogic.update(pk,name,value);
	}
	
	
}
