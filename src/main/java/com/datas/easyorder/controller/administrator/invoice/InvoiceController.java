package com.datas.easyorder.controller.administrator.invoice;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.invoice.logic.InvoiceLogic;
import com.datas.easyorder.db.entity.Invoice;
import com.datas.easyorder.db.entity.User;
import com.datas.utils.SearchForm;
import com.plugin.utils.DateHelper;

/**
 * 
 * @author leo
 *
 */
@RestController
@RequestMapping("/management/invoice")
public class InvoiceController extends BaseController<Invoice>{

	@Autowired
	InvoiceLogic invoiceLogic;
	
	/**
	 * 
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return
	 */
	@RequestMapping(value={"","/"})
	public ModelAndView index(@Valid @ModelAttribute(value = "searchForm") SearchForm searchForm,
			BindingResult bindingResult) {
		
		ModelAndView modelAndView = new ModelAndView("administrator/invoice/index");
		
		
		if (StringHelper.isEmpty(searchForm.getDateFrom())) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -36);
			searchForm.setDateFrom(DateHelper.getYYYYMMDD(calendar.getTime().getTime()));
		}
		if (StringHelper.isEmpty(searchForm.getDateTo())) {
			searchForm.setDateTo(DateHelper.getYYYYMMDD());
		}

		Pageable pageable = new PageRequest(searchForm.getPage()-1<1?0:searchForm.getPage()-1, searchForm.getSize(), Direction.fromString(searchForm.getSort()), searchForm.getSortBy());
		
		Page<Invoice> list = invoiceLogic.getList(searchForm,pageable);

		modelAndView.addObject("invoiceList", list.getContent());
		
		modelAndView.addObject("totalPrice",list.getContent().stream().map(invoice -> invoice.getTotal()).reduce(0D,(a,b) -> a+b).doubleValue());
		modelAndView.addObject("totalBalance",list.getContent().stream().map(invoice -> invoice.getBalance()).reduce(0D,(a,b) -> a+b).doubleValue());

		super.setPage(modelAndView, list, searchForm);
		return modelAndView;
	}

	/**
	 * Edit redirect
	 * @return
	 */
	@RequestMapping("/edit/{invoiceId}")
	public ModelAndView edit(@PathVariable long invoiceId,HttpServletRequest request){
		ModelAndView mv = new ModelAndView("administrator/invoice/detail");
		
		InvoiceView invoiceView = invoiceLogic.getInvoiceById(invoiceId,super.getLognUser());
		mv.addObject("invoiceView", invoiceView);
		return mv;
	}
	
	/**
	 * email
	 * @return
	 */
	@RequestMapping("/email/{invoiceId}")
	@ResponseBody
	public String email(@PathVariable long invoiceId,HttpServletRequest request,HttpServletResponse response){
		String ret = invoiceLogic.email(invoiceId, super.getLognUser(), request, response);
		
		return ret;
	}
	
	/**
	 * invoice 批量预览
	 * @return
	 */
	@RequestMapping("/batch/print/preview")
	public ModelAndView printPreview(@RequestParam("invoiceIds") Long[] invoiceIds ){
		
		User user = super.getLognUser();
		ModelAndView modelAndView = new ModelAndView("administrator/invoice/pdf/template");
		List<InvoiceView> invoiceViewList =  invoiceLogic.invoicePreview(invoiceIds,user);
		modelAndView.addObject("invoiceViewList",invoiceViewList);
		return modelAndView;
	}
	
	/**
	 * pdf View | download
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/pdf/view/{invoiceId}.pdf",produces="application/pdf;")
	public void view(@PathVariable long invoiceId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		response.setContentType("application/pdf");
        invoiceLogic.pdfOutputStream(invoiceId, super.getLognUser(), request, response);
        
        response.getOutputStream().flush();
        response.getOutputStream().close();
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
		invoiceLogic.update(pk,name,value,super.getLognUser());
	}
}
