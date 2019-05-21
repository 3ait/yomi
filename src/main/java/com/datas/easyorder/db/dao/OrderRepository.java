package com.datas.easyorder.db.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.datas.easyorder.db.entity.Order;

@Component("orderRepository")
public interface OrderRepository extends CrudRepository<Order, Long> {
	 
	/**
	 * 支付commission（0：未支付
	 */
	public static final Integer salesCommissionStatus_unpaid = 0;
	/**
	 * 支付commission（1：支付
	 */
	public static final Integer salesCommissionStatus_paid = 1;
	/**
	 * 订单状态（1：下单，
	 */
	public static final Integer status_new = 1;
	/**
	 * 2：已付款，
	 */
	public static final Integer status_hold = 2;
	/**
	 * 3：已发货，
	 */
	public static final Integer status_send = 3;
	
	/**
	 * 4：已发货，
	 */
	public static final Integer status_complete = 4;
	
	/**
	 * 5 back order
	 */
	public static final Integer status_backorder = 5;

	/**
	 * 0：已取消）
	 */
	public static final Integer status_canceled = 0;

	public static final Integer ispaid_no = 0;
	public static final Integer ispaid_paidInPart = 1;
	public static final Integer ispaid_yes = 2;
	
	
	public Page<Order> findAll(Specification<Order> specification,Pageable pageable);

	public Page<Order> findByCustomerByCustomerIdId(Long id, Pageable pageable);
	
	public Page<Order> findBycustomerBySalesIdIdAndStatus(Long id,Integer status, Pageable pageable);

	@Query("SELECT date_format(createTime,'%d/%m/%Y'), count(id),SUM(totalProductPrice)  FROM Order WHERE createTime >= ?1 and createTime <= ?2  and status <> ?3   GROUP BY date(createTime)")
	public List<Object[]> findByCreateTimeBetweenAndStatusNot(Date dateFrom, Date dateTo,Integer status,Sort sort);

	public Page<Order> findByCustomerBySalesIdIdAndStatusNotAndCreateTimeBetween(Long customerId, Integer statusCanceled,Date start, Date end,Pageable pageable);

	public Order findOneByIdAndCustomerBySalesIdId(Long orderId, Long customerSalesId);
	
	public Order findOneByIdAndCustomerByCustomerIdId(Long orderId, Long customerId);
	
	@Query("SELECT date_format(o.createTime,'%d/%m/%Y'), count(o.id),SUM(o.totalProductPrice) ,customerBySalesId.id,customerBySalesId.name FROM Order o  WHERE   o.createTime >= ?1 and o.createTime <= ?2  and o.status <> ?3   GROUP BY o.customerBySalesId.id, date(o.createTime)")
	public List<Object[]> findSalesOrderByCreateTimeBetween(Date dateFrom, Date dateTo,Integer status);

	public List<Order> findByIdIn(Long[] orderIds);

	public Page<Order> findByCustomerByCustomerIdIdOrCustomerBySalesIdId(Long id, Long id2, Pageable pageable);
	
}
