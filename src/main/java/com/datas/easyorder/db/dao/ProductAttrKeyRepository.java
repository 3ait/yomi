package com.datas.easyorder.db.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.datas.easyorder.db.entity.ProductAttrKey;

public interface ProductAttrKeyRepository extends CrudRepository<ProductAttrKey, Long> {

	Page<ProductAttrKey>  findAll(Pageable Pageable);
	
}
