package com.datas.easyorder.db.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import com.datas.easyorder.db.entity.ProductAttr;

public interface ProductAttrRepository extends CrudRepository<ProductAttr, Long> {

	public List<ProductAttr> findByProductId(Long productId);

	public Page<ProductAttr> findAll(Specification<ProductAttr> searchSpecification, Pageable pageable);

	public Page<ProductAttr> findAllByProductAttrValueIdIn(Long[] attrValueIds, Pageable pageable);

	public List<ProductAttr> findAllByProductAttrValueId(Long id);

	public List<ProductAttr> findAllByProductAttrKeyId(Long id);
	

	public Page<ProductAttr> findByProductAttrValueIdInAndProductFrontPage(Long[] productAttrValueIds,byte hot,Pageable pageable);
	
	public Page<ProductAttr> findByProductAttrValueIdInAndProductPromote(Long[] productAttrValueIds,byte hot,Pageable pageable);

	public Page<ProductAttr> findByProductAttrValueIdInAndProductRecommend(Long[] attrValueIds, byte b,
			Pageable pageable);
}
