package com.datas.easyorder.controller.administrator.user.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class UserInterceptor implements HandlerInterceptor{

	
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
		/*boolean ret = false;
		
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute(BaseController.SESSION_USER);
		
		if(user!=null){
			ret = true;
		}else{
			response.sendRedirect(request.getContextPath()+"/management/");
		}*/
		
		return true;
	}


}
