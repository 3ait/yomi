package com.datas.easyorder.controller.administrator.customer;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.customer.logic.CustomerCommissionLogic;
import com.datas.easyorder.controller.administrator.customer.logic.CustomerLogic;
import com.datas.easyorder.db.dao.CustomerRepository;
import com.datas.easyorder.db.entity.Customer;
import com.datas.easyorder.db.entity.CustomerCommission;
import com.datas.utils.SearchForm;
import com.plugin.utils.DateHelper;

/**
 * 
 * @author leo
 *
 */
@RestController
@RequestMapping("/management/customer")
public class CustomerController extends BaseController<Customer>{

	@Autowired
	private CustomerLogic customerLogic;
	@Autowired
	private CustomerCommissionLogic customerCommissionLogic;
	
	/**
	 * 获取所有Customer
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return
	 */
	@RequestMapping(value={"","/"})
	public ModelAndView index( @ModelAttribute(value = "searchForm") SearchForm searchForm) {
		
		ModelAndView modelAndView = new ModelAndView("administrator/customer/index");
		
		if (StringHelper.isEmpty(searchForm.getDateFrom())) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -12);
			searchForm.setDateFrom(DateHelper.getYYYYMMDD(calendar.getTime().getTime()));
		}
		if (StringHelper.isEmpty(searchForm.getDateTo())) {
			searchForm.setDateTo(DateHelper.getYYYYMMDD());
		}

		Pageable pageable = new PageRequest(searchForm.getPage()-1<1?0:searchForm.getPage()-1, searchForm.getSize(), Direction.fromString(searchForm.getSort()), searchForm.getSortBy());
		
		Page<Customer> list = customerLogic.findAll(searchForm,CustomerRepository.customerType_customer, pageable);

		modelAndView.addObject("customerList", list.getContent());
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
		
		if(name.equalsIgnoreCase("status") && value.equals(CustomerRepository.status_active+"")){
			customerLogic.sendActiveEmail(pk,super.getLognUser());
		}
		customerLogic.update(pk,name,value);
	}
	
	/**
	 * get Statement
	 * @return ModelAndView
	 */
	@RequestMapping(value={"/statement/{customerId}"})
	public ModelAndView statement(@PathVariable("customerId") Long customerId,
			@Valid @ModelAttribute(value = "searchForm") SearchForm searchForm,
			BindingResult bindingResult){
		ModelAndView modelAndView = new ModelAndView("administrator/customer/statement/index");
		
		if (StringHelper.isEmpty(searchForm.getDateFrom())) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -36);
			searchForm.setDateFrom(DateHelper.getYYYYMMDD(calendar.getTime().getTime()));
		}
		if (StringHelper.isEmpty(searchForm.getDateTo())) {
			searchForm.setDateTo(DateHelper.getYYYYMMDD());
		}
		List<Transfer> transferList = customerLogic.getTransferList(customerId,searchForm);
		
		modelAndView.addObject("transferList", transferList);
		modelAndView.addObject("customer", customerLogic.getCustomerById(customerId));
		modelAndView.addObject("searchForm", searchForm);
		
		return modelAndView;
	}

	
	/**
	 * 
	 * get Statement PDF
	 * @return ModelAndView
	 * @throws IOException 
	 * 
	 */
	@RequestMapping(value={"/statement/pdf/{customerId}"}, produces="application/pdf;")
	public void statementPdf(@PathVariable("customerId") Long customerId,
			@Valid @ModelAttribute(value = "searchForm") SearchForm searchForm,
			BindingResult bindingResult, HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		response.setContentType("application/pdf;");
		
		customerLogic.getStatementPdf(customerId,super.getLognUser(),searchForm,request,response);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	
	
	/**
	 * 
	 * @param customerIds
	 * @return ModelAndView
	 */
	@RequestMapping(value={"/statement/batch/print/preview"}, produces="application/pdf;")
	public void batchPrint(@RequestParam("customerIds") Long[] customerIds, @ModelAttribute(value = "searchForm") SearchForm searchForm,
			BindingResult bindingResult, HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		response.setContentType("application/pdf;");
		
		customerLogic.getStatementPdf(customerIds,super.getLognUser(),searchForm,request,response);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	
	
	/**
	 * 
	 * Email
	 * @return ModelAndView
	 * @throws IOException 
	 * 
	 */
	@ResponseBody
	@RequestMapping(value={"/statement/pdf/email/{customerId}"} )
	public String statementPdfEmail(@PathVariable("customerId") Long customerId,
			@Valid @ModelAttribute(value = "searchForm") SearchForm searchForm,
			BindingResult bindingResult, HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		String ret = customerLogic.statementEmail(customerId,super.getLognUser(),searchForm,request,response);
		
		Customer customer = customerLogic.getCustomerById(customerId);
		if(customer!=null && "success".equals(ret)){
			customer.setModifyTime(Calendar.getInstance().getTime());
			customerLogic.update(customer);
		}
		
		return ret;
	}
	
	/**
	 * 
	 * Email
	 * @return ModelAndView
	 * @throws IOException 
	 */
	@RequestMapping(value={"/get/{customerId}/balance"} )
	@ResponseBody
	public Double getCustomerBalance(@PathVariable("customerId") Long customerId) throws IOException{
		
		Double balance = customerLogic.findOneById(customerId).getBalance();
		return balance;
	}
	
	
	/**
	 * 获取所有Sales
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return
	 */
	@RequestMapping(value={"/sales"})
	public ModelAndView saleIndex(@Valid @ModelAttribute(value = "searchForm") SearchForm searchForm,
			BindingResult bindingResult) {
		
		ModelAndView modelAndView = new ModelAndView("administrator/customer/sales/index");
		
		if (StringHelper.isEmpty(searchForm.getDateFrom())) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -6);
			searchForm.setDateFrom(DateHelper.getYYYYMMDD(calendar.getTime().getTime()));
		}
		if (StringHelper.isEmpty(searchForm.getDateTo())) {
			searchForm.setDateTo(DateHelper.getYYYYMMDD());
		}

		Pageable pageable = new PageRequest(searchForm.getPage()-1<1?0:searchForm.getPage()-1, searchForm.getSize(), Direction.fromString(searchForm.getSort()), searchForm.getSortBy());
		Page<Customer> list = customerLogic.findAll(searchForm,CustomerRepository.customerType_sales, pageable);
		modelAndView.addObject("customerList", list.getContent());
		
		super.setPage(modelAndView, list, searchForm);
		
		return modelAndView;
	}
	
	
	/**
	 * commission
	 * @param customerId
	 * @param searchForm
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value={"/sales/commission/{customerId}"})
	public ModelAndView commission(@PathVariable("customerId") Long customerId,
			@Valid @ModelAttribute(value = "searchForm") SearchForm searchForm,
			BindingResult bindingResult){
		ModelAndView modelAndView = new ModelAndView("administrator/customer/sales/commission/index");
		
		if (StringHelper.isEmpty(searchForm.getDateFrom())) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -6);
			searchForm.setDateFrom(DateHelper.getYYYYMMDD(calendar.getTime().getTime()));
		}
		if (StringHelper.isEmpty(searchForm.getDateTo())) {
			searchForm.setDateTo(DateHelper.getYYYYMMDD());
		}
		
		List<CommissionView> commissionViewList = customerCommissionLogic.getCommissionPayment(customerId,searchForm);
		modelAndView.addObject("commissionViewList", commissionViewList);
		modelAndView.addObject("customer", customerLogic.getCustomerById(customerId));
		modelAndView.addObject("searchForm", searchForm);
		
		return modelAndView;
	}

	/**
	 * 保存 commission
	 * @param orderIds
	 * @param customerCommission
	 * @return
	 */
	@RequestMapping("/sales/commission/save")
	public ModelAndView commissionSave(@RequestParam(name="orderIds") Long[] orderIds,CustomerCommission customerCommission,HttpServletRequest request){
		customerCommissionLogic.saveCommission(super.getLognUser(),orderIds,customerCommission);
		return new ModelAndView(new RedirectView("/management/customer/sales/commission/" + customerCommission.getCustomer().getId(), true));
	}
	
	/**
	 * 更新value属性
	 */
	@RequestMapping("/sales/commission/update/colomn/")
	@ResponseBody
	public void customerCommissionValueUpdate(
			@RequestParam("pk") Long  pk, 
			@RequestParam("name") String name , 
			@RequestParam("value") String value ,
			HttpServletRequest request
			){
		customerCommissionLogic.update(pk,name,value);
	}
	
	/**
	 * 获取一个Customer
	 * @param orderIds
	 * @param customerCommission
	 * @return
	 */
	@RequestMapping("/{customerId}")
	public ModelAndView commissionSave(@PathVariable("customerId") Long customerId){
		ModelAndView mv = new ModelAndView("administrator/customer/edit");
		
		mv.addObject("customer",customerLogic.getCustomerById(customerId));
		return mv;
	}
	/**
	 * 删除一个Customer
	 * @return
	 */
	@RequestMapping("/del/{customerId}")
	@ResponseBody
	public void newCustomerSave(@PathVariable("customerId") Long customerId){
		
		customerLogic.delCustomer(customerId);
	}
	/**
	 * 关联词查询customer
	 */
	@RequestMapping("/q")
	@ResponseBody
	public ResponseEntity<List<Customer>> getCustomerList(@ModelAttribute SearchForm searchForm){
		
		Pageable pageable = new PageRequest(searchForm.getPage()-1<1?0:searchForm.getPage()-1, searchForm.getSize(), Direction.fromString(searchForm.getSort()), searchForm.getSortBy());
		Page<Customer> list = customerLogic.findByCompanyName(searchForm.getQ(),CustomerRepository.customerType_customer, pageable);
		
		return new ResponseEntity<List<Customer>>(list.getContent(),HttpStatus.OK);
		
	}
	
	/**
	 * new 跳转
	 * @return
	 */
	@RequestMapping("/new")
	public ModelAndView newCustomer(){
		ModelAndView mv = new ModelAndView("administrator/customer/new");
		
		return mv;
	}
	
	/**
	 * 保存一个Customer
	 * @return
	 */
	@RequestMapping("/new/save")
	public ModelAndView newCustomerSave(@ModelAttribute("customer") Customer customer){
		
		customerLogic.save(customer);
		
		ModelAndView mv = new ModelAndView(new RedirectView("/management/customer/"+customer.getId(),true) );
		return mv;
	}
	
	/**
	 * email check 
	 * @return String
	 */
	@RequestMapping(value={"/email/check"})
	@ResponseBody
	public String checkEmail(@RequestParam(value="companyEmail",required=true) String companyEmail) {
		Boolean ret = true;
		Customer customer = customerLogic.findByEmail(companyEmail);
		if(customer!=null){
			ret = false;
		}
		
		return ret.toString();
	}
}
