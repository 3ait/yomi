package com.datas.easyorder.controller.administrator.coupon.logic;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.datas.easyorder.controller.BaseLogic;
import com.datas.easyorder.controller.administrator.coupon.CouponView;
import com.datas.easyorder.db.dao.CouponCustomerRepository;
import com.datas.easyorder.db.dao.CouponOrderRepository;
import com.datas.easyorder.db.dao.CouponRepository;
import com.datas.easyorder.db.dao.CouponSpecifications;
import com.datas.easyorder.db.entity.Coupon;
import com.datas.easyorder.db.entity.CouponCustomer;
import com.datas.easyorder.db.entity.CouponOrder;
import com.datas.easyorder.db.entity.Customer;
import com.datas.easyorder.db.entity.Order;
import com.datas.utils.SearchForm;
import com.plugin.utils.DateHelper;

@Component
public class CouponLogic  extends BaseLogic<Coupon> {
	
	@Autowired
	CouponRepository couponRepository;
	@Autowired
	CouponOrderRepository couponOrderRepository;
	@Autowired
	CouponCustomerRepository couponCustomerRepository;
	
	
	@Override
	public CrudRepository<Coupon, Long> getRepository() {
		return this.couponRepository;
	}

	/**
	 * admin
	 * @param searchForm
	 * @param pageable
	 * @return
	 */
	public Page<Coupon> list(SearchForm searchForm, Pageable pageable) {
		return couponRepository.findAll(CouponSpecifications.getSearchSpecification(searchForm),pageable);
	}


	public void save(Coupon coupon, String expiredDate) {
		coupon.setCreateTime(Calendar.getInstance().getTime());
		
		coupon.setExpiredTime(DateHelper.parseYYYYMMDD(expiredDate));
		
		couponRepository.save(coupon);
	}

	/**
	 * 前端列出所有有效优惠券
	 * @param searchForm
	 * @param pageable
	 * @return Page<Coupon>
	 */
	public List<CouponView> webListAll(SearchForm searchForm,Customer customer,Pageable pageable) {
		Page<Coupon> pageCoupon = couponRepository.findAll(CouponSpecifications.webList(searchForm),pageable);
		
		List<CouponView> list = new ArrayList<>();
		pageCoupon.getContent().forEach(c -> {
			CouponView cv = new CouponView();
			cv.setCoupon(c);
			cv.setCollected(false);
			
			if(customer!=null){
				CouponCustomer couponCustomer = couponCustomerRepository.findOneByCouponIdAndCustomerId(c.getId(), customer.getId());
				if(couponCustomer!=null){
					cv.setCollected(true);
				}
			}
			list.add(cv);
		});
		return list;
	}

	/**
	 * 
	 * @param code
	 * @return Coupon
	 */
	public Coupon getByCode(String code) {
		return couponRepository.findByCode(code);
	}

	/**
	 * 获取用户所有优惠券列表
	 * @param id
	 * @return
	 */
	@Transactional(rollbackOn=Exception.class)
	public List<Coupon> findCouponByCustomerId(Long customerId) {
		List<CouponCustomer> list = couponCustomerRepository.findAllByCustomerId(customerId);
		
		List<Coupon> cList = new ArrayList<>();
		list.forEach(cc -> {
			cList.add(cc.getCoupon());
		});
		return cList;
	}
	
	/**
	 * 获取用户有效优惠券
	 * @param id
	 * @return
	 */
	@Transactional(rollbackOn=Exception.class)
	public List<Coupon> findAvailableCouponByCustomerId(Long customerId) {
		List<CouponCustomer> list = couponCustomerRepository.findAllByCustomerIdAndStatus(customerId,CouponCustomerRepository.STATUS_1);
		
		List<Coupon> cList = new ArrayList<>();
		list.forEach(cc -> {
			cList.add(cc.getCoupon());
		});
		return cList;
	}

	public Coupon getById(Long id) {
		return couponRepository.findOne(id);
	}

	/**
	 * 查询还有 在 dayNum 天过期的优惠券
	 * @param id
	 * @param dayNum
	 * @return
	 */
	public List<Coupon> findCouponByCustomerIdAndExpiredDate(Long customerId, Integer before) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, before);
		
		List<CouponCustomer> list = couponCustomerRepository.findAllByCustomerIdAndCouponExpiredTimeBefore(customerId,calendar.getTime());
		
		List<Coupon> cList = new ArrayList<>();
		list.forEach(cc -> cList.add(cc.getCoupon()));
		return cList;
	}

	public Page<CouponOrder> getCouponOrderByCouponId(Long couponId,Pageable pageable) {
		
		return couponOrderRepository.findByCouponId(couponId,pageable);
	}

	public Page<CouponCustomer> getCouponCustomerByCouponId(Long couponId, Pageable pageable) {
		return couponCustomerRepository.findByCouponId(couponId,pageable);
	}

	
	/**
	 * 检查CouponId检查用户是否领取优惠券，如果领取成功返回True,如果失败，返回false
	 * @param couponId
	 * @param customerId
	 * @return
	 */
	@Transactional(rollbackOn=Exception.class)
	public CouponView collectCouponById(Long couponId, Customer customer) {
		
		CouponView couponView = new CouponView();
		
		Date expiredDate = Calendar.getInstance().getTime();
		Coupon coupon = couponRepository.findByIdAndStatusAndExpiredTimeAfter(couponId,CouponRepository.STATUS_ACTIVE,expiredDate);
		if(coupon!=null){
			couponView.setCoupon(coupon);
		}
		
		
		CouponCustomer couponCustomer = couponCustomerRepository.findOneByCouponIdAndCustomerId(couponId,customer.getId());
		if(couponCustomer==null && coupon !=null){
			couponCustomer = new CouponCustomer();
			couponCustomer.setCustomer(customer);
			couponCustomer.setCoupon(coupon);
			couponCustomer.setStatus(CouponCustomerRepository.STATUS_1);
			couponCustomerRepository.save(couponCustomer);
			couponView.setCollected(true);
		}else{
			couponView.setCollected(false);
			
		}
		
		return couponView;
		
	}
	
	/**
	 * 检查Coupon code 检查用户是否领取优惠券，如果领取成功返回True,如果失败，返回false
	 * @param couponId
	 * @param customerId
	 * @return
	 */
	@Transactional(rollbackOn=Exception.class)
	public CouponView collectCouponByCode(String code, Customer customer) {
		
		CouponView couponView = new CouponView();
		Date expiredDate = Calendar.getInstance().getTime();
		Coupon coupon = couponRepository.findByCodeAndStatusAndExpiredTimeAfter(code,CouponRepository.STATUS_ACTIVE,expiredDate);
		
		if(coupon!=null){
			couponView.setCoupon(coupon);
		}
		
		
		CouponCustomer couponCustomer = couponCustomerRepository.findOneByCouponCodeAndCustomerId(code,customer.getId());
		if(couponCustomer==null && coupon !=null){
			couponCustomer = new CouponCustomer();
			couponCustomer.setCustomer(customer);
			
			couponCustomer.setCoupon(coupon);
			couponCustomer.setStatus(CouponCustomerRepository.STATUS_1);
			
			couponCustomerRepository.save(couponCustomer);
			couponView.setCollected(true);
		}else{
			couponView.setCollected(false);
			
		}
		

		return couponView;
		
	}
	
	/**
	 * 根据couponId获取打折价格
	 * @param couponId
	 * @param price
	 * @return
	 */
	@Transactional(rollbackOn=Exception.class)
	public Double getDiscountPirceByCouponId(Long couponId ,Order order, Customer customer,Double price){
		Double ret = price;
		Coupon coupon = couponRepository.findOneByIdAndStatusAndExpiredTimeAfter(couponId,CouponRepository.STATUS_ACTIVE,Calendar.getInstance().getTime());
		
		if(coupon!=null){
			CouponOrder couponOrder = new CouponOrder();
			couponOrder.setCoupon(coupon);
			couponOrder.setOrder(order);
			if(coupon.getType().endsWith(CouponRepository.TYPE_CASH)){
				if(coupon.getReg()< price){
					ret -= coupon.getValue().doubleValue() ;
					couponOrderRepository.save(couponOrder);
				}
			}else if(coupon.getType().endsWith(CouponRepository.TYPE_DISCOUNT)){
				if(coupon.getReg()< price){
					ret *=  coupon.getValue().doubleValue();
					couponOrderRepository.save(couponOrder);
				}
			}
			couponOrderRepository.save(couponOrder);
			
		}
		
		return ret;
		
	}
	
	/**
	 * 更新客人优惠券的的状态
	 * @param couponId
	 * @param order
	 * @param customer
	 * @param price
	 */
	public void updateCustomerCoupon(Order order,Integer couponCustomerStatus){
		
		Long customerId = order.getCustomerByCustomerId().getId();
		CouponOrder couponOrder = couponOrderRepository.findByOrderId(order.getId());
		
		if(couponOrder!=null){
			CouponCustomer couponCustomer = couponCustomerRepository.findOneByCouponIdAndCustomerId(couponOrder.getCoupon().getId(),customerId);
			couponCustomer.setStatus(couponCustomerStatus);
			couponCustomerRepository.save(couponCustomer);
			
		}
	}

	/**
	 * 更新任意表字段
	 * @param pk
	 * @param name
	 * @param value
	 */
	@Transactional(rollbackOn=Exception.class)
	public Coupon update(Long id, String atrributeName, String attributeValue) {
		Coupon t = getRepository().findOne(id);
		try {
			Field field = t.getClass().getDeclaredField(atrributeName);
			field.setAccessible(true);
			if(field.getType()  ==  String.class){
				field.set(t, attributeValue);
			}else if(field.getType().equals(Double.class) || field.getType().equals(double.class)){
				field.set(t, Double.valueOf(attributeValue));
			}else if(field.getType().equals(Integer.class) || field.getType().equals(int.class)){
				field.set(t, Integer.valueOf(attributeValue));
			}else if(field.getType().equals(Byte.class) || field.getType().equals(byte.class)){
				field.set(t, Byte.valueOf(attributeValue));
			}else if(field.getType().equals(Customer.class)){
				Customer customer = new Customer();
				customer.setId(Long.valueOf(attributeValue));
				field.set(t, customer);
				
			}
			
			getRepository().save(t);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return t;
	}

}
