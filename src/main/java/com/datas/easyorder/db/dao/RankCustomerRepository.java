package com.datas.easyorder.db.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.datas.easyorder.db.entity.RankCustomer;

@Component("rankCustomerRepository")
public interface RankCustomerRepository extends CrudRepository<RankCustomer, Long> {
	 

	public Page<RankCustomer> findAll(Pageable pageable);

}
