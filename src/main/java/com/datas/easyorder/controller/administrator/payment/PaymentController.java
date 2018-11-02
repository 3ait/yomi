package com.datas.easyorder.controller.administrator.payment;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.internal.util.StringHelper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
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
import com.datas.easyorder.controller.administrator.payment.logic.PaymentLogic;
import com.datas.easyorder.db.entity.CustomerPaymentHistory;
import com.datas.easyorder.db.entity.Invoice;
import com.datas.utils.SearchForm;
import com.plugin.utils.DateHelper;

/**
 * 
 * @author leo
 *
 */
@RestController
@RequestMapping("/management/payment")
public class PaymentController extends BaseController<CustomerPaymentHistory>{

	@Autowired
	PaymentLogic paymentLogic;
	
	/**
	 * 
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return ModelAndView
	 */
	@RequestMapping(value={"","/"})
	public ModelAndView index(@Valid @ModelAttribute(value = "searchForm") SearchForm searchForm,
			BindingResult bindingResult) {
		
		ModelAndView modelAndView = new ModelAndView("administrator/payment/index");
		
		
		if (StringHelper.isEmpty(searchForm.getDateFrom())) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -36);
			searchForm.setDateFrom(DateHelper.getYYYYMMDD(calendar.getTime().getTime()));
		}
		if (StringHelper.isEmpty(searchForm.getDateTo())) {
			searchForm.setDateTo(DateHelper.getYYYYMMDD());
		}

		Pageable pageable = new PageRequest(searchForm.getPage()-1<1?0:searchForm.getPage()-1, searchForm.getSize(), Direction.fromString(searchForm.getSort()), searchForm.getSortBy());
		
		
		Page<CustomerPaymentHistory> list = paymentLogic.getList(searchForm,pageable);

		modelAndView.addObject("customerPaymentHistoryList", list.getContent());
		
		super.setPage(modelAndView,list, searchForm);
		
		return modelAndView;
	}

	
	
	/**
	 * 更新value属性
	 */
	@RequestMapping("/update/colomn/")
	public void valueUpdate(
			@RequestParam("pk") Long  pk, 
			@RequestParam("name") String name , 
			@RequestParam("value") String value,
			HttpServletRequest request
			){
		paymentLogic.update(pk,name,value);
	}
	
	
	/**
	 * 增加Payment
	 * @param customerPaymentHistory
	 * @param hiddenId
	 * 
	 */
	@RequestMapping("/save")
	@ResponseBody
	public void  save(@ModelAttribute(value="customerPaymentHistory") CustomerPaymentHistory customerPaymentHistory,
			@RequestParam("hiddenId") Long hiddenId, 
			@RequestParam("invoiceId") Long invoiceId, 
			HttpServletRequest request){
		if(invoiceId!=null){
			Invoice invoice = new Invoice();
			invoice.setId(invoiceId);
			customerPaymentHistory.setInvoice(invoice);
		}else{
			customerPaymentHistory.setInvoice(null);
		}
		paymentLogic.savePayment(customerPaymentHistory, hiddenId,super.getLognUser());
		
	}
	
	
	@RequestMapping("/invoice/{invoiceId}")
	@ResponseBody
	public String getCompanyInfoByInvoiceId(@PathVariable("invoiceId") String invoiceId) throws Exception {

		JSONObject strs = null;
		if (StringHelper.isNotEmpty(invoiceId)) {
			strs = paymentLogic.getCompanyInfoByInvoiceId(invoiceId);
		} else {
			strs = new JSONObject();
		}
		return strs.toString();
	}
	
	/**
	 * 获取关联词列表
	 * 
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping("/words")
	@ResponseBody
	public String getGuessingWord(@RequestParam("q") String q, @RequestParam("size") int size) throws Exception {

		JSONObject strs = null;
		if (StringHelper.isNotEmpty(q)) {
			strs = paymentLogic.getGuessingWords(q, size);
		} else {
			strs = new JSONObject();
		}
		return strs.toString();
	}
}
