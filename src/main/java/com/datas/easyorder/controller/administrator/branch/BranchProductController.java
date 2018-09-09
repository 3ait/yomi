package com.datas.easyorder.controller.administrator.branch;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.branch.logic.BranchProductLogic;
import com.datas.easyorder.db.entity.BranchProduct;

/**
 * 
 * @author leo
 * Attachment management
 *
 */
@RestController
@RequestMapping("/management/branchproduct")
public class BranchProductController extends BaseController<BranchProduct> {

	
	@Autowired
	BranchProductLogic branchProductLogic;

	
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
		branchProductLogic.update(pk,name,value);
	}
	
	
}
