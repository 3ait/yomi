package com.datas.easyorder.db.dao;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.datas.easyorder.db.entity.Coupon;

@Component("couponRepository")
public interface CouponRepository extends CrudRepository<Coupon, Long> {
	
	public static final Integer STATUS_ACTIVE = 1;
	public static final Integer STATUS_INACTIVE = 0;
	public static final String TYPE_DISCOUNT = "discount";
	public static final String TYPE_CASH = "cash";

	public Page<Coupon> findAll(Specification<Coupon> searchSpecification, Pageable pageable);

	public Coupon findByCode(String code);

	public Coupon findByIdAndStatusAndExpiredTimeAfter(Long couponId, Integer statusActive, Date expiredDate);

	public Coupon findByCodeAndStatusAndExpiredTimeAfter(String code, Integer statusActive, Date expiredDate);

	public Coupon findOneByIdAndStatusAndExpiredTimeAfter(long long1, Integer statusActive, Date time);
    
}
