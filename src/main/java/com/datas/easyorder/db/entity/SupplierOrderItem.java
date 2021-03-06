package com.datas.easyorder.db.entity;
// Generated 2018-9-28 16:02:05 by Hibernate Tools 4.3.1.Final

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
 * SupplierOrderItem generated by hbm2java
 */
@Entity
@Table(name = "supplier_order_item", catalog = "yomi")
public class SupplierOrderItem implements java.io.Serializable {

	private Long id;
	private Product product;
	private SupplierOrder supplierOrder;
	private int num;
	private Double productCost;
	private double productPrice;
	private String productNameCn;
	private String productNameEn;
	private String productDefaultSrc;

	public SupplierOrderItem() {
	}

	public SupplierOrderItem(SupplierOrder supplierOrder, int num, double productPrice) {
		this.supplierOrder = supplierOrder;
		this.num = num;
		this.productPrice = productPrice;
	}

	public SupplierOrderItem(Product product, SupplierOrder supplierOrder, int num, Double productCost,
			double productPrice, String productNameCn, String productNameEn, String productDefaultSrc) {
		this.product = product;
		this.supplierOrder = supplierOrder;
		this.num = num;
		this.productCost = productCost;
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
	@JoinColumn(name = "product_id")
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier_order_id", nullable = false)
	public SupplierOrder getSupplierOrder() {
		return this.supplierOrder;
	}

	public void setSupplierOrder(SupplierOrder supplierOrder) {
		this.supplierOrder = supplierOrder;
	}

	@Column(name = "num", nullable = false)
	public int getNum() {
		return this.num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Column(name = "product_cost", precision = 10)
	public Double getProductCost() {
		return this.productCost;
	}

	public void setProductCost(Double productCost) {
		this.productCost = productCost;
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
