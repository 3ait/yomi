package com.datas.easyorder.db.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.datas.easyorder.db.entity.CouponOrder;

@Component("couponOrderRepository")
public interface CouponOrderRepository extends CrudRepository<CouponOrder, Long> {


	Page<CouponOrder> findByCouponId(Long couponId, Pageable pageable);

	CouponOrder findByOrderId(Long orderId);

    
}
