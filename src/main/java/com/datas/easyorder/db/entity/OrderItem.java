package com.datas.easyorder.db.entity;
// Generated 2018-10-4 15:36:49 by Hibernate Tools 4.3.1.Final

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * OrderItem generated by hbm2java
 */
@Entity
@Table(name = "order_item", catalog = "yomi")
public class OrderItem implements java.io.Serializable {

	private Long id;
	@JsonIgnore
	private Order order;
	@JsonIgnore
	private Product product;
	private double num;
	private Double cost;
	private double productPrice;
	private String productNameCn;
	private String productNameEn;
	private String productDefaultSrc;

	public OrderItem() {
	}

	public OrderItem(Order order, double num, double productPrice) {
		this.order = order;
		this.num = num;
		this.productPrice = productPrice;
	}

	public OrderItem(Order order, Product product, double num, Double cost, double productPrice, String productNameCn,
			String productNameEn, String productDefaultSrc) {
		this.order = order;
		this.product = product;
		this.num = num;
		this.cost = cost;
		this.productPrice = productPrice;
		this.productNameCn = productNameCn;
		this.productNameEn = productNameEn;
		this.productDefaultSrc = productDefaultSrc;
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
	@JoinColumn(name = "order_id", nullable = false)
	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "num", nullable = false, precision = 10)
	public double getNum() {
		return this.num;
	}

	public void setNum(double num) {
		this.num = num;
	}

	@Column(name = "cost", precision = 10)
	public Double getCost() {
		return this.cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	@Column(name = "product_price", nullable = false, precision = 10)
	public double getProductPrice() {
		return this.productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	@Column(name = "product_name_cn", length = 1024)
	public String getProductNameCn() {
		return this.productNameCn;
	}

	public void setProductNameCn(String productNameCn) {
		this.productNameCn = productNameCn;
	}

	@Column(name = "product_name_en", length = 1024)
	public String getProductNameEn() {
		return this.productNameEn;
	}

	public void setProductNameEn(String productNameEn) {
		this.productNameEn = productNameEn;
	}

	@Column(name = "product_default_src", length = 1024)
	public String getProductDefaultSrc() {
		return this.productDefaultSrc;
	}

	public void setProductDefaultSrc(String productDefaultSrc) {
		this.productDefaultSrc = productDefaultSrc;
	}

}
