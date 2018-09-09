package com.datas.easyorder.controller.administrator.branch;

public class BranchProductView {

	private Long id;
	private Long branchId;
	private Long productId;
	
	private String branchName;
	private Integer stock;
	private Double price1;
	private Double price2;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Double getPrice1() {
		return price1;
	}
	public void setPrice1(Double price1) {
		this.price1 = price1;
	}
	public Double getPrice2() {
		return price2;
	}
	public void setPrice2(Double price2) {
		this.price2 = price2;
	}

}
