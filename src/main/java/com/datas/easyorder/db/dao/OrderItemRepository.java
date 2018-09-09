package com.datas.easyorder.db.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.datas.easyorder.db.entity.OrderItem;

@Component("orderItemRepository")
public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
	 
	Page<OrderItem> findByOrderId(Long orderId,Pageable pageRequest);

	
	/**
	 * 
	 * @param dateFrom
	 * @param dateTo
	 * @param status order status
	 * @return
	 * SELECT oi.productId, oi.productNameEn SUM(oi.num)
	 */
	@Query(value="SELECT oi.product_Id, oi.product_name_cn, sum(oi.num)  FROM order_item oi, `order` o  WHERE oi.order_Id=o.id and  o.create_Time >= ?1 and o.create_Time <= ?2  and o.status <> ?3   GROUP BY oi.product_Id ORDER BY SUM(oi.num) DESC limit ?4",nativeQuery=true)
	public List<Object[]> findTopProductByCreateTimeBetween(Date dateFrom, Date dateTo,Integer status,Integer topProductNum);


	/**
	 * 获取单个产品的任意时间短的销售记录图
	 * @param productId
	 * @param dateFrom
	 * @param dateTo
	 * @param statusCanceled
	 * @return
	 */
	@Query(value="SELECT date_format(o.create_time,'%d/%m/%Y'), oi.product_Id, oi.product_Name_En, sum(oi.num)  FROM order_item oi, `order` o  WHERE oi.product_Id=?1 and oi.order_Id=o.id and o.create_Time >= ?2 and o.create_Time <= ?3  and o.status <> ?4 GROUP BY date_format(o.create_time,'%d/%m/%Y')  ORDER BY SUM(oi.num) DESC",nativeQuery=true)
	List<Object[]> findProductByIdAndCreateTimeBetween(Long productId, Date dateFrom, Date dateTo, Integer statusCanceled);
	
	
	/**
	 * 获取多个产品一段时间内销量的的总和报表
	 * @param productId
	 * @param dateFrom
	 * @param dateTo
	 * @param statusCanceled
	 * @return oi.product_Id, oi.product_Name_En, sum(oi.num)
	 */
	@Query(value="SELECT oi.product_Id, oi.product_Name_cn, sum(oi.num)  FROM order_item oi, `order` o  WHERE oi.product_Id IN ?1 and oi.order_Id=o.id and o.create_Time >= ?2 and o.create_Time <= ?3  and o.status <> ?4  GROUP BY oi.product_Id  ORDER BY SUM(oi.num) DESC",nativeQuery=true)
	List<Object[]> findProductByIdAndCreateTimeBetween(Long[] productIds, Date dateFrom, Date dateTo, Integer statusCanceled);


	List<OrderItem> findAllByOrderId(Long id);
}
