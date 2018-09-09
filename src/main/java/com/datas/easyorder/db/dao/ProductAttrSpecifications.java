package com.datas.easyorder.db.dao;

import java.util.ArrayList;
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

import com.datas.easyorder.db.entity.Product;
import com.datas.easyorder.db.entity.ProductAttr;

public class ProductAttrSpecifications {

	public static Specification<ProductAttr> getSearchSpecification(Long categoryId, String q, Long[] attrValueIds) {

		return new Specification<ProductAttr>() {

			@Override
			public Predicate toPredicate(Root<ProductAttr> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				Path<String> productAttrValueId = root.get("productAttrValue").get("id");
				
				Join<ProductAttr,Product> product = root.join("product", JoinType.INNER);
				Path<Long> menuId = product.get("menu").get("id");
				Path<Long> productId = product.get("id");
				Path<String> productName = product.get("productName");
				Path<String> productNameAlias = product.get("productNameAlias");
				Path<String> label = product.get("label");
				
				
				List<Predicate> list = new ArrayList<>();
				if(categoryId>0){
					Predicate predicate = criteriaBuilder.equal(menuId, categoryId);
					list.add(predicate);
				}
				
				if (StringHelper.isNotEmpty(q)) {
					Predicate predicate = criteriaBuilder.or(
							criteriaBuilder.like(productName, "%" + q + "%"),
							criteriaBuilder.like(productNameAlias, "%" + q + "%"),
							criteriaBuilder.like(label, "%" +  q + "%"));
					
					Pattern pattern = Pattern.compile("[0-9]*");
					Matcher isNum = pattern.matcher(q);
					if (isNum.matches()) {
						criteriaBuilder.or(predicate,criteriaBuilder.equal(productId, q));
					}
					list.add(predicate);
				}

				if(attrValueIds !=null){
					list.add(productAttrValueId.in(attrValueIds));
				}

				return criteriaBuilder.and(list.toArray(new Predicate[] {}));
			}
		};
	}
}
