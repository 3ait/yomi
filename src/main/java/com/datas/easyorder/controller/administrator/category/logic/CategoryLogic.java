package com.datas.easyorder.controller.administrator.category.logic;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.datas.easyorder.controller.BaseLogic;
import com.datas.easyorder.controller.administrator.product.logic.ProductLogic;
import com.datas.easyorder.controller.web.index.MenuView;
import com.datas.easyorder.db.dao.MenuRepository;
import com.datas.easyorder.db.entity.Menu;
import com.datas.easyorder.db.entity.Product;

@Component
@Service
public class CategoryLogic extends BaseLogic<Menu>{

	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	ProductLogic productLogic;
	
	public  final String POSITION = "position"; 
	
	
	
	/**
	 * new menu
	 * 
	 * @param nameCn
	 * @param level
	 * @param fatherId
	 */
	@Transactional(rollbackOn = Exception.class)
	public void newMenu(String nameEn, Integer level, Long fatherId,String menuType) {

		Menu menu = new Menu();
		menu.setName(nameEn);
		menu.setNameAlias(nameEn);
		menu.setLevel(level);
		menu.setPosition(0);
		menu.setMenuType(menuType);
		
		Menu fatherMenu = menuRepository.findOne(fatherId);
		if(fatherMenu!=null){
			menu.setMenuType(fatherMenu.getMenuType());
			menu.setMenu(fatherMenu);
		}

		menuRepository.save(menu);
	}

	/**
	 * 根据级别获取菜单
	 * @param level
	 * @param menuType ==null ? all menu
	 * @return
	 */
	@Transactional(rollbackOn = Exception.class)
	public List<Menu> getMenuByLevel(Integer level,String... menuTypes) {
		Pageable pageable = new PageRequest(0, Integer.MAX_VALUE,Direction.ASC,POSITION);
		
		List<Menu> menuList = new ArrayList<>();
		if(menuTypes.length>0){
			menuList = menuRepository.findByLevelAndMenuTypeIn(level,menuTypes,pageable);
		}else{
			menuList = menuRepository.findByLevel(level,pageable);
		}
		return menuList;
	}

	/**
	 * father Id
	 * 
	 * @param fatherId
	 * @return
	 */
	@Transactional(rollbackOn = Exception.class)
	public List<Menu> getMenuByFatherId(Long fatherId) {
		Pageable pageable = new PageRequest(0, Integer.MAX_VALUE,Direction.ASC,POSITION);
		
		List<Menu>  menus = menuRepository.findByMenuId(fatherId,pageable);
		
		
		
		return menus;
	}
	
	/**
	 * father Id
	 * 
	 * @param fatherId
	 * @return
	 */
	@Transactional(rollbackOn = Exception.class)
	public Menu getMenuById(Long id) {
		return menuRepository.findOne(id);
	}

	@Transactional(rollbackOn = Exception.class)
	public void delMenuById(Long menuId) {
		Pageable pageable = new PageRequest(0, Integer.MAX_VALUE,Direction.ASC,POSITION);
		List<Menu> list = menuRepository.findByMenuId(menuId,pageable);
		for (Menu m : list) {
			delMenuById(m.getId());
		}
		menuRepository.delete(menuId);

	}

	/**
	 * 
	 * 排序
	 * @param categoryIds
	 */
	@Transactional(rollbackOn = Exception.class)
	public void sort(String categoryIds) {
		String[] ids = categoryIds.split(",");
		Long menuSize = menuRepository.count();
		for (int i = 0; i < ids.length; i++) {
			Menu menu = menuRepository.findOne(Long.valueOf(ids[i]));
			if (menu != null) {
				menu.setPosition((int) (menuSize + i));
				menuRepository.save(menu);
			}

		}
	}


	/**
	 * web index page menu list
	 * @param menuList
	 * @param flag true:第一次调用 ，false:其它
	 * @return List<MenuView>
	 */
	@Transactional(rollbackOn = Exception.class)
	public List<MenuView> getWebTopIndexMenu(List<Menu> menuList,boolean flag) {
		
		Pageable pageable = new PageRequest(0, Integer.MAX_VALUE,Direction.ASC,POSITION);
		if(flag){
			menuList = menuRepository.findByLevelAndStatus(1,MenuRepository.MENU_STATUS_TOP,pageable);
		}
		List<MenuView> retList = new ArrayList<>();
		for(Menu menu: menuList){
			MenuView menuView = new MenuView();
			menuView.setMenu(menu);
			List<MenuView>  subMenu = getWebTopIndexMenu(menuRepository.findByMenuId(menu.getId(),pageable),false);
			menuView.setSubMenuViewList(subMenu);
			retList.add(menuView);
			
		}
		
		
		
		return retList;
	}
	
	/**
	 * 获取底部菜单
	 * @param menuList
	 * @param flag true:第一次调用 ，false:其它
	 * @return List<MenuView>
	 */
	@Transactional(rollbackOn = Exception.class)
	public List<MenuView> getWebBottomIndexMenu(List<Menu> menuList,boolean flag) {
		
		Pageable pageable = new PageRequest(0, Integer.MAX_VALUE,Direction.ASC,POSITION);
		if(flag){
			menuList = menuRepository.findByLevelAndStatus(1,MenuRepository.MENU_STATUS_BOTTOM,pageable);
		}
		List<MenuView> retList = new ArrayList<>();
		for(Menu menu: menuList){
			MenuView menuView = new MenuView();
			menuView.setMenu(menu);
			menuView.setSubMenuViewList(getWebBottomIndexMenu(menuRepository.findByMenuId(menu.getId(),pageable),false));
			retList.add(menuView);
		}
		
		return retList;
	}

	@Override
	public CrudRepository<Menu,Long> getRepository() {
		return menuRepository;
	}

	/**
	 * mobile获取菜单
	 * @param level
	 * @param menuType
	 * @return
	 */
	public List<Menu> getMenuByLevelAndStatus(Integer level, String menuType) {
		Pageable pageable = new PageRequest(0, Integer.MAX_VALUE,Direction.ASC,MenuRepository.POSITION);
		
		List<Menu> menuList = new ArrayList<>();
		if(StringHelper.isNotEmpty(menuType)){
			menuList = menuRepository.findByLevelAndStatusAndMenuType(level,MenuRepository.MENU_STATUS_TOP , menuType,pageable);
		}else{
			menuList = menuRepository.findByLevelAndStatus(level,MenuRepository.MENU_STATUS_TOP,pageable);
		}
		return menuList;
	}
	/**
	 * 上传分类图片
	 * @param categoryId
	 * @param attachments
	 */
	@Transactional(rollbackOn = Exception.class)
	public void uploadImg(Long categoryId, MultipartFile[] attachments) {
		// category img 只有 一个图片
		if(attachments.length==1){
			MultipartFile mf = attachments[0];
			Menu menu = menuRepository.findOne(categoryId);
			String defaultImgSrc = "/category/" + categoryId + "_" + menu.getName();
			super.saveImgs(attachments, defaultImgSrc);
			menu.setDefaultSrc(contentPath + defaultImgSrc + "/" + mf.getOriginalFilename());
			menuRepository.save(menu);
		}
		
	}
}
