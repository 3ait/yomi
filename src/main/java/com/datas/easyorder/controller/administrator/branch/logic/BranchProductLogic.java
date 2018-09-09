package com.datas.easyorder.controller.administrator.branch.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.datas.easyorder.controller.BaseLogic;
import com.datas.easyorder.db.dao.BranchProductRepository;
import com.datas.easyorder.db.entity.BranchProduct;

@Component
public class BranchProductLogic extends BaseLogic<BranchProduct> {

	@Autowired
	BranchProductRepository branchProductRepository;

	@Override
	public CrudRepository<BranchProduct, Long> getRepository() {
		return branchProductRepository;
	}

}
