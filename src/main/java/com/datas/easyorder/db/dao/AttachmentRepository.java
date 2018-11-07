package com.datas.easyorder.db.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import com.datas.easyorder.db.entity.Attachment;

public interface AttachmentRepository extends CrudRepository<Attachment, Long> {

	public List<Attachment> findAllByProductId(Long productId);

	public List<Attachment> findAllByArticleId(Long articleId);

	public Page<Attachment> findAll(Specification<Attachment> searchSpecification, Pageable pageable);

	public Page<Attachment> findAllByTitle(String title, Pageable pageable);

    
}
