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

import com.datas.easyorder.db.entity.Attachment;
import com.datas.easyorder.db.entity.Menu;
import com.datas.easyorder.db.entity.Product;
import com.datas.utils.SearchForm;
import com.plugin.utils.DateHelper;

public class AttachmentSpecifications {

	/**
	 * 
	 * @param searchForm
	 * @return
	 */
	public static Specification<Attachment> getSearchSpecification(SearchForm searchForm) {

		return new Specification<Attachment>() {

			@Override
			public Predicate toPredicate(Root<Attachment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				Path<Long> id = root.get("id");
				Join<Attachment, Menu> menuJoin = root.join("menu", JoinType.LEFT);
				Path<Long> menuId = menuJoin.get("id");
				
				Path<Product> product = root.get("product");
				Path<String> title = root.get("title");
				Path<String> fileName = root.get("fileName");
				Path<Date> createTime = root.get("createTime");

				List<Predicate> list = new ArrayList<>();
				if (StringHelper.isNotEmpty(searchForm.getQ())) {
					Predicate predicate = criteriaBuilder.or(
							criteriaBuilder.like(title, "%" + searchForm.getQ() + "%"),
							criteriaBuilder.like(fileName, "%" + searchForm.getQ() + "%"));
					
					Pattern pattern = Pattern.compile("[0-9]*");
					Matcher isNum = pattern.matcher(searchForm.getQ());
					if (isNum.matches()) {
						Predicate predicate3 = criteriaBuilder.or(predicate,criteriaBuilder.equal(id, searchForm.getQ()));
						list.add(predicate3);
					}else{
						list.add(predicate);
					}
				}
				
				//product Id ==null
				//只选取非产品图片的附件
				Predicate predicateProduct = criteriaBuilder.isNull(product);
				list.add(predicateProduct);
				
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
