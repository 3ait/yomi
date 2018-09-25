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

import com.datas.easyorder.db.entity.Customer;
import com.datas.utils.SearchForm;
import com.plugin.utils.DateHelper;

public class CustomerSpecifications {

	public static Specification<Customer> getSearchSpecification(SearchForm searchForm,String type) {

		return new Specification<Customer>() {

			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				Path<Long> id = root.get("id");
				Path<String> name = root.get("name");
				Path<String> companyName = root.get("companyName");
				Path<String> email = root.get("email");
				Path<String> phone = root.get("phone");
				Path<String> customerType = root.get("customerType");
				Path<String> tel = root.get("tel");
				Path<Date> createTime = root.get("createTime");
				Path<Integer> status = root.get("status");
				List<Predicate> list = new ArrayList<>();
				list.add(criteriaBuilder.equal(customerType,type));
				
				
				if (StringHelper.isNotEmpty(searchForm.getQ())) {
					Predicate predicate = criteriaBuilder.or(
							criteriaBuilder.like(name, "%" + searchForm.getQ() + "%"),
							criteriaBuilder.like(tel, "%" + searchForm.getQ() + "%"),
							criteriaBuilder.like(email, "%" + searchForm.getQ() + "%"),
							criteriaBuilder.like(companyName, "%" + searchForm.getQ() + "%"),
							criteriaBuilder.like(phone, "%" + searchForm.getQ() + "%"));
					
					Pattern pattern = Pattern.compile("[0-9]*");
					Matcher isNum = pattern.matcher(searchForm.getQ());
					if (isNum.matches()) {
						criteriaBuilder.or(predicate,criteriaBuilder.equal(id, searchForm.getQ()));
					}
					list.add(predicate);
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
}
