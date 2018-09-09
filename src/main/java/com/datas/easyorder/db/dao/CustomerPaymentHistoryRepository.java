package com.datas.easyorder.db.dao;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import com.datas.easyorder.db.entity.CustomerPaymentHistory;

public interface CustomerPaymentHistoryRepository extends CrudRepository<CustomerPaymentHistory, Long> {

	public Page<CustomerPaymentHistory> findAll(Specification<CustomerPaymentHistory> searchSpecification, Pageable pageable);

	public Page<CustomerPaymentHistory> findByCustomerIdAndModifyTimeBetween(Long customerId, Date startDate,
			Date endDate, Pageable pageable);

	public Page<CustomerPaymentHistory> findByCustomerIdAndModifyTimeBefore(Long customerId, Date startDate,
			Pageable pageable);


}
