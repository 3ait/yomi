package com.datas.easyorder.controller.administrator.branch;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import com.datas.easyorder.controller.administrator.branch.logic.BranchLogic;
import com.datas.easyorder.db.entity.Branch;
import com.datas.utils.SearchForm;
import com.plugin.utils.DateHelper;

/**
 * 
 * @author leo
 * Attachment management
 *
 */
@RestController
@RequestMapping("/management/branch")
public class BranchController extends BaseController<Branch> {

	
	@Autowired
	BranchLogic branchLogic;


	@RequestMapping(value={"","/"},method = RequestMethod.GET)
	public ModelAndView index(@ModelAttribute(value = "searchForm") SearchForm searchForm) {
		
		if (StringHelper.isEmpty(searchForm.getDateFrom())) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.YEAR, -3);
			searchForm.setDateFrom(DateHelper.getYYYYMMDD(calendar.getTime().getTime()));
		}
		if (StringHelper.isEmpty(searchForm.getDateTo())) {
			searchForm.setDateTo(DateHelper.getYYYYMMDD());
		}
		
		ModelAndView mv = new ModelAndView("administrator/branch/index");
		Pageable pageable = new PageRequest(searchForm.getPage(), searchForm.getSize());
		
		Page<Branch> page = branchLogic.getAllBranch(pageable);
		super.setPage(mv,page,searchForm );
		
		mv.addObject("branchList",page.getContent());
		return mv;
	}
	
	/**
	 * 更新value属性
	 */
	@RequestMapping("/update/colomn/")
	@ResponseBody
	public void update(
			@RequestParam("pk") Long  pk, 
			@RequestParam("name") String name , 
			@RequestParam("value") String value ,
			HttpServletRequest request
			){
		branchLogic.update(pk,name,value);
	}
	
	
	/**
	 * 删除一个branch
	 * @param orderIds
	 * @param customerCommission
	 * @return
	 */
	@RequestMapping("/del/{branchId}")
	@ResponseBody
	public void newCustomerSave(@PathVariable("branchId") Long branchId){
		
		branchLogic.del(branchId);
	}

	/**
	 * new 跳转
	 * @return
	 */
	@RequestMapping("/new")
	public ModelAndView newBranch(){
		ModelAndView mv = new ModelAndView("administrator/branch/new");
		
		return mv;
	}
	
	/**
	 * 新增保存
	 * @param branch
	 * @return
	 */
	@RequestMapping("/new/save")
	public ModelAndView newCustomerSave(@ModelAttribute("branch") Branch branch){
		
		branchLogic.save(branch);
		
		ModelAndView mv = new ModelAndView(new RedirectView("/management/branch/new",true) );
		return mv;
	}
	
	
	@RequestMapping(value="/api/all",method = RequestMethod.GET)
	public ResponseEntity<List<Branch>> getBranches(@ModelAttribute(value = "searchForm") SearchForm searchForm) {
		
		Pageable pageable = new PageRequest(searchForm.getPage(), searchForm.getSize());
		return new ResponseEntity<List<Branch>>(branchLogic.getAllBranch(pageable).getContent(),HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param productId
	 * @param name='branchId'
	 * @param value = branchId
	 * @param request
	 */
	@RequestMapping("/api/new/stock")
	@ResponseBody
	public void newBranchStock(
			@RequestParam("pk") Long  productId, 
			@RequestParam("name") String name , 
			@RequestParam("value") String value ,
			HttpServletRequest request
			){
		branchLogic.newStock(productId,value);
	}
	
	/**
	 * 获取产品所有Stock
	 * @param productId
	 * @return
	 */
	@RequestMapping(value="/api/stocks/{productId}",method = RequestMethod.GET)
	public ResponseEntity<List<BranchProductView>> getAllStocks(@PathVariable("productId") Long productId) {
		return new ResponseEntity<List<BranchProductView>>(branchLogic.getAllStocks(productId),HttpStatus.OK);
	}
	
	
	
}
