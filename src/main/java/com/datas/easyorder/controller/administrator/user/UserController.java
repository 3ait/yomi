package com.datas.easyorder.controller.administrator.user;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.user.logic.UserLogic;
import com.datas.easyorder.db.entity.User;
import com.datas.utils.SearchForm;
import com.plugin.utils.DateHelper;

/**
 * 
 * @author leo
 *
 */
@RestController
@RequestMapping("/management/user")
public class UserController extends BaseController<User>{

	@Autowired
	private UserLogic userLogic;

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
		
		ModelAndView modelAndView = new ModelAndView("administrator/user/index");
		
		
		if (StringHelper.isEmpty(searchForm.getDateFrom())) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -36);
			searchForm.setDateFrom(DateHelper.getYYYYMMDD(calendar.getTime().getTime()));
		}
		if (StringHelper.isEmpty(searchForm.getDateTo())) {
			searchForm.setDateTo(DateHelper.getYYYYMMDD());
		}

		String[] sortBy = {"id","status"};
		Pageable pageable = new PageRequest(searchForm.getPage()-1<1?0:searchForm.getPage()-1, searchForm.getSize(), Direction.DESC,sortBy);
		
		Page<User> list = userLogic.findAll(searchForm,pageable);

		modelAndView.addObject("userList", list.getContent());
		super.setPage(modelAndView, list, searchForm);
		return modelAndView;
	}
	
	/**
	 * 增加用户跳转
	 */
	@RequestMapping("/new")
	public ModelAndView newUser(){
		ModelAndView modelAndView = new ModelAndView("administrator/user/new");
		return modelAndView;
	}
	
	/**
	 * 增加用户保存
	 */
	@RequestMapping("/new/save")
	public ModelAndView saveUser(@ModelAttribute("userView") UserView userview, HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView(new RedirectView("/management/user/",true));
		User sessionUser = super.getLognUser();
		userview.setUserCompany(sessionUser.getUserCompany());
		userview.setUserGroup(sessionUser.getUserGroup());
		userLogic.saveUser(userview);
		return modelAndView;
	}
	
	/**
	 * email check 
	 * @return String
	 */
	@RequestMapping(value={"/email/check"})
	@ResponseBody
	public String checkEmail(@RequestParam(value="email",required=true) String email) {
		Boolean ret = true;
		User user = userLogic.findByEmail(email);
		if(user!=null){
			ret = false;
		}
		
		return ret.toString();
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
		userLogic.update(pk, name, value);
	}

}
