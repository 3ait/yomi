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
import com.datas.easyorder.db.entity.CustomerPaymentHistory;
import com.datas.easyorder.db.entity.Invoice;
import com.datas.utils.SearchForm;
import com.plugin.utils.DateHelper;

public class CustomerPaymentHistorySpecifications {

	public static Specification<CustomerPaymentHistory> getSearchSpecification(SearchForm searchForm) {

		return new Specification<CustomerPaymentHistory>() {

			@Override
			public Predicate toPredicate(Root<CustomerPaymentHistory> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				Path<Long> id = root.get("id");
				Join<CustomerPaymentHistory, Invoice> invoiceJoin = root.join("invoice", JoinType.LEFT);
				Path<String> aliasId = invoiceJoin.get("aliasId");
				Join<CustomerPaymentHistory, Customer> customerJoin = root.join("customer", JoinType.LEFT);
				Path<Long> customerId = customerJoin.get("id");
				Path<String> customerJoinEmail = customerJoin.get("email");
				Path<String> customerJoinName = customerJoin.get("name");
				Path<String> customerJoinCompanyName = customerJoin.get("companyName");
				Path<String> memo = root.get("memo");
				
				Path<Date> createTime = root.get("createTime");

				List<Predicate> list = new ArrayList<>();
				if (StringHelper.isNotEmpty(searchForm.getQ())) {
					Predicate predicate = criteriaBuilder.or(
							criteriaBuilder.like(memo, "%" + searchForm.getQ() + "%"),
							criteriaBuilder.like(aliasId, searchForm.getQ()),
							criteriaBuilder.like(customerJoinEmail, searchForm.getQ()),
							criteriaBuilder.like(customerJoinCompanyName, searchForm.getQ()),
							criteriaBuilder.like(customerJoinName, searchForm.getQ())
							);
					
					Pattern pattern = Pattern.compile("[0-9]*");
					Matcher isNum = pattern.matcher(searchForm.getQ());
					if (isNum.matches()) {
						criteriaBuilder.or(predicate,criteriaBuilder.equal(id, searchForm.getQ()));
						criteriaBuilder.or(predicate,criteriaBuilder.equal(customerId, searchForm.getQ()));
					}
					
					
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
