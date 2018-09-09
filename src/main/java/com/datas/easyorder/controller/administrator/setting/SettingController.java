package com.datas.easyorder.controller.administrator.setting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.setting.logic.SettingLogic;
import com.datas.easyorder.db.entity.UserCompany;

/**
 * 
 * @author leo
 * System Config
 *
 */
@RestController
@RequestMapping("/management/setting")
public class SettingController extends BaseController{

	@Autowired
	private SettingLogic settingLogic;

	/**
	 * index
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return
	 */
	@RequestMapping(value={"","/"})
	public ModelAndView index() {
		
		ModelAndView modelAndView = new ModelAndView("administrator/setting/index");
		
		UserCompany userCompany = settingLogic.getUserCompany();
		
		modelAndView.addObject("userCompany", userCompany);
		return modelAndView;
	}

	/**
	 * save
	 * @return
	 */
	@RequestMapping(value={"/save"})
	public ModelAndView save(@ModelAttribute("userCompany") UserCompany userCompany) {
		
		ModelAndView modelAndView = new ModelAndView(new RedirectView("/management/setting",true));
		
		settingLogic.save(userCompany);
		return modelAndView;
	}

}
