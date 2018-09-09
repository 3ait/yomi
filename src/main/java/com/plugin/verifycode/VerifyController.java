package com.plugin.verifycode;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datas.easyorder.controller.BaseController;


/**
 * 验证码
 * @author yaoliang
 *
 */
@Controller
@RequestMapping("/verify")
public class VerifyController {

	
	private static Logger logger = LoggerFactory.getLogger(BaseController.class);
	int w = 90;
	int h = 35;
	
	
	@RequestMapping(value="/img",method=RequestMethod.GET)
	@ResponseBody
	public void getVerifyImg(@RequestParam(value="rd",required=false) String rd, HttpServletRequest request,HttpServletResponse response){
		//验证码
		logger.debug("getVerifyImg");
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		request.getSession().setAttribute("verifyCode", verifyCode);
		try {
			VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 验证码检查
	 * @param vCode
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/check")
	@ResponseBody
	public boolean check(@RequestParam(value = "vCode",required = true) String vCode, HttpServletRequest request){
		boolean ret = false;
		if(vCode.equalsIgnoreCase(request.getSession().getAttribute("verifyCode").toString())){
			ret = true;
		}
		return ret;
	}
}
