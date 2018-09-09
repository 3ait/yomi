package com.datas.easyorder.controller.administrator.attachment;

import java.util.Calendar;

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
import org.springframework.web.servlet.view.RedirectView;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.attachment.logic.AttachmentLogic;
import com.datas.easyorder.controller.administrator.category.logic.CategoryLogic;
import com.datas.easyorder.db.dao.MenuRepository;
import com.datas.easyorder.db.entity.Attachment;
import com.datas.utils.SearchForm;
import com.plugin.utils.DateHelper;

/**
 * 
 * @author leo
 * Attachment management
 *
 */
@RestController
@RequestMapping("/management/attachment")
public class AttachmentController extends BaseController<Attachment> {

	@Autowired
	AttachmentLogic attachmentLogic;
	@Autowired
	CategoryLogic categoryLogic;

	/**
	 * index
	 * 
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * @return ModelAndView
	 */
	@RequestMapping(value = { "", "/" })
	public ModelAndView index(@Valid @ModelAttribute(value = "searchForm") SearchForm searchForm,
			BindingResult bindingResult) {

		ModelAndView modelAndView = new ModelAndView("administrator/attachment/index");

		if (StringHelper.isEmpty(searchForm.getDateFrom())) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -36);
			searchForm.setDateFrom(DateHelper.getYYYYMMDD(calendar.getTime().getTime()));
		}
		if (StringHelper.isEmpty(searchForm.getDateTo())) {
			searchForm.setDateTo(DateHelper.getYYYYMMDD());
		}

		String[] sortBy = { "id", "position" };
		Pageable pageable = new PageRequest(searchForm.getPage() - 1 < 1 ? 0 : searchForm.getPage() - 1, searchForm.getSize(), Direction.DESC, sortBy);

		Page<Attachment> attachmentList = attachmentLogic.attachementList(searchForm, pageable);

		modelAndView.addObject("attachmentList", attachmentList.getContent());
		super.setPage(modelAndView, attachmentList, searchForm);

		// 加载菜单
		modelAndView.addObject("menuList", categoryLogic.getMenuByLevel(1, MenuRepository.MENU_TYPE_ARTICLE));
		modelAndView.addObject("leve2FatherId", 0);
		modelAndView.addObject("leve3FatherId", 0);

		return modelAndView;
	}

	/**
	 * del by Id
	 * 
	 * @param attachmentId
	 * @return
	 */
	@RequestMapping("/del/{id}")
	@ResponseBody
	public boolean del(@PathVariable("id") Long attachmentId) {
		return attachmentLogic.delAttachmentById(attachmentId);

	}
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/new")
	public ModelAndView newRedirect() {
		ModelAndView modelAndView = new ModelAndView("administrator/attachment/fragment/new-container");

		// 加载菜单
		modelAndView.addObject("searchForm", new SearchForm());
		modelAndView.addObject("menuList", categoryLogic.getMenuByLevel(1, MenuRepository.MENU_TYPE_ARTICLE));
		modelAndView.addObject("leve2FatherId", 0);
		modelAndView.addObject("leve3FatherId", 0);
		return modelAndView;
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/new/save")
	public ModelAndView newSave(@ModelAttribute("productForm") AttachmentForm attachmentForm ) {
		ModelAndView modelAndView = new ModelAndView(new RedirectView("/management/attachment/new",true));
		
		attachmentLogic.saveAttachments(attachmentForm);
		return modelAndView;
	}

	/**
	 * 更新value属性
	 */
	@RequestMapping("/update/colomn/")
	@ResponseBody
	public void valueUpdate(@RequestParam("pk") Long pk, @RequestParam("name") String name,
			@RequestParam("value") String value) {
		attachmentLogic.update(pk, name, value);
	}
	
	/**
	 * index
	 * 
	 * @param page
	 * @param size
	 * @param orderBy
	 * @param order
	 * CKEDITOR CKEditor=txtDefaultHtmlArea&CKEditorFuncNum=1&langCode=en
	 * @return ModelAndView
	 */
	@RequestMapping(value = { "/ckediter/select" })
	public ModelAndView show(@Valid @ModelAttribute(value = "searchForm") SearchForm searchForm,
			BindingResult bindingResult,
			@RequestParam(value="CKEditorFuncNum",required=false,defaultValue="0") Integer CKEditorFuncNum
			) {

		ModelAndView modelAndView = new ModelAndView("administrator/attachment/editor-select");
		
		
		
		if (StringHelper.isEmpty(searchForm.getDateFrom())) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -36);
			searchForm.setDateFrom(DateHelper.getYYYYMMDD(calendar.getTime().getTime()));
		}
		if (StringHelper.isEmpty(searchForm.getDateTo())) {
			searchForm.setDateTo(DateHelper.getYYYYMMDD());
		}

		String[] sortBy = { "id", "position" };
		Pageable pageable = new PageRequest(searchForm.getPage() - 1 < 1 ? 0 :searchForm.getPage() - 1, searchForm.getSize(), Direction.DESC, sortBy);

		Page<Attachment> attachmentList = attachmentLogic.attachementList(searchForm, pageable);

		modelAndView.addObject("attachmentList", attachmentList.getContent());
		super.setPage(modelAndView, attachmentList, searchForm);

		// 加载菜单
		modelAndView.addObject("menuList", categoryLogic.getMenuByLevel(1, MenuRepository.MENU_TYPE_ARTICLE));
		modelAndView.addObject("leve2FatherId", 0);
		modelAndView.addObject("leve3FatherId", 0);

		//CKEDITOR
		modelAndView.addObject("CKEditorFuncNum", CKEditorFuncNum);
		
		return modelAndView;
	}
	
	
	
}
