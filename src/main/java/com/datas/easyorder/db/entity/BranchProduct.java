package com.datas.easyorder.db.entity;
// Generated 2018-9-4 16:40:06 by Hibernate Tools 4.3.1.Final

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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * BranchProduct generated by hbm2java
 */
@Entity
@Table(name = "branch_product", catalog = "yomi", uniqueConstraints = @UniqueConstraint(columnNames = {
		"branch_id", "product_id" }))
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class BranchProduct implements java.io.Serializable {

	private Long id;
	@JsonIgnore
	private Branch branch;
	@JsonIgnore
	private Product product;
	private Integer stock;
	private Double price1;
	private Double price2;

	public BranchProduct() {
	}

	public BranchProduct(Branch branch, Product product) {
		this.branch = branch;
		this.product = product;
	}

	public BranchProduct(Branch branch, Product product, Integer stock, Double price1, Double price2) {
		this.branch = branch;
		this.product = product;
		this.stock = stock;
		this.price1 = price1;
		this.price2 = price2;
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
	@JoinColumn(name = "branch_id", nullable = false)
	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "stock")
	public Integer getStock() {
		return this.stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	@Column(name = "price1", precision = 10)
	public Double getPrice1() {
		return this.price1;
	}

	public void setPrice1(Double price1) {
		this.price1 = price1;
	}

	@Column(name = "price2", precision = 10)
	public Double getPrice2() {
		return this.price2;
	}

	public void setPrice2(Double price2) {
		this.price2 = price2;
	}

}
