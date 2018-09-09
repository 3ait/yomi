package com.datas.easyorder.db.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.internal.util.StringHelper;
import org.springframework.data.jpa.domain.Specification;

import com.datas.easyorder.db.entity.Coupon;
import com.datas.utils.SearchForm;
import com.plugin.utils.DateHelper;

public class CouponSpecifications {

	/**
	 * 
	 * @param searchForm
	 * @return
	 */
	public static Specification<Coupon> getSearchSpecification(SearchForm searchForm) {

		return new Specification<Coupon>() {

			@Override
			public Predicate toPredicate(Root<Coupon> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				Path<Long> id = root.get("id");
				Path<String> code = root.get("code");
				Path<Integer> status = root.get("status");
				Path<Date> createTime = root.get("createTime");

				List<Predicate> list = new ArrayList<>();
				if (StringHelper.isNotEmpty(searchForm.getQ())) {
					Predicate predicate = criteriaBuilder.or(
							criteriaBuilder.like(code, "%" + searchForm.getQ() + "%"));
					
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
				
				if (searchForm.getDateTo() != null && searchForm.getDateFrom() != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(DateHelper.parseYYYYMMDD(searchForm.getDateTo()));
					calendar.add(Calendar.DATE, 1);

					Predicate predicate = criteriaBuilder.between(createTime, DateHelper.parseYYYYMMDD(searchForm.getDateFrom()), calendar.getTime());
					list.add(predicate);
				}

				Predicate[] Predicates = new Predicate[] {};
				return criteriaBuilder.and(list.toArray(Predicates));
			}
		};
	}
	
	
	/**
	 * 
	 * @param searchForm
	 * @return
	 */
	public static Specification<Coupon> webList(SearchForm searchForm) {

		return new Specification<Coupon>() {
			@Override
			public Predicate toPredicate(Root<Coupon> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				Path<Long> id = root.get("id");
				Path<String> code = root.get("code");
				Path<Integer> status = root.get("status");
				Path<Date> expiredTime = root.get("expiredTime");

				List<Predicate> list = new ArrayList<>();
				if (StringHelper.isNotEmpty(searchForm.getQ())) {
					Predicate predicate = criteriaBuilder.or(
							criteriaBuilder.like(code, "%" + searchForm.getQ() + "%"));
					
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
				
				
				
				Calendar calendar = Calendar.getInstance();
				
				
				Predicate predicate = criteriaBuilder.greaterThan(expiredTime, calendar.getTime());
				list.add(predicate);
				

				Predicate[] Predicates = new Predicate[] {};
				return criteriaBuilder.and(list.toArray(Predicates));
			}
		};
	}
}
