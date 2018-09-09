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

import com.datas.easyorder.db.entity.User;
import com.datas.easyorder.db.entity.UserCompany;
import com.datas.utils.SearchForm;
import com.plugin.utils.DateHelper;

public class UserSpecifications {

	public static Specification<User> getSearchSpecification(SearchForm searchForm) {

		return new Specification<User>() {

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				Path<Long> id = root.get("id");
				Path<String> name = root.get("name");
				Path<String> email = root.get("email");
				Path<String> phone = root.get("phone");
				Join<User, UserCompany> userCompanyJoin = root.join("userCompany", JoinType.LEFT);
				Path<String> companyName = userCompanyJoin.get("name");
				Path<String> companyEmail = userCompanyJoin.get("email");
				Path<String> companyMobile = userCompanyJoin.get("mobile");
				Path<Date> createTime = root.get("createTime");

				List<Predicate> list = new ArrayList<>();
				if (StringHelper.isNotEmpty(searchForm.getQ())) {
					Predicate predicate = criteriaBuilder.or(
							criteriaBuilder.like(name, "%" + searchForm.getQ() + "%"),
							criteriaBuilder.like(email, "%" + searchForm.getQ() + "%"),
							criteriaBuilder.like(phone, "%" + searchForm.getQ() + "%"),
							criteriaBuilder.like(companyName, "%" + searchForm.getQ() + "%"),
							criteriaBuilder.like(companyMobile, "%" + searchForm.getQ() + "%"),
							criteriaBuilder.like(companyEmail, "%" + searchForm.getQ() + "%"));
					
					Pattern pattern = Pattern.compile("[0-9]*");
					Matcher isNum = pattern.matcher(searchForm.getQ());
					if (isNum.matches()) {
						criteriaBuilder.or(predicate,criteriaBuilder.equal(id, searchForm.getQ()));
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
