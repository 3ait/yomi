package com.datas.easyorder.controller.administrator.rankcustomer.logic;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.datas.easyorder.controller.BaseLogic;
import com.datas.easyorder.db.dao.ProductRepository;
import com.datas.easyorder.db.dao.RankCustomerRepository;
import com.datas.easyorder.db.dao.RankProductPriceRepository;
import com.datas.easyorder.db.entity.Product;
import com.datas.easyorder.db.entity.RankCustomer;
import com.datas.easyorder.db.entity.RankProductPrice;

@Component
public class RankCustomerLogic extends BaseLogic<RankCustomer>{

	@Autowired
	RankCustomerRepository rankCustomerRepository;
	@Autowired
	RankProductPriceRepository rankProductPriceRepository;
	@Autowired
	ProductRepository productRepository;
	
	public Page<RankCustomer> findAll(Pageable pageable) {
		return rankCustomerRepository.findAll(pageable);
	}

	@Override
	public CrudRepository<RankCustomer, Long> getRepository() {
		return rankCustomerRepository;
	}

	@Transactional(rollbackOn = Exception.class)
	public void del(Long rankCustomerId) {
		rankProductPriceRepository.delete(rankProductPriceRepository.findAllByRankCustomerId(rankCustomerId));
		rankCustomerRepository.delete(rankCustomerId);
	}
	
	
	@Transactional(rollbackOn = Exception.class)
	public void save(RankCustomer rankCustomer) {
		
		rankCustomerRepository.save(rankCustomer);
		
		
		//保存产品
		Iterable<Product> itr = productRepository.findAll();
		List<RankProductPrice> list = new ArrayList<>();
		itr.forEach(p -> {
			RankProductPrice rankProductPrice = new RankProductPrice();
			rankProductPrice.setPrice(1D);
			rankProductPrice.setPrice1(1D);
			rankProductPrice.setProduct(p);
			rankProductPrice.setRankCustomer(rankCustomer);
			list.add(rankProductPrice);
		});
		
		
		rankProductPriceRepository.save(list);
		
	}

	public List<RankCustomer> getAllRankCustomer(Pageable pageable) {
		return rankCustomerRepository.findAll(pageable).getContent();
	}

}
