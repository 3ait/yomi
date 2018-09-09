package com.datas.easyorder.controller.mobile.my.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.datas.easyorder.controller.BaseLogic;

/**
 * token interceptor
 * @author leo
 *
 */
public class MobileTokenInterceptor implements HandlerInterceptor{

	public final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MobileTokenInterceptor.class);
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
		String token = request.getHeader("Authorization");
		logger.info("token=" + token);
		try {
			if(JwtToken.verifyToken(token).get("userId")!=null){
				ret = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpStatus.FORBIDDEN.value());
		}
		
		return ret;
	}


}
