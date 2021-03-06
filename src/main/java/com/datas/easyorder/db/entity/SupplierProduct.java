package com.datas.easyorder.db.entity;
// Generated 2018-9-28 16:02:05 by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SupplierProduct generated by hbm2java
 */
@Entity
@Table(name = "supplier_product", catalog = "yomi")
public class SupplierProduct implements java.io.Serializable {

	private Long id;
	private Product product;
	private Supplier supplier;
	private BigDecimal cost;
	private BigDecimal cost1;
	private BigDecimal price;
	private BigDecimal price1;
	private String memo;

	public SupplierProduct() {
	}

	public SupplierProduct(Product product, Supplier supplier) {
		this.product = product;
		this.supplier = supplier;
	}

	public SupplierProduct(Product product, Supplier supplier, BigDecimal cost, BigDecimal cost1, BigDecimal price,
			BigDecimal price1, String memo) {
		this.product = product;
		this.supplier = supplier;
		this.cost = cost;
		this.cost1 = cost1;
		this.price = price;
		this.price1 = price1;
		this.memo = memo;
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
	@JoinColumn(name = "supplier_id", nullable = false)
	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	@Column(name = "cost", precision = 10)
	public BigDecimal getCost() {
		return this.cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	@Column(name = "cost1", precision = 10)
	public BigDecimal getCost1() {
		return this.cost1;
	}

	public void setCost1(BigDecimal cost1) {
		this.cost1 = cost1;
	}

	@Column(name = "price", precision = 10)
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "price1", precision = 10)
	public BigDecimal getPrice1() {
		return this.price1;
	}

	public void setPrice1(BigDecimal price1) {
		this.price1 = price1;
	}

	@Column(name = "memo", length = 1024)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
