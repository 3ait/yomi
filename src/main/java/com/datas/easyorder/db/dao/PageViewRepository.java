package com.datas.easyorder.db.dao;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.datas.easyorder.db.entity.PageView;

public interface PageViewRepository extends CrudRepository<PageView, Long> {

	@Query("SELECT SUM(viewNumber)  FROM PageView WHERE date >= ?1 and date <= ?2")
	Long findTotalByDateBetween(Date from, Date to);
	
	@Query(value="INSERT INTO page_view (`date`, view_number) VALUES  (NOW(), 1) ON DUPLICATE KEY UPDATE view_number = view_number + 1",nativeQuery=true)
	public int insertPageView();

	PageView findOneByDate(Date time);
	
	Page<PageView> findByDateBetween(Date from, Date to,Pageable pageable);
}
