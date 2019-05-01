package com.datas.easyorder.controller.web.cart;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.annotations.common.util.StringHelper;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.db.entity.Customer;

/**
 * 
 * @author leo
 *
 */
@RestController
@RequestMapping("/cart")
public class CartController extends BaseController{



	
	/**
	 * 
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return
	 */
	@RequestMapping(value={"","/"})
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("web/cart/cart");
		modelAndView.addObject("customer",super.getLoginCustomer(request));	
		return modelAndView;
	}

	/**
	 * 获取关联词列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/s")
	@ResponseBody
	public String getQList(@RequestParam("q") String q, @RequestParam("size") int size) throws Exception {

		JSONObject strs = null;
		if (StringHelper.isNotEmpty(q)) {
//			strs = paymentLogic.search(q, size);
		} else {
			strs = new JSONObject();
		}
		return strs.toString();
	}

}
