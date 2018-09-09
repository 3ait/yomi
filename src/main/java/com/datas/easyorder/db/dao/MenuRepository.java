package com.datas.easyorder.db.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.datas.easyorder.db.entity.Menu;

public interface MenuRepository extends CrudRepository<Menu, Long> {

	public  final String MENU_TYPE_ARTICLE = "article"; 
	public  final String MENU_TYPE_PRODUCT = "product"; 
	public  final String MENU_TYPE_BRAND = "brand"; 
	public  final String POSITION = "position"; 
	//'bottom','hidden','top','other','default','disable'
	public final String MENU_STATUS_TOP = "top";
	public final String MENU_STATUS_BOTTOM = "bottom";
	public final String MENU_STATUS_HIDDEN = "hidden";
	
	List<Menu> findByLevel(Integer level,Pageable pageable);

	List<Menu> findByMenuId(Long fatherId,Pageable pageable);

	List<Menu> findByLevelAndMenuType(Integer level, String menuType, Pageable pageable);

	List<Menu> findByLevelAndStatus(int i, String mENU_STATUS_TOP, Pageable pageable);

	List<Menu> findByLevelAndStatusAndMenuType(Integer level, String menuStatusTop, String menuType, Pageable pageable);

	List<Menu> findByLevelAndMenuTypeIn(Integer level, String[] menuTypes, Pageable pageable);


}
