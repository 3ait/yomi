package com.datas.easyorder.db.entity;
// Generated 2018-3-7 11:20:55 by Hibernate Tools 4.3.1.Final

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
 * MenuAttr generated by hbm2java
 */
@Entity
@Table(name = "menu_attr", catalog = "yomi")
public class MenuAttr implements java.io.Serializable {

	private Long id;
	private Menu menu;
	private String menuAttrName;

	public MenuAttr() {
	}

	public MenuAttr(Menu menu, String menuAttrName) {
		this.menu = menu;
		this.menuAttrName = menuAttrName;
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
	@JoinColumn(name = "menu_id", nullable = false)
	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@Column(name = "menu_attr_name", nullable = false, length = 128)
	public String getMenuAttrName() {
		return this.menuAttrName;
	}

	public void setMenuAttrName(String menuAttrName) {
		this.menuAttrName = menuAttrName;
	}

}
