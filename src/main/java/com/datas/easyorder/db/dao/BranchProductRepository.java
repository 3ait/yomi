package com.datas.easyorder.db.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.datas.easyorder.db.entity.BranchProduct;

public interface BranchProductRepository extends CrudRepository<BranchProduct, Long> {

	List<BranchProduct> findAllByProductId(Long productId);

	BranchProduct findOneByProductIdAndBranchId(Long productId, Long branchId);

	List<BranchProduct> findAllByBranchId(Long branchId);
    
}
