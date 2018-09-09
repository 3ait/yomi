package com.datas.easyorder.db.dao;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.datas.easyorder.db.entity.CustomerCommission;

public interface CustomerCommissionRepository extends CrudRepository<CustomerCommission, Long> {

	
	Page<CustomerCommission> findByCustomerIdAndCreateTimeBetween(long customerId, Date startDate, Date time,Pageable pageRequest);

	
}
