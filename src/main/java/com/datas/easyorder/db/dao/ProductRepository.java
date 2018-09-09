package com.datas.easyorder.db.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.datas.easyorder.db.entity.Product;

@Component("productRepository")
public interface ProductRepository extends CrudRepository<Product, Long> {
	 

	public static final byte status_cancelled = 0;
	public static final byte status_acitve = 1;
	public static final byte status_out_stock = 2;
	
	public Page<Product> findAll(Specification<Product> specification,Pageable pageable);

	public Page<Product> findByMpn(String string, Pageable pageable);

	public Page<Product>  findByPromoteAndStatusNot(byte promote, byte status, Pageable pageable);

	public Page<Product> findByFrontPageAndStatusNot(byte frontPage, byte status, Pageable pageable);

	public Page<Product> findByHotAndStatusNot(byte hot, byte status, Pageable pageable);

	public Page<Product> findByRecommendAndStatusNot(byte recommendb, byte status, Pageable pageable);

	public Page<Product> findByStatusInAndMenuIdInAndIdIn(Byte[] bytes, List<Long> menuIds, List<Long> productIds,Pageable menuPageable);

	public Product findOneByIdAndStatusNot(Long productId, Byte byte1);


	public Page<Product> findByStatusNotAndMenuIdIn(byte statusCancelled, List<Long> menuIds, Pageable pageable);


	@Query(value = "SELECT p FROM Product p WHERE p.id in (select pa.product.id from ProductAttr pa where pa.productAttrValue.id in ?1) And p.frontPage=?2 And p.status <> 0",
		    countQuery = "SELECT count(id) FROM Product p WHERE p.id in (select pa.product.id from ProductAttr pa where pa.productAttrValue.id in ?1) And p.frontPage=?2 And p.status <> 0")
	public Page<Product> findByProductAttrValueIdInAndProductFrontPage(Long[] productAttrValueIds,byte hot,Pageable pageable);
	
	
	@Query(value = "SELECT p FROM Product p WHERE p.id in (select pa.product.id from ProductAttr pa where pa.productAttrValue.id in ?1) And p.promote=?2 And p.status <> 0",
			countQuery = "SELECT count(id) FROM Product p WHERE p.id in (select pa.product.id from ProductAttr pa where pa.productAttrValue.id in ?1) And p.promote=?2 And p.status <> 0")
	public Page<Product> findByProductAttrValueIdInAndProductPromote(Long[] productAttrValueIds,byte hot,Pageable pageable);

	@Query(value = "SELECT p FROM Product p WHERE p.id in (select pa.product.id from ProductAttr pa where pa.productAttrValue.id in ?1) And p.recommend=?2 And p.status <> 0",
			countQuery = "SELECT count(id) FROM Product p WHERE p.id in (select pa.product.id from ProductAttr pa where pa.productAttrValue.id in ?1) And p.recommend=?2 And p.status <> 0")
	public Page<Product> findByProductAttrValueIdInAndProductRecommend(Long[] attrValueIds, byte recommend,Pageable pageable);

	public Page<Product> findByStatusInAndMenuIdIn(Byte[] bytes, List<Long> menuIds, Pageable pageable);

	
	@Query(value = "SELECT p FROM Product p WHERE p.id in (select pa.product.id from ProductAttr pa where pa.productAttrValue.id in ?1) And p.status = ?2",
			countQuery = "SELECT count(id) FROM Product p WHERE p.id in (select pa.product.id from ProductAttr pa where pa.productAttrValue.id in ?1) And  p.status = ?2")
	public Page<Product> findByProductAttrValueIdInAndStatus(Long[] attrValueIds, byte status,Pageable pageable);

	


}
