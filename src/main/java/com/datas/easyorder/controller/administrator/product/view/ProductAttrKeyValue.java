package com.datas.easyorder.controller.administrator.product.view;

import java.util.List;

import com.datas.easyorder.db.entity.ProductAttrKey;
import com.datas.easyorder.db.entity.ProductAttrValue;

public class ProductAttrKeyValue {

	private ProductAttrKey productAttrKey;
	
	private List<ProductAttrValue> productAttrValueList;

	public ProductAttrKey getProductAttrKey() {
		return productAttrKey;
	}
	public void setProductAttrKey(ProductAttrKey productAttrKey) {
		this.productAttrKey = productAttrKey;
	}
	public List<ProductAttrValue> getProductAttrValueList() {
		return productAttrValueList;
	}
	public void setProductAttrValueList(List<ProductAttrValue> productAttrValueList) {
		this.productAttrValueList = productAttrValueList;
	}
	
	
	
}
