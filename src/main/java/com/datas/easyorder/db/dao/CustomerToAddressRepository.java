package com.datas.easyorder.db.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.datas.easyorder.db.entity.CustomerToAddress;

public interface CustomerToAddressRepository extends CrudRepository<CustomerToAddress, Long> {

	Page<CustomerToAddress> findByCustomerId(Long customerId, Pageable pageable);

	List<CustomerToAddress> findByToNameAndToAddress(String toName,String toAddress);

	
}
