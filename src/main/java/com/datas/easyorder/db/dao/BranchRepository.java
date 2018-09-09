package com.datas.easyorder.db.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.datas.easyorder.db.entity.Branch;

public interface BranchRepository extends CrudRepository<Branch, Long> {

	public Page<Branch> findAll(Pageable pageable);


    
}
