package com.datas.easyorder.controller.web.index;

import java.util.List;

import com.datas.easyorder.db.entity.Menu;

public class MenuView {

	private Menu menu;
	
	private List<MenuView> subMenuViewList;

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public List<MenuView> getSubMenuViewList() {
		return subMenuViewList;
	}

	public void setSubMenuViewList(List<MenuView> subMenuViewList) {
		this.subMenuViewList = subMenuViewList;
	}
	
	
	
	
}
