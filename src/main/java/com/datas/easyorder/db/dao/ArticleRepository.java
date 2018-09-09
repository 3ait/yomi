package com.datas.easyorder.db.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.datas.easyorder.db.entity.Article;

@Component("articleRepository")
public interface ArticleRepository extends CrudRepository<Article, Long> {
	
	public static final byte status_active = 1;
	public static final byte status_in_active = 0;
	
	public static final byte recommend = 1;

	Page<Article> findAll(Specification<Article> searchSpecification, Pageable pageable);

	Page<Article> findByMenuIdAndStatus(Long categoryId, byte status, Pageable pageable);

	Page<Article> findByUrlTitleAndStatus(String urlTitle, byte status, Pageable pageable);

	Page<Article> findByProductAttrValueIdAndStatus(Long productAttrValueId, byte status, Pageable pageable);

	Page<Article> findByProductAttrValueIdNotNullAndStatus(byte status, Pageable pageable);

	Page<Article> findByMenuIdAndStatusAndRecommend(Long categoryId, byte recommend, byte status, Pageable pageable);

	Page<Article> findByHotAndStatus(byte b, byte c, Pageable pageable);

	Page<Article> findByRecommendAndStatus(byte b, byte c, Pageable pageable);

}
