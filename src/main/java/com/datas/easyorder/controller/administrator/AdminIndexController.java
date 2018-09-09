package com.datas.easyorder.controller.administrator;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.datas.easyorder.controller.administrator.user.logic.UserLogic;

/**
 * 
 * @author leo
 *
 */
@RestController
public class AdminIndexController{

	private static Logger logger = LogManager.getLogger(AdminIndexController.class);
	
	@Autowired
	UserLogic userLogic;
	
	/**
	 * 
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return
	 */
	@RequestMapping(value={"/management","/management/"})
	public ModelAndView index(HttpServletRequest request) {
		
		logger.debug("index");
		ModelAndView modelAndView = new ModelAndView("administrator/index");
		return modelAndView;
	}


}
