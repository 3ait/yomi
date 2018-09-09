package com.datas.easyorder.db.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.datas.easyorder.db.entity.UserCompany;

public interface UserCompanyRepository extends CrudRepository<UserCompany, Long> {

	 
	List<UserCompany> findAll();
	
}
