package com.essot.web.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.essot.web.backend.dao.IEssotDAO;
import com.essot.web.backend.entity.IEssotEntity;
import com.essot.web.backend.entity.concrete.ProductCategory;
import com.essot.web.controller.data.MenuData;


public class MenuUtil {

	@Autowired
	static IEssotDAO productCategoryDAO;
	
	private static MenuUtil utility;
	
	private static List<MenuData> categories;
	
	public static Boolean dbOperation = false;
	
	/**
	 * 
	 */
	private MenuUtil(){
		//private constructor
	}
	
	/**
	 * 
	 * @return
	 */
	public static MenuUtil getInstance(){
		
		if(MenuUtil.utility == null){
			synchronized (MenuUtil.utility) {
				if(MenuUtil.utility == null){
					MenuUtil.utility = new MenuUtil();
				}
			}			
		}
		
		return MenuUtil.utility;
	}

	/**
	 * 
	 * @return
	 */
	public static List<MenuData> getCategories() {
		
		if(MenuUtil.categories == null){
			MenuUtil.categories = new ArrayList<MenuData>();
		}
		
		return MenuUtil.categories;
	}
	
	/**
	 * 
	 */
	public static void clearMenuCache(){
		MenuUtil.categories = null;
	}
	
	/**
	 * 
	 * @param categoryID
	 * @return
	 */
	public static Collection<Object> getSubMenus(Integer categoryID){
		Collection<Object> subCategories = new ArrayList<Object>();
		
		subCategories.add(categoryID);
		
		List<MenuData> allCategories = MenuUtil.getCategories();
		for(MenuData details : allCategories){
			if(details.getParentCategoryID().equals(categoryID)){
				subCategories.add(details.getCategoryID());
			}
		}
		
		return subCategories;
	}
	
	/**
	 * 
	 * @param detail
	 */
	public static void addCategoryToCache(MenuData detail){
		if(detail != null && detail.getCategoryID() != null){
			MenuUtil.getCategories().add(detail);
		}
	}
	
	/**
	 * 
	 * @param categoryName
	 * @return
	 */
	public static boolean categoryExists(String categoryName){
		boolean isExists = false;
		List<MenuData> menuData = MenuUtil.getCategories() ;
		
		/*for(MenuData menu : menuData){
			if(menu != null && menu.getCategoryName().equalsIgnoreCase(categoryName)){
				isExists = true;
				break;
			}
		}*/
		try{
			IEssotEntity productCategory = productCategoryDAO.findEntityById(categoryName);
			if(productCategory != null){
				if(((ProductCategory)productCategory).getName().equalsIgnoreCase(categoryName)){
					isExists = true;
				}
			}
		}catch(Exception e){
			return isExists;
		}
		
		return isExists;
	}
	
	/**
	 * 
	 * @param categoryName
	 * @return
	 */
	public static MenuData getMenu(String categoryName){
		MenuData menuInfo = new MenuData();
		List<MenuData> menuData = MenuUtil.getCategories() ;
				
		for(MenuData menu : menuData){
			if(menu != null && menu.getCategoryName().equalsIgnoreCase(categoryName)){
				menuInfo = menu;
				break;
			}
		}
		
		if(categoryName == null){
			menuInfo.setCategoryID(0);
		}
		
		return menuInfo;
	}
	
}