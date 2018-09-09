package com.datas.easyorder.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.datas.easyorder.db.dao.UserRepository;
import com.datas.easyorder.db.entity.User;
import com.datas.utils.SearchForm;
import com.payment.iemoney.IEMoney;
import com.spring.security.MyUserDetails;

@Controller
public abstract class BaseController<T> {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	public IEMoney iEMoney;
	
	public static final String SESSION_CUSTOMER = "sessionCustomer";
	public static final String SESSIONO_TOP_MENU = "topMenuViewList";
	public static final String SESSIONO_BOTTOM_MENU = "bottomMenuViewList";
	

	@Transactional(rollbackFor=Exception.class)
	public User getLognUser(){
		MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userRepository.findOne(userDetails.getId());
	}
	
	public Double getRate(HttpServletRequest request){
		Double rate = (Double)request.getSession().getAttribute("rate");
		if(rate==null){
			rate = iEMoney.getRate();
			request.getSession().setAttribute("rate", rate);
		}
		
		return rate;
	}
	
	
	/**
	 * 设置翻页
	 * @param modelAndView
	 * @param page
	 */
	public void setPage(ModelAndView modelAndView, Page<T> page, SearchForm searchForm){
		modelAndView.addObject("totalPages", page.getTotalPages());
		modelAndView.addObject("number", searchForm.getPage() > page.getTotalPages() ? 0 : page.getNumber() + 1);
		modelAndView.addObject("totalNum", page.getTotalElements());
		modelAndView.addObject("searchForm", searchForm);
	}
	
}
