package com.datas.easyorder.db.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.datas.easyorder.db.entity.CouponCustomer;

@Component("couponCustomerRepository")
public interface CouponCustomerRepository extends CrudRepository<CouponCustomer, Long> {

	public static final Integer STATUS_1 = 1;
	public static final Integer STATUS_0 = 0;
	
	
	List<CouponCustomer> findAllByCustomerId(Long customerId);

	List<CouponCustomer> findAllByCustomerIdAndCouponExpiredTimeBefore(Long customerId, Date time);


	Page<CouponCustomer> findByCouponId(Long couponId, Pageable pageable);

	CouponCustomer findOneByCouponIdAndCustomerId(Long couponId, Long id);

	CouponCustomer findOneByCouponCodeAndCustomerId(String code, Long id);

	List<CouponCustomer> findAllByCustomerIdAndStatus(Long customerId, Integer status);

}
