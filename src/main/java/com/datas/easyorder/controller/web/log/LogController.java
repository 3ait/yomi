package com.datas.easyorder.controller.web.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.report.logic.ReportLogic;

/**
 * 
 * @author leo
 *
 */
@RestController
@RequestMapping("/log")
public class LogController extends BaseController{

	@Autowired
	ReportLogic reportLogic;
	
	@RequestMapping("/pv/save")
	@ResponseBody
	public void paveViewSave(){
		reportLogic.savePageView();
	}
	
}
