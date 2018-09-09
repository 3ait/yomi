package com.datas.easyorder.controller.administrator.report;

import java.util.Calendar;

import javax.validation.Valid;

import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.product.logic.ProductLogic;
import com.datas.easyorder.controller.administrator.report.logic.ReportLogic;
import com.datas.easyorder.db.entity.Product;
import com.datas.utils.SearchForm;
import com.plugin.utils.DateHelper;

/**
 * 
 * @author leo
 * System Config
 *
 */
@RestController
@RequestMapping("/management/report")
public class ReportController extends BaseController{

	@Autowired
	private ReportLogic reportLogic;
	@Autowired
	private ProductLogic productLogic;
	
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
			BindingResult bindingResult, @RequestParam(defaultValue = "1", required = false) Integer page) {
		
		ModelAndView modelAndView = new ModelAndView("administrator/product/index");
		
		if (StringHelper.isEmpty(searchForm.getDateFrom())) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -36);
			searchForm.setDateFrom(DateHelper.getYYYYMMDD(calendar.getTime().getTime()));
		}
		if (StringHelper.isEmpty(searchForm.getDateTo())) {
			searchForm.setDateTo(DateHelper.getYYYYMMDD());
		}

		
		
		return modelAndView;
	}
	
	/**
	 * Single Product Report
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return
	 */
	@RequestMapping(value={"/product/{productId}"})
	public ModelAndView singleProductReport(@Valid @ModelAttribute(value = "searchForm") SearchForm searchForm,
			BindingResult bindingResult, @RequestParam(defaultValue = "1", required = false) Integer page,
			@PathVariable("productId") Long productId) {
		
		ModelAndView modelAndView = new ModelAndView("administrator/product/report-single");
		
		if (StringHelper.isEmpty(searchForm.getDateFrom())) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -1);
			searchForm.setDateFrom(DateHelper.getYYYYMMDD(calendar.getTime().getTime()));
		}
		if (StringHelper.isEmpty(searchForm.getDateTo())) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, 1);
			searchForm.setDateTo(DateHelper.getYYYYMMDD(calendar.getTime().getTime()));
		}
		
		modelAndView.addObject("productReport", reportLogic.getSingleProductReport(productId, DateHelper.parseYYYYMMDD(searchForm.getDateFrom()),DateHelper.parseYYYYMMDD(searchForm.getDateTo())));
		
		
		modelAndView.addObject("searchForm",searchForm);
		modelAndView.addObject("page", page);
		modelAndView.addObject("product", productLogic.getProdcutById(productId));
		
		
		return modelAndView;
	} 
	
	/**
	 * multiple Product Report
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return
	 */
	@RequestMapping(value={"/product/multiple"})
	public ModelAndView multipleProductReport(@Valid @ModelAttribute(value = "searchForm") SearchForm searchForm,
			BindingResult bindingResult, @RequestParam(defaultValue = "1", required = false) Integer page,
			@RequestParam("productIds") Long[] productIds) {
		
		ModelAndView modelAndView = new ModelAndView("administrator/product/report-multipule");
		
		if (StringHelper.isEmpty(searchForm.getDateFrom())) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -1);
			searchForm.setDateFrom(DateHelper.getYYYYMMDD(calendar.getTime().getTime()));
		}
		if (StringHelper.isEmpty(searchForm.getDateTo())) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, 1);
			searchForm.setDateTo(DateHelper.getYYYYMMDD(calendar.getTime().getTime()));
		}
		
		modelAndView.addObject("productReport", reportLogic.getMultipuleProductReport(productIds, DateHelper.parseYYYYMMDD(searchForm.getDateFrom()),DateHelper.parseYYYYMMDD(searchForm.getDateTo())));
		
		
		modelAndView.addObject("searchForm",searchForm);
		modelAndView.addObject("page", page);
		
	
		modelAndView.addObject("productIds", productIds);
		
		return modelAndView;
	} 

}
