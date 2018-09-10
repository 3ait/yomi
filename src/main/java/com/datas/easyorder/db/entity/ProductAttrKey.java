package com.datas.easyorder.db.entity;
// Generated 2018-3-7 11:20:55 by Hibernate Tools 4.3.1.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * ProductAttrKey generated by hbm2java
 */
@Entity
@Table(name = "product_attr_key", catalog = "yomi")
public class ProductAttrKey implements java.io.Serializable {

	private Long id;
	private String attrKey;
	private Integer position;
	@JsonIgnore
	private Set<ProductAttr> productAttrs = new HashSet<ProductAttr>(0);
	@JsonIgnore
	private Set<ProductAttrValue> productAttrValues = new HashSet<ProductAttrValue>(0);

	public ProductAttrKey() {
	}

	public ProductAttrKey(String attrKey, Integer position, Set<ProductAttr> productAttrs,
			Set<ProductAttrValue> productAttrValues) {
		this.attrKey = attrKey;
		this.position = position;
		this.productAttrs = productAttrs;
		this.productAttrValues = productAttrValues;
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

	@Column(name = "attr_key", length = 1024)
	public String getAttrKey() {
		return this.attrKey;
	}

	public void setAttrKey(String attrKey) {
		this.attrKey = attrKey;
	}

	@Column(name = "position")
	public Integer getPosition() {
		return this.position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productAttrKey")
	public Set<ProductAttr> getProductAttrs() {
		return this.productAttrs;
	}

	public void setProductAttrs(Set<ProductAttr> productAttrs) {
		this.productAttrs = productAttrs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productAttrKey")
	public Set<ProductAttrValue> getProductAttrValues() {
		return this.productAttrValues;
	}

	public void setProductAttrValues(Set<ProductAttrValue> productAttrValues) {
		this.productAttrValues = productAttrValues;
	}

}
