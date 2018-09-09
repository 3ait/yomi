package com.datas.easyorder.controller.administrator.quotation;

import java.util.Calendar;

import javax.validation.Valid;

import org.hibernate.internal.util.StringHelper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.datas.easyorder.controller.BaseController;
import com.datas.utils.SearchForm;
import com.plugin.utils.DateHelper;

/**
 * 
 * @author leo
 *
 */
@RestController
@RequestMapping("/management/quotation")
public class QuotationController extends BaseController{


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
		
		ModelAndView modelAndView = new ModelAndView("/quotation/index");
		
		
		if (StringHelper.isEmpty(searchForm.getDateFrom())) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -1);
			searchForm.setDateFrom(DateHelper.getYYYYMMDD(calendar.getTime().getTime()));
		}
		if (StringHelper.isEmpty(searchForm.getDateTo())) {
			searchForm.setDateTo(DateHelper.getYYYYMMDD());
		}

		String[] sortBy = {"id","status"};
		Pageable pageable = new PageRequest(searchForm.getPage()-1<1?0:searchForm.getPage()-1, searchForm.getSize(), Direction.DESC,sortBy);
		
		/*Page<Project> companyPage = new PageImpl<>(new ArrayList<>());

		modelAndView.addObject("companyList", companyPage.getContent());
		modelAndView.addObject("totalPages", companyPage.getTotalPages());
		modelAndView.addObject("number", page > companyPage.getTotalPages() ? 0 : companyPage.getNumber() + 1);

		modelAndView.addObject("searchForm", searchForm);*/
		return modelAndView;
	}



}
