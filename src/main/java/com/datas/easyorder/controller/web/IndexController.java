package com.datas.easyorder.controller.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.category.ArticleEditView;
import com.datas.easyorder.controller.administrator.category.logic.AttrLogic;
import com.datas.easyorder.controller.administrator.category.logic.CategoryLogic;
import com.datas.easyorder.controller.administrator.content.logic.ContentLogic;
import com.datas.easyorder.controller.administrator.customer.logic.CustomerLogic;
import com.datas.easyorder.controller.administrator.product.logic.ProductLogic;
import com.datas.easyorder.controller.web.index.MenuView;
import com.datas.easyorder.db.dao.UserCompanyRepository;
import com.datas.easyorder.db.entity.Customer;
import com.datas.easyorder.db.entity.UserCompany;
import com.plugin.mail.logic.SendMail;
import com.plugin.utils.Md5;

/**
 * 
 * @author leo
 *
 */
@RestController
@RequestMapping("/")
public class IndexController extends BaseController{

	@Autowired
	CategoryLogic categoryLogic;
	@Autowired
	ProductLogic productLogic;
	@Autowired
	ContentLogic contentLogic;
	@Autowired
	AttrLogic attrLogic;
	@Autowired
	SendMail sendMail;
	@Autowired
	UserCompanyRepository userCompanyRepository;
	@Autowired
	CustomerLogic customerLogic;
	
	@Value("${project.name}")
	String projectName;
	@Value("${index.slider}")
	public String indexSlider;
	
	
	/**
	 * 
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return
	 */
	@RequestMapping(value={""})
	public ModelAndView index() {
		
		ModelAndView modelAndView = new ModelAndView("web/index");
		
		
		Pageable pageable = new PageRequest(0, 10,Direction.ASC,"position");
		ArticleEditView articleEditView = contentLogic.getArtitleByUrlTitle(indexSlider, (byte)1, pageable);
		modelAndView.addObject("articleEditView",articleEditView);
		
		Pageable hotPageRequest = new PageRequest(0, 24,Direction.ASC,"position");
		Pageable frontPageRequest = new PageRequest(0, 24,Direction.ASC,"position");
		
		modelAndView.addObject("frontPageProductList",productLogic.getFrontPageProduct(hotPageRequest).getContent());
		modelAndView.addObject("PromoteProductList",productLogic.getPromoteProduct(frontPageRequest).getContent());
		
		
		return modelAndView;
	}
	
	/**
	 * 加载菜单
	 * @param searchForm
	 * @param bindingResult
	 * @param page
	 * @return
	 */
	@RequestMapping(value={"header/nav"})
	public ModelAndView getCagegory(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("web/fragment/f-header-nav :: header-nav");
		
		List<MenuView> menuViewList = (List<MenuView>)request.getSession().getAttribute(SESSIONO_TOP_MENU);
		if(menuViewList==null){
			menuViewList = categoryLogic.getWebTopIndexMenu(new ArrayList<>(),true);
			request.getSession().setAttribute(SESSIONO_TOP_MENU, menuViewList);
		}
		modelAndView.addObject(SESSIONO_TOP_MENU, menuViewList);
		Customer customer = (Customer)request.getSession().getAttribute(SESSION_CUSTOMER);
		modelAndView.addObject("customer",customer);
		return modelAndView;
	}
	
	/**
	 * 加载底部菜单
	 * @param searchForm
	 * @param bindingResult
	 * @param page
	 * @return
	 */
	@RequestMapping(value={"footer/nav"})
	public ModelAndView getFooterCategory(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("web/fragment/f-footer :: footer");
		
		List<MenuView> menuViewList = (List<MenuView>)request.getSession().getAttribute(SESSIONO_BOTTOM_MENU);
		if(menuViewList==null){
			menuViewList = categoryLogic.getWebBottomIndexMenu(new ArrayList<>(),true);
			request.getSession().setAttribute(SESSIONO_BOTTOM_MENU, menuViewList);
		}
		modelAndView.addObject(SESSIONO_BOTTOM_MENU, menuViewList);
		return modelAndView;
	}
	
	
	
	
	/**
	 * 加载验证用户是否登录
	 * @param searchForm
	 * @param bindingResult
	 * @param page
	 * @return ModelAndView
	 */
	@RequestMapping(value={"header/top"})
	public ModelAndView getCustomer(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("web/fragment/f-header-top :: header-top");
		
		Customer customer = (Customer)request.getSession().getAttribute(SESSION_CUSTOMER);
		modelAndView.addObject("customer",customer);
		
		return modelAndView;
	}
	
	/**
	 * 忘记密码
	 * @return
	 */
	@RequestMapping(value={"forgot/password"})
	public ModelAndView forgotPassword() {
		ModelAndView modelAndView = new ModelAndView("web/index/forgot-password");
		
		return modelAndView;
	} 
	
	/**
	 * 找回密码
	 * @return
	 */
	@RequestMapping(value={"forgot/password/retrieve/email"})
	@ResponseBody
	public Boolean retrieve(@RequestParam("email")String email) {
		
		boolean ret = false;
		UserCompany userCompany = userCompanyRepository.findAll().get(0);
		sendMail.init(userCompany, email);
		
		String newPassword = Md5.getMd5String(Calendar.getInstance().getTimeInMillis()+"").substring(0, 10);
		String content = projectName + " your new password:" + newPassword;
		try {
			ret = sendMail.send(projectName + "update password", content);
			if(ret){
				Customer customer = customerLogic.findByEmail(email);
				customerLogic.update(customer.getId(), "password", newPassword);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ret;
	} 
	
	/**
	 * 直播间
	 * 
	 */
	@RequestMapping(value={"/tv/show"})
	public ModelAndView tvShow() {
		ModelAndView modelAndView = new ModelAndView("web/index/tv");
		
		return modelAndView;
	}
	
	
}
