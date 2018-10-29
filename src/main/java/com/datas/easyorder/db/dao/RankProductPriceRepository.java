package com.datas.easyorder.db.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.datas.easyorder.db.entity.RankProductPrice;

@Component("rankProductPriceRepository")
public interface RankProductPriceRepository extends CrudRepository<RankProductPrice, Long> {

	List<RankProductPrice> findAllByRankCustomerId(Long rankCustomerId);
	 
	List<RankProductPrice> findAllByProductId(Long productId);
	
	RankProductPrice findOneByProductIdAndRankCustomerId(Long productId,Long rankCustomerId);

}
