package com.datas.easyorder.db.entity;
// Generated 2018-9-4 16:40:06 by Hibernate Tools 4.3.1.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Product generated by hbm2java
 */
@Entity
@Table(name = "product", catalog = "ultimatelife")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Product implements java.io.Serializable {

	private Long id;
	@JsonIgnore
	private Menu menu;
	private String mpn;
	private String productName;
	private String productNameAlias;
	private String defaultSrc;
	private Double cost;
	private double price1;
	private Double price2;
	private Double price3;
	private Double price4;
	private Byte status;
	private Byte hot;
	private Byte promote;
	private Byte frontPage;
	private Byte recommend;
	private Integer soldNum;
	private Integer stock;
	private Double weight;
	private Integer clickNum;
	private String summary;
	private String description;
	private String seoKeywords;
	private String seoDesc;
	private Date createTime;
	private Date modifyTime;
	private String location;
	private String label;
	private Integer position;
	private String mobileDefaultSrc;
	private String mobileDefaultDesc;
	private String norms;
	@JsonIgnore
	private Set<Reply> replies = new HashSet<Reply>(0);
	@JsonIgnore
	private Set<OrderItem> orderItems = new HashSet<OrderItem>(0);
	@JsonIgnore
	private Set<BranchProduct> branchProducts = new HashSet<BranchProduct>(0);
	@JsonIgnore
	private Set<Attachment> attachments = new HashSet<Attachment>(0);
	@JsonIgnore
	private Set<Comment> comments = new HashSet<Comment>(0);
	@JsonIgnore
	private Set<ProductAttr> productAttrs = new HashSet<ProductAttr>(0);

	public Product() {
	}

	public Product(double price1) {
		this.price1 = price1;
	}

	public Product(Menu menu, String mpn, String productName, String productNameAlias, String defaultSrc, Double cost,
			double price1, Double price2, Double price3, Double price4, Byte status, Byte hot, Byte promote,
			Byte frontPage, Byte recommend, Integer soldNum, Integer stock, Double weight, Integer clickNum,
			String summary, String description, String seoKeywords, String seoDesc, Date createTime, Date modifyTime,
			String location, String label, Integer position, String mobileDefaultSrc, String mobileDefaultDesc,
			String norms, Set<Reply> replies, Set<OrderItem> orderItems, Set<BranchProduct> branchProducts,
			Set<Attachment> attachments, Set<Comment> comments, Set<ProductAttr> productAttrs) {
		this.menu = menu;
		this.mpn = mpn;
		this.productName = productName;
		this.productNameAlias = productNameAlias;
		this.defaultSrc = defaultSrc;
		this.cost = cost;
		this.price1 = price1;
		this.price2 = price2;
		this.price3 = price3;
		this.price4 = price4;
		this.status = status;
		this.hot = hot;
		this.promote = promote;
		this.frontPage = frontPage;
		this.recommend = recommend;
		this.soldNum = soldNum;
		this.stock = stock;
		this.weight = weight;
		this.clickNum = clickNum;
		this.summary = summary;
		this.description = description;
		this.seoKeywords = seoKeywords;
		this.seoDesc = seoDesc;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.location = location;
		this.label = label;
		this.position = position;
		this.mobileDefaultSrc = mobileDefaultSrc;
		this.mobileDefaultDesc = mobileDefaultDesc;
		this.norms = norms;
		this.replies = replies;
		this.orderItems = orderItems;
		this.branchProducts = branchProducts;
		this.attachments = attachments;
		this.comments = comments;
		this.productAttrs = productAttrs;
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
	@JoinColumn(name = "menu_id")
	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@Column(name = "mpn", length = 128)
	public String getMpn() {
		return this.mpn;
	}

	public void setMpn(String mpn) {
		this.mpn = mpn;
	}

	@Column(name = "product_name", length = 256)
	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "product_name_alias", length = 256)
	public String getProductNameAlias() {
		return this.productNameAlias;
	}

	public void setProductNameAlias(String productNameAlias) {
		this.productNameAlias = productNameAlias;
	}

	@Column(name = "default_src", length = 1024)
	public String getDefaultSrc() {
		return this.defaultSrc;
	}

	public void setDefaultSrc(String defaultSrc) {
		this.defaultSrc = defaultSrc;
	}

	@Column(name = "cost", precision = 22, scale = 0)
	public Double getCost() {
		return this.cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	@Column(name = "price1", nullable = false, precision = 22, scale = 0)
	public double getPrice1() {
		return this.price1;
	}

	public void setPrice1(double price1) {
		this.price1 = price1;
	}

	@Column(name = "price2", precision = 22, scale = 0)
	public Double getPrice2() {
		return this.price2;
	}

	public void setPrice2(Double price2) {
		this.price2 = price2;
	}

	@Column(name = "price3", precision = 22, scale = 0)
	public Double getPrice3() {
		return this.price3;
	}

	public void setPrice3(Double price3) {
		this.price3 = price3;
	}

	@Column(name = "price4", precision = 22, scale = 0)
	public Double getPrice4() {
		return this.price4;
	}

	public void setPrice4(Double price4) {
		this.price4 = price4;
	}

	@Column(name = "status")
	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	@Column(name = "hot")
	public Byte getHot() {
		return this.hot;
	}

	public void setHot(Byte hot) {
		this.hot = hot;
	}

	@Column(name = "promote")
	public Byte getPromote() {
		return this.promote;
	}

	public void setPromote(Byte promote) {
		this.promote = promote;
	}

	@Column(name = "front_page")
	public Byte getFrontPage() {
		return this.frontPage;
	}

	public void setFrontPage(Byte frontPage) {
		this.frontPage = frontPage;
	}

	@Column(name = "recommend")
	public Byte getRecommend() {
		return this.recommend;
	}

	public void setRecommend(Byte recommend) {
		this.recommend = recommend;
	}

	@Column(name = "sold_num")
	public Integer getSoldNum() {
		return this.soldNum;
	}

	public void setSoldNum(Integer soldNum) {
		this.soldNum = soldNum;
	}

	@Column(name = "stock")
	public Integer getStock() {
		return this.stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	@Column(name = "weight", precision = 22, scale = 0)
	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@Column(name = "click_num")
	public Integer getClickNum() {
		return this.clickNum;
	}

	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}

	@Column(name = "summary", length = 65535)
	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "seo_keywords", length = 1024)
	public String getSeoKeywords() {
		return this.seoKeywords;
	}

	public void setSeoKeywords(String seoKeywords) {
		this.seoKeywords = seoKeywords;
	}

	@Column(name = "seo_desc", length = 1024)
	public String getSeoDesc() {
		return this.seoDesc;
	}

	public void setSeoDesc(String seoDesc) {
		this.seoDesc = seoDesc;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_time", length = 19)
	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "location", length = 1024)
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "label", length = 1024)
	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Column(name = "position")
	public Integer getPosition() {
		return this.position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	@Column(name = "mobile_default_src", length = 1024)
	public String getMobileDefaultSrc() {
		return this.mobileDefaultSrc;
	}

	public void setMobileDefaultSrc(String mobileDefaultSrc) {
		this.mobileDefaultSrc = mobileDefaultSrc;
	}

	@Column(name = "mobile_default_desc", length = 65535)
	public String getMobileDefaultDesc() {
		return this.mobileDefaultDesc;
	}

	public void setMobileDefaultDesc(String mobileDefaultDesc) {
		this.mobileDefaultDesc = mobileDefaultDesc;
	}

	@Column(name = "norms", length = 65535)
	public String getNorms() {
		return this.norms;
	}

	public void setNorms(String norms) {
		this.norms = norms;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public Set<Reply> getReplies() {
		return this.replies;
	}

	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public Set<OrderItem> getOrderItems() {
		return this.orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public Set<BranchProduct> getBranchProducts() {
		return this.branchProducts;
	}

	public void setBranchProducts(Set<BranchProduct> branchProducts) {
		this.branchProducts = branchProducts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public Set<Attachment> getAttachments() {
		return this.attachments;
	}

	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public Set<ProductAttr> getProductAttrs() {
		return this.productAttrs;
	}

	public void setProductAttrs(Set<ProductAttr> productAttrs) {
		this.productAttrs = productAttrs;
	}

}
