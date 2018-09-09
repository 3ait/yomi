package com.datas.easyorder.controller.administrator.dashboard;

import java.util.Calendar;

import javax.validation.Valid;

import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.customer.logic.CustomerLogic;
import com.datas.easyorder.controller.administrator.order.logic.OrderLogic;
import com.datas.easyorder.controller.administrator.report.logic.ReportLogic;
import com.datas.easyorder.db.dao.CustomerRepository;
import com.datas.utils.SearchForm;
import com.plugin.utils.DateHelper;

/**
 * 
 * @author leo
 *
 */
@RestController
@RequestMapping("/management/dashboard")
public class DashboardController extends BaseController{

	@Autowired
	private CustomerLogic customerLogic;
	@Autowired
	private OrderLogic orderLogic;
	@Autowired
	private ReportLogic reportLogic;

	/**
	 * 获取前topProductNum个销量最好的产品
	 */
	private static final int topProductNum = 100;
	
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
			BindingResult bindingResult, @RequestParam(defaultValue = "1", required = false) Integer page) {
		
		ModelAndView modelAndView = new ModelAndView("administrator/dashboard/index");
		
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
		
		
		
		Calendar pageViewFromCalendar = Calendar.getInstance(); 
		pageViewFromCalendar.add(Calendar.DATE, -7);
		Calendar toCalendar = Calendar.getInstance(); 
		Pageable pageable = new PageRequest(0, 1);
		searchForm.setStatus(0);
		modelAndView.addObject("customerNumber", customerLogic.findAll(searchForm,CustomerRepository.customerType_customer, pageable).getTotalElements());
		modelAndView.addObject("pageViewNumber", reportLogic.getPageViewNumber(pageViewFromCalendar.getTime(), toCalendar.getTime()));
		modelAndView.addObject("orderNumber", orderLogic.getOrderList(searchForm, pageable).getTotalElements());
		
		//order
		modelAndView.addObject("orderDateNumberPrice", reportLogic.getOrderReportByDate(DateHelper.parseYYYYMMDD(searchForm.getDateFrom()),DateHelper.parseYYYYMMDD(searchForm.getDateTo())));
		//sales
		modelAndView.addObject("salesChartData", reportLogic.getSalesReportByDate(DateHelper.parseYYYYMMDD(searchForm.getDateFrom()),DateHelper.parseYYYYMMDD(searchForm.getDateTo())));
		//pageView
		modelAndView.addObject("pageViewData", reportLogic.getPageViewReportByDate(DateHelper.parseYYYYMMDD(searchForm.getDateFrom()),DateHelper.parseYYYYMMDD(searchForm.getDateTo())));
		//Top50 sales Product
		modelAndView.addObject("topProduct", reportLogic.getTopProduct(DateHelper.parseYYYYMMDD(searchForm.getDateFrom()),DateHelper.parseYYYYMMDD(searchForm.getDateTo()),topProductNum));
		
		
		modelAndView.addObject("searchForm",searchForm);
		modelAndView.addObject(" page", page);
		
		
		return modelAndView;
	}
	
	
	
	
	

}
