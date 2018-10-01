package com.datas.easyorder.controller.administrator.rankcustomer.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.datas.easyorder.controller.BaseLogic;
import com.datas.easyorder.db.dao.RankProductPriceRepository;
import com.datas.easyorder.db.entity.RankProductPrice;

@Component
public class RankProductPriceLogic extends BaseLogic<RankProductPrice> {

	@Autowired
	RankProductPriceRepository rankProductPriceRepository;

	@Override
	public CrudRepository<RankProductPrice, Long> getRepository() {
		return rankProductPriceRepository;
	}

}
