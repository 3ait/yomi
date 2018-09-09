package com.datas.easyorder.controller.administrator.customer.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.datas.easyorder.controller.BaseLogic;
import com.datas.easyorder.controller.administrator.customer.CommissionView;
import com.datas.easyorder.db.dao.CustomerCommissionRepository;
import com.datas.easyorder.db.dao.OrderRepository;
import com.datas.easyorder.db.entity.CustomerCommission;
import com.datas.easyorder.db.entity.Order;
import com.datas.easyorder.db.entity.User;
import com.datas.utils.SearchForm;
import com.plugin.utils.DateHelper;

/**
 * 
 * @author leo
 *
 */
@Component("customerCommissionLogic")
public class CustomerCommissionLogic extends BaseLogic<CustomerCommission>{

	@Autowired
	private CustomerCommissionRepository customerCommissionRepository;
	@Autowired
	OrderRepository orderRepository;
	
	

	/**
	 * 获取commission支付，右连接order
	 * @param customerId
	 * @param searchForm
	 * @return List<CommissionView>
	 */
	@Transactional(rollbackOn=Exception.class)
	public List<CommissionView> getCommissionPayment(long customerId,SearchForm searchForm){
		List<CommissionView> list = new ArrayList<>();

		Date startDate = DateHelper.parseYYYYMMDD(searchForm.getDateFrom());
		Calendar dateToCalendar = Calendar.getInstance();
		dateToCalendar.setTime(DateHelper.parseYYYYMMDD(searchForm.getDateTo()));
		dateToCalendar.add(Calendar.DATE, 1);
		
		Page<CustomerCommission> customerCommissionPage = customerCommissionRepository.findByCustomerIdAndCreateTimeBetween(customerId, startDate,dateToCalendar.getTime(), new PageRequest(0, Integer.MAX_VALUE,Direction.DESC,"id"));
		customerCommissionPage.getContent().forEach(cc -> {
			CommissionView commissionView = new CommissionView();
			commissionView.setCustomerCommission(cc);
			commissionView.setCreateTime(cc.getCreateTime());
			list.add(commissionView);
		});
		
		
		Page<Order> orderPage = orderRepository.findByCustomerBySalesIdIdAndStatusNotAndCreateTimeBetween(customerId, OrderRepository.status_canceled,startDate,dateToCalendar.getTime(), new PageRequest(0, Integer.MAX_VALUE,Direction.DESC,"id"));
		orderPage.getContent().forEach(o -> {
			CommissionView commissionView = new CommissionView();
			commissionView.setOrder(o);
			commissionView.setCreateTime(o.getCreateTime());
			list.add(commissionView);
		});
		
		list.stream().sorted((t1,t2) -> Integer.valueOf((t2.getCreateTime().getTime() - t1.getCreateTime().getTime())+""));
		
		
		return list;
	}


	@Override
	public CrudRepository<CustomerCommission, Long> getRepository() {
		return customerCommissionRepository;
	}


	/**
	 * 保存commission
	 * @param orderIds
	 * @param customerCommission
	 */
	@Transactional(rollbackOn=Exception.class)
	public void saveCommission(User user, Long[] orderIds, CustomerCommission customerCommission) {
		
		
		for(Long id: orderIds){
			if(id==null){
				continue;
			}
			Order order = orderRepository.findOne(id);
			if(order!=null){
				order.setSalesCommissionStatus(orderRepository.salesCommissionStatus_paid);
				order.setModifyTime(Calendar.getInstance().getTime());
				order.setUser(user);
				orderRepository.save(order);
			}
			
		}
		customerCommission.setUser(user);
		customerCommission.setCreateTime(Calendar.getInstance().getTime());
		customerCommission.setModifyTime(Calendar.getInstance().getTime());
		
		customerCommissionRepository.save(customerCommission);
		
	}
	
}
