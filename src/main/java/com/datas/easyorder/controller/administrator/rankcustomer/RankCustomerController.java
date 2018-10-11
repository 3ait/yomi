package com.datas.easyorder.controller.administrator.rankcustomer;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.rankcustomer.logic.RankCustomerLogic;
import com.datas.easyorder.db.entity.RankCustomer;
import com.datas.utils.SearchForm;
import com.plugin.utils.DateHelper;

/**
 * 
 * @author leo
 *
 */
@RestController
@RequestMapping("/management/rankcustomer")
public class RankCustomerController extends BaseController<RankCustomer>{

	@Autowired
	private RankCustomerLogic rankCustomerLogic;
	
	/**
	 * 获取所有Customer
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return
	 */
	@RequestMapping(value={"","/"})
	public ModelAndView index(@ModelAttribute(value = "searchForm") SearchForm searchForm) {
		
		ModelAndView modelAndView = new ModelAndView("administrator/rankcustomer/index");
		
		if (StringHelper.isEmpty(searchForm.getDateFrom())) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -12);
			searchForm.setDateFrom(DateHelper.getYYYYMMDD(calendar.getTime().getTime()));
		}
		
		if (StringHelper.isEmpty(searchForm.getDateTo())) {
			searchForm.setDateTo(DateHelper.getYYYYMMDD());
		}

		Pageable pageable = new PageRequest(searchForm.getPage()-1<1?0:searchForm.getPage()-1, searchForm.getSize(), Direction.fromString(searchForm.getSort()), searchForm.getSortBy());
		
		Page<RankCustomer> list = rankCustomerLogic.findAll(pageable);

		modelAndView.addObject("rankCustomerList", list.getContent());
		super.setPage(modelAndView, list, searchForm);
		return modelAndView;
	}
	
	/**
	 * 更新value属性
	 * @throws Exception 
	 */
	@RequestMapping("/update/colomn/")
	@ResponseBody
	public void valueUpdate(
			@RequestParam("pk") Long  pk, 
			@RequestParam("name") String name , 
			@RequestParam("value") String value ,
			HttpServletRequest request
			) throws Exception{
		
		rankCustomerLogic.update(pk,name,value);
	}
	
	/**
	 * 删除一个branch
	 * @param orderIds
	 * @param customerCommission
	 * @return
	 */
	@RequestMapping("/del/{rankCustomerId}")
	@ResponseBody
	public void newCustomerSave(@PathVariable("rankCustomerId") Long rankCustomerId){
		rankCustomerLogic.del(rankCustomerId);
	}

	/**
	 * new 跳转
	 * @return
	 */
	@RequestMapping("/new")
	public ModelAndView newBranch(){
		ModelAndView mv = new ModelAndView("administrator/rankcustomer/new");
		return mv;
	}
	
	/**
	 * 新增保存
	 * @param branch
	 * @return
	 */
	@RequestMapping("/new/save")
	public ModelAndView newCustomerSave(@ModelAttribute("rankCustomer") RankCustomer rankCustomer){
		rankCustomerLogic.save(rankCustomer);
		ModelAndView mv = new ModelAndView(new RedirectView("/management/rankcustomer/new",true));
		return mv;
	}
	
	
	@RequestMapping(value="/api/all",method = RequestMethod.GET)
	public ResponseEntity<List<RankCustomer>> getBranches(@ModelAttribute(value = "searchForm") SearchForm searchForm) {
		
		Pageable pageable = new PageRequest(searchForm.getPage(), searchForm.getSize());
		return new ResponseEntity<List<RankCustomer>>(rankCustomerLogic.getAllRankCustomer(pageable),HttpStatus.OK);
	}
}
