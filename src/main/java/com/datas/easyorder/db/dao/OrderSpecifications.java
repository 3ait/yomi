package com.datas.easyorder.db.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.internal.util.StringHelper;
import org.springframework.data.jpa.domain.Specification;

import com.datas.easyorder.db.entity.Customer;
import com.datas.easyorder.db.entity.Order;
import com.datas.utils.SearchForm;
import com.plugin.utils.DateHelper;

public class OrderSpecifications {

	public static Specification<Order> getSearchSpecification(SearchForm searchForm) {

		return new Specification<Order>() {

			@Override
			public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				
				Path<Long> id = root.get("id");
				Join<Order, Customer> customerJoin = root.join("customerByCustomerId", JoinType.LEFT);
				Path<String> customerName = customerJoin.get("name");
				Path<String> trackingNum = root.get("trackingNum");
				Path<String> fromName = root.get("fromName");
				Path<String> fromPhone = root.get("fromPhone");
				Path<String> toCustomerName = root.get("toCustomerName");
				Path<String> toCustomerCompanyName = root.get("toCustomerCompanyName");
				Path<String> toPhone = root.get("toPhone");
				Path<String> toTel = root.get("toTel");
				Path<Date> createTime = root.get("createTime");
				Path<Integer> status = root.get("status");
				Path<Integer> isPaid = root.get("isPaid");
				Path<Long> salesId = root.join("customerBySalesId", JoinType.LEFT).get("id");

				List<Predicate> list = new ArrayList<>();
				if (StringHelper.isNotEmpty(searchForm.getQ())) {
					Predicate predicate = criteriaBuilder.or(
							criteriaBuilder.like(customerName, "%" + searchForm.getQ() + "%"),
							criteriaBuilder.like(trackingNum, "%" + searchForm.getQ() + "%"),
							criteriaBuilder.like(fromName, "%" + searchForm.getQ() + "%"),
							criteriaBuilder.like(fromPhone, "%" + searchForm.getQ() + "%"),
							criteriaBuilder.like(toCustomerName, "%" + searchForm.getQ() + "%"),
							criteriaBuilder.like(toCustomerCompanyName, "%" + searchForm.getQ() + "%"),
							criteriaBuilder.like(toTel, "%" + searchForm.getQ() + "%"),
							criteriaBuilder.like(toPhone, "%" + searchForm.getQ() + "%"));
					
					
					Pattern pattern = Pattern.compile("[0-9]*");
					Matcher isNum = pattern.matcher(searchForm.getQ());
					if (isNum.matches()) {
						Predicate predicate3 = criteriaBuilder.or(predicate,criteriaBuilder.equal(id, searchForm.getQ()));
						list.add(predicate3);
					}else{
						list.add(predicate);
					}
				}
				
				if(searchForm.getStatus()!=-1){
					Predicate predicate = criteriaBuilder.equal(status, searchForm.getStatus());
					list.add(predicate);
				}
				if(searchForm.getIsPaid()!=-1){
					Predicate predicate = criteriaBuilder.equal(isPaid, searchForm.getIsPaid());
					list.add(predicate);
				}
				if(searchForm.getSalesId()!=-1){
					Predicate predicate = criteriaBuilder.equal(salesId, searchForm.getSalesId());
					list.add(predicate);
				}
				
				
				if (searchForm.getDateTo() != null && searchForm.getDateFrom() != null) {

					Calendar calendar = Calendar.getInstance();
					calendar.setTime(DateHelper.parseYYYYMMDD(searchForm.getDateTo()));
					calendar.add(Calendar.DATE, 1);

					Predicate predicate = criteriaBuilder.between(createTime,
							DateHelper.parseYYYYMMDD(searchForm.getDateFrom()), calendar.getTime());
					list.add(predicate);
				}

				Predicate[] Predicates = new Predicate[] {};
				return criteriaBuilder.and(list.toArray(Predicates));
			}
		};
	}
}
