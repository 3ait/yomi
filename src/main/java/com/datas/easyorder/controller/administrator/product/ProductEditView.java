package com.datas.easyorder.controller.administrator.product;

import java.util.List;
import java.util.Set;

import com.datas.easyorder.db.entity.Attachment;
import com.datas.easyorder.db.entity.Product;

public class ProductEditView {

	private Product product;
	private List<Attachment> attachmentList;
	private Set<String> keyvalueSet;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}
	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}
	public Set<String> getKeyvalueSet() {
		return keyvalueSet;
	}
	public void setKeyvalueSet(Set<String> keyvalueSet) {
		this.keyvalueSet = keyvalueSet;
	} 
	
	
	
}
