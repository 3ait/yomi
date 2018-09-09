package com.datas.easyorder.controller.web.customer.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.db.entity.Customer;

public class CustomerInterceptor implements HandlerInterceptor{

	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object object) throws Exception {
		boolean ret = false;
		
		HttpSession session = request.getSession(true);
		Customer customer = (Customer) session.getAttribute(BaseController.SESSION_CUSTOMER);
		
		if(customer!=null){
			ret = true;
		}else{
			response.sendRedirect(request.getContextPath()+"/customer");
		}
		
		return ret;
	}


}
