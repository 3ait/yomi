package com.datas.easyorder.db.entity;
// Generated 2018-3-7 11:20:55 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ProductBrand generated by hbm2java
 */
@Entity
@Table(name = "product_brand", catalog = "yomi")
public class ProductBrand implements java.io.Serializable {

	private Long id;
	private String brandNameCn;
	private String brandNameEn;
	private String brandImgSrc;

	public ProductBrand() {
	}

	public ProductBrand(String brandNameCn, String brandNameEn, String brandImgSrc) {
		this.brandNameCn = brandNameCn;
		this.brandNameEn = brandNameEn;
		this.brandImgSrc = brandImgSrc;
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

	@Column(name = "brand_name_cn", length = 128)
	public String getBrandNameCn() {
		return this.brandNameCn;
	}

	public void setBrandNameCn(String brandNameCn) {
		this.brandNameCn = brandNameCn;
	}

	@Column(name = "brand_name_en", length = 128)
	public String getBrandNameEn() {
		return this.brandNameEn;
	}

	public void setBrandNameEn(String brandNameEn) {
		this.brandNameEn = brandNameEn;
	}

	@Column(name = "brand_img_src", length = 512)
	public String getBrandImgSrc() {
		return this.brandImgSrc;
	}

	public void setBrandImgSrc(String brandImgSrc) {
		this.brandImgSrc = brandImgSrc;
	}

}
