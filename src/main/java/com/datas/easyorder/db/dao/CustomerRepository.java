package com.datas.easyorder.db.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import com.datas.easyorder.db.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	public static final String customerType_customer = "customer";
	public static final String customerType_sales = "sales";
	public static final byte status_inactive = 0;
	public static final byte status_active = 1;
	
	
	Customer findOneByEmailAndPassword(String email, String password);

	Customer findOneByEmail(String registerEmail);
	
	Page<Customer> findAll(Specification<Customer> specification, Pageable pageable);

	Page<Customer> findByCompanyNameContainingOrNameContaining(String companyName,String Name, Pageable pageable);

	List<Customer> findAllByCustomerTypeAndStatus(String customertypeSales, byte status1);

	public Page<Customer> findByCompanyNameContainingAndCustomerType(String q, String customertype, Pageable pageable);

	Customer findOneByEmailAndPasswordAndStatus(String username, String md5String,byte statusActive);

}
