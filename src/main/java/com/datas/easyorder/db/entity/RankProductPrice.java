package com.datas.easyorder.db.entity;
// Generated 2018-9-27 12:33:29 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * RankProductPrice generated by hbm2java
 */
@Entity
@Table(name = "rank_product_price", catalog = "yomi", uniqueConstraints = @UniqueConstraint(columnNames = {
		"product_id", "rank_customer_id" }))
public class RankProductPrice implements java.io.Serializable {

	private Long id;
	@JsonIgnore
	private Product product;
	@JsonIgnore
	private RankCustomer rankCustomer;
	private double price;
	private Double price1;
	private String description;

	public RankProductPrice() {
	}

	public RankProductPrice(Product product, RankCustomer rankCustomer, double price) {
		this.product = product;
		this.rankCustomer = rankCustomer;
		this.price = price;
	}

	public RankProductPrice(Product product, RankCustomer rankCustomer, double price, Double price1, String description) {
		this.product = product;
		this.rankCustomer = rankCustomer;
		this.price = price;
		this.price1 = price1;
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rank_customer_id", nullable = false)
	public RankCustomer getRankCustomer() {
		return this.rankCustomer;
	}

	public void setRankCustomer(RankCustomer rankCustomer) {
		this.rankCustomer = rankCustomer;
	}

	@Column(name = "price", nullable = false, precision = 10)
	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Column(name = "price1", precision = 10)
	public Double getPrice1() {
		return this.price1;
	}

	public void setPrice1(Double price1) {
		this.price1 = price1;
	}

	@Column(name = "description", length = 1024)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
