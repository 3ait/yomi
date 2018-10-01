package com.datas.easyorder.controller.administrator.product.view;

import java.util.List;

import com.datas.easyorder.db.entity.Product;

public class ProductMulitPrice {

	private Product product;
	private List<RankPrice> rankPriceList;
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public List<RankPrice> getRankPriceList() {
		return rankPriceList;
	}
	public void setRankPriceList(List<RankPrice> rankPriceList) {
		this.rankPriceList = rankPriceList;
	}
	
	
	
	
}
