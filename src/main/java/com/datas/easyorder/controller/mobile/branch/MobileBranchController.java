package com.datas.easyorder.controller.mobile.branch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.branch.logic.BranchLogic;
import com.datas.easyorder.db.entity.Branch;
import com.datas.utils.SearchForm;

/**
 * 
 * @author leo
 *
 */
@Controller
@RequestMapping("/m/api/branches")
public class MobileBranchController extends BaseController<Branch> {

	@Autowired
	BranchLogic branchLogic;


	@RequestMapping(value="",method = RequestMethod.GET)
	public ResponseEntity<List<Branch>> index(@ModelAttribute(value = "searchForm") SearchForm searchForm) {
		Pageable pageable = new PageRequest(searchForm.getPage(), searchForm.getSize());
		return new ResponseEntity<List<Branch>>(branchLogic.getAllBranch(pageable).getContent(),HttpStatus.OK);
	}
	
	
}
