package com.datas.easyorder.controller.administrator.category.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.datas.easyorder.controller.administrator.product.view.ProductAttrKeyValue;
import com.datas.easyorder.db.dao.ProductAttrKeyRepository;
import com.datas.easyorder.db.dao.ProductAttrRepository;
import com.datas.easyorder.db.dao.ProductAttrValueRepository;
import com.datas.easyorder.db.entity.ProductAttrKey;
import com.datas.easyorder.db.entity.ProductAttrValue;

@Component
@Service
public class AttrLogic {

	@Autowired
	ProductAttrRepository productAttrRepository;
	@Autowired
	ProductAttrKeyRepository productAttrKeyRepository;
	@Autowired
	ProductAttrValueRepository productAttrValueRepository;

	
	public Page<ProductAttrKey>  getProductAttrKeyList() {
		
		String[] sortBy = {"position"};
		Pageable pageable = new PageRequest(0, Integer.MAX_VALUE,Direction.ASC,sortBy);
		return productAttrKeyRepository.findAll(pageable);
	}

	/**
	 * newAttrKey 
	 * @param attrName
	 */
	public void newAttrKey(String attrName) {
		ProductAttrKey productAttrKey = new ProductAttrKey();
		productAttrKey.setAttrKey(attrName);
		productAttrKeyRepository.save(productAttrKey);
	}
	
	
	/**
	 * findValuesByAttrKeyId 
	 * @param attrName
	 */
	public List<ProductAttrValue> getValuesByAttrKeyId(Long attrKeyId) {
		return productAttrValueRepository.findByProductAttrKeyId(attrKeyId);
	}
	
	/**
	 * delAttrKey 
	 * @param attrName
	 */
	@Transactional(rollbackOn=Exception.class)
	public void delAttrKey(Long id) {
		productAttrRepository.delete(productAttrRepository.findAllByProductAttrKeyId(id));
		productAttrValueRepository.delete(productAttrValueRepository.findByProductAttrKeyId(id));
		productAttrKeyRepository.delete(id);
	}
	
	
	/**
	 * newAttrValue
	 * @param attrName
	 */
	@Transactional(rollbackOn=Exception.class)
	public void newAttrValue(Long attrKeyId, String attrValue) {
		ProductAttrValue productAttrValue = new ProductAttrValue();
		ProductAttrKey productAttrKey = new ProductAttrKey();
		productAttrKey.setId(attrKeyId);
		productAttrValue.setProductAttrKey(productAttrKey);
		productAttrValue.setAttrValue(attrValue);
		productAttrValueRepository.save(productAttrValue);
	}

	
	/**
	 * delAttrValue 
	 * @param attrName
	 */
	@Transactional(rollbackOn=Exception.class)
	public void delAttrValue(Long id) {
		productAttrRepository.delete(productAttrRepository.findAllByProductAttrValueId(id));
		productAttrValueRepository.delete(id);
	}

	/**
	 * 更新attr key 
	 * @param pk
	 * @param name
	 * @param value
	 */
	@Transactional(rollbackOn=Exception.class)
	public void updateAttrKey(Long pk, String name, String value) {
		 ProductAttrKey  productAttrKey =  productAttrKeyRepository.findOne(pk);
		 productAttrKey.setAttrKey(value);
		 productAttrKeyRepository.save(productAttrKey);
		
	}
	/**
	 * 更新attr value 
	 * @param pk
	 * @param name
	 * @param value
	 */
	@Transactional(rollbackOn=Exception.class)
	public void updateAttrValue(Long pk, String name, String value) {
		 ProductAttrValue  productAttrValue =  productAttrValueRepository.findOne(pk);
		 productAttrValue.setAttrValue(value);
		 productAttrValueRepository.save(productAttrValue);
		
	}
	
	/**
	 * 排序
	 * 
	 * @param categoryIds
	 */
	@Transactional(rollbackOn = Exception.class)
	public void sort(String categoryIds) {
		String[] ids = categoryIds.split(",");
		Long menuSize = productAttrKeyRepository.count();
		for (int i = 0; i < ids.length; i++) {
			ProductAttrKey productAttrKey = productAttrKeyRepository.findOne(Long.valueOf(ids[i]));
			if (productAttrKey != null) {
				productAttrKey.setPosition((int) (menuSize + i));
				productAttrKeyRepository.save(productAttrKey);
			}

		}

	}

	/**
	 * 获取attr key:value list
	 * @return
	 */
	@Transactional(rollbackOn = Exception.class)
	public List<ProductAttrKeyValue> getProductKeyValueList() {
		
		String[] sortBy = {"position"};
		Pageable pageable = new PageRequest(0, Integer.MAX_VALUE,Direction.ASC,sortBy);
		Page<ProductAttrKey> keyList = productAttrKeyRepository.findAll(pageable);
		
		List<ProductAttrKeyValue> list = new ArrayList<>();
		keyList.forEach(key -> {
			ProductAttrKeyValue pav = new ProductAttrKeyValue();
			pav.setProductAttrKey(key);
			pav.setProductAttrValueList(key.getProductAttrValues().stream().sorted((v1,v2) -> v1.getId().compareTo(v2.getId())).collect(Collectors.toList()));
			list.add(pav);
		});
		return list;
	}

	/**
	 * 根据key获取value的值
	 * @param key
	 * @param pageable
	 * @return
	 */
	public Page<ProductAttrValue> findAllValuesByKey(String key, Pageable pageable) {
		return productAttrValueRepository.findByProductAttrKeyAttrKey(key, pageable);
	}
}
