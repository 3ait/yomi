package com.datas.easyorder.db.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.datas.easyorder.db.entity.ProductAttrValue;

public interface ProductAttrValueRepository extends CrudRepository<ProductAttrValue, Long> {

	List<ProductAttrValue> findByProductAttrKeyId(Long id);

	Page<ProductAttrValue> findByProductAttrKeyAttrKey(String key, Pageable pageable);

	
}
