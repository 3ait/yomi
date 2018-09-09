package com.datas.easyorder.controller.administrator.branch.logic;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.datas.easyorder.controller.BaseLogic;
import com.datas.easyorder.controller.administrator.branch.BranchProductView;
import com.datas.easyorder.db.dao.BranchProductRepository;
import com.datas.easyorder.db.dao.BranchRepository;
import com.datas.easyorder.db.dao.ProductRepository;
import com.datas.easyorder.db.entity.Branch;
import com.datas.easyorder.db.entity.BranchProduct;
import com.datas.easyorder.db.entity.Product;

@Component
public class BranchLogic extends BaseLogic<Branch> {

	@Autowired
	BranchRepository branchRepository;
	@Autowired
	BranchProductRepository branchProductRepository;
	@Autowired
	ProductRepository productRepository;
	
	
	@Override
	public CrudRepository<Branch, Long> getRepository() {
		return branchRepository;
	}

	public Page<Branch> getAllBranch(Pageable pageable){
		return branchRepository.findAll(pageable);
	}

	@Transactional(rollbackOn=Exception.class)
	public void del(Long branchId) {
		branchProductRepository.delete(branchProductRepository.findAllByBranchId(branchId));
		branchRepository.delete(branchId);
		
	}
	
	/**
	 * 新增branch
	 * @param branch
	 */
	@Transactional(rollbackOn=Exception.class)
	public void save(Branch branch) {
		//保存branch
		branchRepository.save(branch);
		//保存产品
		Iterable<Product> itr = productRepository.findAll();
		List<BranchProduct> list = new ArrayList<>();
		itr.forEach(p -> {
			BranchProduct bp = new BranchProduct();
			bp.setProduct(p);
			bp.setBranch(branch);
			bp.setStock(0); 	
			list.add(bp);
		});
		branchProductRepository.save(list);
		
	}

	/**
	 * 新增stock
	 * @param productId
	 * @param branchId
	 */
	@Transactional(rollbackOn=Exception.class)
	public void newStock(Long productId, String branchId) {
		
		BranchProduct branchProduct = branchProductRepository.findOneByProductIdAndBranchId(productId,Long.valueOf(branchId));
		if(branchProduct==null){
			Product product = new Product();
			product.setId(productId);
			Branch branch = new Branch();
			branch.setId(Long.valueOf(branchId));
			BranchProduct bp = new BranchProduct();
			bp.setProduct(product);
			bp.setBranch(branch);
			bp.setStock(0);
			branchProductRepository.save(bp);
		}
	}

	/**
	 * 获取一个产品的所有库存
	 * @param productId
	 * @return
	 */
	public List<BranchProductView> getAllStocks(Long productId) {
		List<BranchProduct> bpList = branchProductRepository.findAllByProductId(productId);
		List<BranchProductView> list = new ArrayList<>();
		bpList.forEach(bp -> {
			BranchProductView bpv = new BranchProductView();
			
			bpv.setId(bp.getId());
			bpv.setBranchId(bp.getBranch().getId());
			bpv.setProductId(bp.getProduct().getId());
			bpv.setBranchName(bp.getBranch().getBranchName());
			bpv.setStock(bp.getStock());
			bpv.setPrice1(bp.getPrice1());
			bpv.setPrice2(bp.getPrice2());
			
			list.add(bpv);
		});
		return list;
	}

}
