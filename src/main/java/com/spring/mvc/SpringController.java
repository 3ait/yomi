package com.spring.mvc;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * web index
 * 
 * @author yaoliang 24-12-2015
 */
@Controller
public class SpringController {

	Logger logger = LogManager.getLogger(SpringController.class);


	
	/**
	 * 退出
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/logout")
	public ModelAndView logout() throws IOException{
		return new ModelAndView(new RedirectView("/management", true));
	}
	
	@RequestMapping("/403")
	public ModelAndView forbidden() {
		logger.info("403");
		return new ModelAndView("/error/403");
	}
	@RequestMapping("/404")
	public ModelAndView error404() {
		logger.info("404");
		return new ModelAndView(new RedirectView("/management", true));
	}
	
}
