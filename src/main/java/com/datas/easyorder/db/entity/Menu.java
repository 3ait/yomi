package com.datas.easyorder.db.entity;
// Generated 2018-3-7 11:20:55 by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Menu generated by hbm2java
 */
@Entity
@Table(name = "menu", catalog = "yomi")
public class Menu implements java.io.Serializable {

	private Long id;
	@JsonIgnore
	private Menu menu;
	private String name;
	private String nameAlias;
	private int level;
	private Integer position;
	private Double unitePrice;
	private Double boxWeight;
	private Double otherCharge;
	private String defaultSrc;
	private String menuType;
	private String status;
	@JsonIgnore
	private Set<Attachment> attachments = new HashSet<Attachment>(0);
	@JsonIgnore
	private Set<Article> articles = new HashSet<Article>(0);
	@JsonIgnore
	private Set<Menu> menus = new HashSet<Menu>(0);
	@JsonIgnore
	private Set<MenuAttr> menuAttrs = new HashSet<MenuAttr>(0);
	@JsonIgnore
	private Set<Product> products = new HashSet<Product>(0);

	public Menu() {
	}

	public Menu(int level) {
		this.level = level;
	}

	public Menu(Menu menu, String name, String nameAlias, int level, Integer position, Double unitePrice,
			Double boxWeight, Double otherCharge, String defaultSrc, String menuType, String status,
			Set<Attachment> attachments, Set<Article> articles, Set<Menu> menus, Set<MenuAttr> menuAttrs,
			Set<Product> products) {
		this.menu = menu;
		this.name = name;
		this.nameAlias = nameAlias;
		this.level = level;
		this.position = position;
		this.unitePrice = unitePrice;
		this.boxWeight = boxWeight;
		this.otherCharge = otherCharge;
		this.defaultSrc = defaultSrc;
		this.menuType = menuType;
		this.status = status;
		this.attachments = attachments;
		this.articles = articles;
		this.menus = menus;
		this.menuAttrs = menuAttrs;
		this.products = products;
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
	@JoinColumn(name = "father_id")
	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@Column(name = "name", length = 128)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "name_alias", length = 128)
	public String getNameAlias() {
		return this.nameAlias;
	}

	public void setNameAlias(String nameAlias) {
		this.nameAlias = nameAlias;
	}

	@Column(name = "level", nullable = false)
	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Column(name = "position")
	public Integer getPosition() {
		return this.position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	@Column(name = "unite_price", precision = 10)
	public Double getUnitePrice() {
		return this.unitePrice;
	}

	public void setUnitePrice(Double unitePrice) {
		this.unitePrice = unitePrice;
	}

	@Column(name = "box_weight", precision = 22, scale = 0)
	public Double getBoxWeight() {
		return this.boxWeight;
	}

	public void setBoxWeight(Double boxWeight) {
		this.boxWeight = boxWeight;
	}

	@Column(name = "other_charge", precision = 22, scale = 0)
	public Double getOtherCharge() {
		return this.otherCharge;
	}

	public void setOtherCharge(Double otherCharge) {
		this.otherCharge = otherCharge;
	}

	@Column(name = "default_src", length = 1024)
	public String getDefaultSrc() {
		return this.defaultSrc;
	}

	public void setDefaultSrc(String defaultSrc) {
		this.defaultSrc = defaultSrc;
	}

	@Column(name = "menu_type", length = 8)
	public String getMenuType() {
		return this.menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	@Column(name = "status", length = 7)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
	public Set<Attachment> getAttachments() {
		return this.attachments;
	}

	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
	public Set<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
	public Set<Menu> getMenus() {
		return this.menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
	public Set<MenuAttr> getMenuAttrs() {
		return this.menuAttrs;
	}

	public void setMenuAttrs(Set<MenuAttr> menuAttrs) {
		this.menuAttrs = menuAttrs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
	public Set<Product> getProducts() {
		return this.products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
