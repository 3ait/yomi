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

import com.datas.easyorder.db.entity.Article;
import com.datas.easyorder.db.entity.Menu;
import com.datas.utils.SearchForm;
import com.plugin.utils.DateHelper;

public class ArticleSpecifications {

	/**
	 * 
	 * @param searchForm
	 * @return
	 */
	public static Specification<Article> getSearchSpecification(SearchForm searchForm) {

		return new Specification<Article>() {

			@Override
			public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				Path<Long> id = root.get("id");
				Join<Article, Menu> menuJoin = root.join("menu", JoinType.LEFT);
				Path<Long> menuId = menuJoin.get("id");
				Path<String> title = root.get("title");
				Path<String> subTitle = root.get("subTitle");
				Path<String> content = root.get("content");
				Path<String> keywords = root.get("keywords");
				Path<Date> createTime = root.get("createTime");
				Path<Byte> status = root.get("status");

				List<Predicate> list = new ArrayList<>();
				if (StringHelper.isNotEmpty(searchForm.getQ())) {
					Predicate predicate = criteriaBuilder.or(
							criteriaBuilder.like(title, "%" + searchForm.getQ() + "%"),
							criteriaBuilder.like(subTitle, "%" + searchForm.getQ() + "%"),
							criteriaBuilder.like(keywords, "%" + searchForm.getQ() + "%"),
							criteriaBuilder.like(content, "%" + searchForm.getQ() + "%"));
					
					Pattern pattern = Pattern.compile("[0-9]*");
					Matcher isNum = pattern.matcher(searchForm.getQ());
					if (isNum.matches()) {
						Predicate predicate3 = criteriaBuilder.or(predicate,criteriaBuilder.equal(id, searchForm.getQ()));
						list.add(predicate3);
					}else{
						list.add(predicate);
					}
				}
				
				if(searchForm.getStatus()==0){
					Predicate predicate = criteriaBuilder.notEqual(status, new Byte(searchForm.getStatus()+""));
					list.add(predicate);
				}
				
				if(searchForm.getMenu3Id()!=-1){
					Predicate predicate = criteriaBuilder.equal(menuId, searchForm.getMenu3Id());
					list.add(predicate);
				}else if(searchForm.getMenu2Id()!=-1){
					Predicate predicate = criteriaBuilder.equal(menuId, searchForm.getMenu2Id());
					list.add(predicate);
				}else if(searchForm.getMenu1Id()!=-1){
					Predicate predicate = criteriaBuilder.equal(menuId, searchForm.getMenu1Id());
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
