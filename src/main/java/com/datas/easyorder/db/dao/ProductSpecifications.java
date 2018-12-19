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

import com.datas.easyorder.db.entity.Menu;
import com.datas.easyorder.db.entity.Product;
import com.datas.easyorder.db.entity.ProductAttr;
import com.datas.utils.SearchForm;
import com.plugin.utils.DateHelper;

public class ProductSpecifications {

	/**
	 * 
	 * @param searchForm
	 * @return
	 */
	public static Specification<Product> getSearchSpecification(SearchForm searchForm) {

		return new Specification<Product>() {

			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				Path<Long> id = root.get("id");
				Join<Product, Menu> menuJoin = root.join("menu", JoinType.LEFT);
				Path<Long> menuId = menuJoin.get("id");
				Path<String> productName = root.get("productName");
				Path<String> productNameAlias = root.get("productNameAlias");
				Path<String> mpn = root.get("mpn");
				Path<Date> createTime = root.get("createTime");
				Path<Byte> status = root.get("status");

				List<Predicate> list = new ArrayList<>();
				if (StringHelper.isNotEmpty(searchForm.getQ())) {
					Predicate predicate = criteriaBuilder.or(
							criteriaBuilder.like(mpn, "%" + searchForm.getQ() + "%"),
							criteriaBuilder.like(productName, "%" + searchForm.getQ() + "%"),
							criteriaBuilder.like(productNameAlias, "%" + searchForm.getQ() + "%"));
					
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
					Predicate predicate = criteriaBuilder.notEqual(status, new Byte(searchForm.getStatus() + ""));
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
	
	
	
	/**
	 * 
	 * @param searchForm
	 * @return
	 */
	public static Specification<Product> getByIdsInAndStatusInAndQ(List<Long> ids,Byte[] status,String q) {

		return new Specification<Product>() {

			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				Path<Long> id = root.get("id");
				Path<String> productName = root.get("productName");
				Path<String> productNameAlias = root.get("productNameAlias");
				Path<String> mpn = root.get("mpn");
				Path<Byte> status = root.get("status");

				List<Predicate> list = new ArrayList<>();
				
				
				if (StringHelper.isNotEmpty(q)) {
					Predicate predicate = criteriaBuilder.or(
							criteriaBuilder.like(mpn, "%" + q + "%"),
							criteriaBuilder.like(productName, "%" + q + "%"),
							criteriaBuilder.like(productNameAlias, "%" + q + "%"));
					
					Pattern pattern = Pattern.compile("[0-9]*");
					Matcher isNum = pattern.matcher(q);
					if (isNum.matches()) {
						Predicate predicate3 = criteriaBuilder.or(predicate,criteriaBuilder.equal(id, q));
						list.add(predicate3);
					}else{
						list.add(predicate);
					}
				}
				if(ids!=null){
					Predicate predicate = id.in(ids);
					list.add(predicate);
				}
				if(status!=null ){
					Predicate predicate = status.in(status);
					list.add(predicate);
				}
				
				
				Predicate[] Predicates = new Predicate[] {};
				return criteriaBuilder.and(list.toArray(Predicates));
			}
		};
	}


	/**
	 * 首页检索
	 * @param searchForm
	 * @param categoryId
	 * @param productAttributeValueId
	 * @return Specification<Product>
	 */
	public static Specification<Product> productWebList(SearchForm searchForm, Long[] categoryId, Long[] productAttributeValueId) {
		return new Specification<Product>() {

			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				Path<Long> id = root.get("id");
				Path<String> productName = root.get("productName");
				Path<String> productNameAlias = root.get("productNameAlias");
				Path<String> mpn = root.get("mpn");
				Path<Date> createTime = root.get("createTime");
				Path<Byte> status = root.get("status");
				
				Join<Product, Menu> menuJoin = root.join("menu", JoinType.INNER);
				Path<Long> menuId = menuJoin.get("id");
				Join<Product, ProductAttr> productAttrsJoin = root.join("productAttrs", JoinType.RIGHT);

				List<Predicate> list = new ArrayList<>();
				if (StringHelper.isNotEmpty(searchForm.getQ())) {
					Predicate predicate = criteriaBuilder.or(
							criteriaBuilder.like(mpn, "%" + searchForm.getQ() + "%"),
							criteriaBuilder.like(productName, "%" + searchForm.getQ() + "%"),
							criteriaBuilder.like(productNameAlias, "%" + searchForm.getQ() + "%"));
					
					Pattern pattern = Pattern.compile("[0-9]*");
					Matcher isNum = pattern.matcher(searchForm.getQ());
					if (isNum.matches()) {
						Predicate predicate3 = criteriaBuilder.or(predicate,criteriaBuilder.equal(id, searchForm.getQ()));
						list.add(predicate3);
					}else{
						list.add(predicate);
					}
				}
				
				if(searchForm.getStatus()==ProductRepository.status_cancelled){
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
