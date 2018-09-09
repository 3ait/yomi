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

import com.datas.easyorder.db.entity.Invoice;
import com.datas.utils.SearchForm;
import com.plugin.utils.DateHelper;

public class InvoiceSpecifications {

	public static Specification<Invoice> getSearchSpecification(SearchForm searchForm) {

		return new Specification<Invoice>() {

			@Override
			public Predicate toPredicate(Root<Invoice> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				Path<Long> id = root.get("id");
				Path<String> customerCompanyName = root.get("customerCompanyName");
				Path<String> customerCompanyPhone = root.get("customerCompanyPhone");
				Path<String> customerEmail = root.get("customerEmail");
				Path<String> userCompanyName = root.get("userCompanyName");
				Path<String> userCompanyTel = root.get("userCompanyTel");
				Path<String> aliasId = root.get("aliasId");
				Path<Integer> status = root.get("status");
				Path<Byte> paid = root.get("paid");
				Path<Date> createTime = root.get("createTime");

				List<Predicate> list = new ArrayList<>();
				if (StringHelper.isNotEmpty(searchForm.getQ())) {
					Predicate predicate = criteriaBuilder.or(
							criteriaBuilder.like(customerCompanyName, "%" + searchForm.getQ() + "%"),
							criteriaBuilder.like(customerCompanyPhone, "%" + searchForm.getQ() + "%"),
							criteriaBuilder.like(customerEmail, "%" + searchForm.getQ() + "%"),
							criteriaBuilder.like(userCompanyName, "%" + searchForm.getQ() + "%"),
							criteriaBuilder.like(aliasId, "%" + searchForm.getQ() + "%"),
							criteriaBuilder.like(userCompanyTel, "%" + searchForm.getQ() + "%")
							);
					
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
				if(searchForm.getIsPaid()!=-1){
					Predicate predicate = criteriaBuilder.equal(paid, (byte)searchForm.getIsPaid());
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
