package com.datas.easyorder.controller.web.product.view;

import com.datas.easyorder.db.entity.Product;

@Deprecated
public class ProductView {

	private Product product;
	
	private Long rankProductPriceId;
	private Double rankProductPricePrice;
	
	private Long rankCustomerId;
	private String rankCustomerRankLevel;
	private String rankCustomerRankDesc;
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Long getRankProductPriceId() {
		return rankProductPriceId;
	}
	public void setRankProductPriceId(Long rankProductPriceId) {
		this.rankProductPriceId = rankProductPriceId;
	}
	public Double getRankProductPricePrice() {
		return rankProductPricePrice;
	}
	public void setRankProductPricePrice(Double rankProductPricePrice) {
		this.rankProductPricePrice = rankProductPricePrice;
	}
	public Long getRankCustomerId() {
		return rankCustomerId;
	}
	public void setRankCustomerId(Long rankCustomerId) {
		this.rankCustomerId = rankCustomerId;
	}
	public String getRankCustomerRankLevel() {
		return rankCustomerRankLevel;
	}
	public void setRankCustomerRankLevel(String rankCustomerRankLevel) {
		this.rankCustomerRankLevel = rankCustomerRankLevel;
	}
	public String getRankCustomerRankDesc() {
		return rankCustomerRankDesc;
	}
	public void setRankCustomerRankDesc(String rankCustomerRankDesc) {
		this.rankCustomerRankDesc = rankCustomerRankDesc;
	}
	
	
	
	
}
