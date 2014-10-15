package com.essot.web.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.essot.web.backend.dao.DAOFactory;
import com.essot.web.backend.entity.IEssotEntity;
import com.essot.web.backend.entity.concrete.Product;
import com.essot.web.backend.entity.concrete.ProductCategory;
import com.essot.web.backend.entity.concrete.ProductCategoryXProduct;
import com.essot.web.controller.data.MenuData;


public class MenuUtil {

	private static MenuUtil utility;
	
	private static List<MenuData> categories;
	
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
		for(MenuData menu : menuData){
			if(menu != null && menu.getCategoryName().equalsIgnoreCase(categoryName)){
				isExists = true;
				break;
			}
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
	/**
	 * 
	 * @return
	 */
	public static List<MenuData> setValidCategoryCache(DAOFactory daoFactory){
		ProductCategory prodCat = new ProductCategory();
		
		List<IEssotEntity> categoryList = daoFactory.getDAOClass(prodCat).readAllData();
		//Get All The Sub-Categories.
		List<IEssotEntity> subCatList = getSubCategoryList(categoryList);
		//Get All the PROD_X_CAT
		List<IEssotEntity> prodXCat = getProductXCatList(subCatList, daoFactory);
		//Get All Relevant SKUS
		Set<Integer> validSubCategory = getValidSubCat(prodXCat, daoFactory);
		
		MenuUtil.clearMenuCache();		//Clear the Menu Cache before the fresh build.
		
		List<MenuData> list = new ArrayList<MenuData>();
		Set<Integer> categoryKeys = new LinkedHashSet<Integer>();
		for(Integer catKey: validSubCategory){
			IEssotEntity subCategory = daoFactory.getDAOClass(prodCat).findEntityById(catKey);
			if(subCategory != null){
				Integer parentKey = ((ProductCategory)subCategory).getParentCategoryKey();
				if(!categoryKeys.contains(parentKey)){
					do{
						IEssotEntity category = daoFactory.getDAOClass(prodCat).findEntityById(parentKey);
						if(category != null){
							parentKey = ((ProductCategory)category).getParentCategoryKey();
							MenuData catData = new MenuData();
							catData.setCategoryID(((ProductCategory)category).getProductCategoryKey());
							catData.setCategoryName(((ProductCategory)category).getName());
							catData.setParentCategoryID(((ProductCategory)category).getParentCategoryKey());
							catData.setPriority(((ProductCategory)category).getPriority());
							list.add(catData);
							MenuUtil.addCategoryToCache(catData);
						}
					}while(parentKey.intValue() != 0);
				}
				categoryKeys.add(parentKey);
				MenuData subCatData = new MenuData();
				subCatData.setCategoryID(((ProductCategory)subCategory).getProductCategoryKey());
				subCatData.setCategoryName(((ProductCategory)subCategory).getName());
				subCatData.setParentCategoryID(((ProductCategory)subCategory).getParentCategoryKey());
				subCatData.setPriority(((ProductCategory)subCategory).getPriority());
				list.add(subCatData);
				MenuUtil.addCategoryToCache(subCatData);
			}
		}
		return list;
	}
	/**
	 * 
	 * @param categoryList
	 * @return
	 */
	private static List<IEssotEntity> getSubCategoryList(List<IEssotEntity> categoryList){
		List<IEssotEntity> subCatL = new ArrayList<IEssotEntity>();
		if(categoryList != null && !categoryList.isEmpty()){
			for(IEssotEntity category : categoryList){
				if(((ProductCategory)category).getParentCategoryKey().intValue() != 0)
					subCatL.add(category);
			}
		}
		return subCatL;
	}
	/**
	 * 
	 * @param list
	 * @return
	 */
	private static List<IEssotEntity> getProductXCatList(List<IEssotEntity> list, DAOFactory daoFactory){
		ProductCategoryXProduct prodXCat = new ProductCategoryXProduct();
		List<IEssotEntity> prodXCatList = new ArrayList<IEssotEntity>();
		for(IEssotEntity subcat : list){
			Collection<Object> catKey = new ArrayList<Object>();
			catKey.add(((ProductCategory)subcat).getProductCategoryKey());
			List<IEssotEntity> prodXCats = daoFactory.getDAOClass(prodXCat).getFilteredListOnPrimarKey(catKey);
			if(prodXCats != null && !prodXCats.isEmpty())
				prodXCatList.addAll(prodXCats);
		}
		return prodXCatList;
	}
	/**
	 * 
	 * @param prodXCategoryList
	 * @return
	 */
	private static Set<Integer> getValidSubCat(List<IEssotEntity> prodXCategoryList, DAOFactory daoFactory){
		Product product = new Product();
		Set<Integer> vSubCatKeyList = new LinkedHashSet<Integer>();
		for(IEssotEntity productXCat : prodXCategoryList){
			IEssotEntity prod = daoFactory.getDAOClass(product).
					findEntityById(((ProductCategoryXProduct)productXCat).getSkuName());
			if(prod != null && ((Product)prod).getActiveFlag().equalsIgnoreCase("Y"))
				vSubCatKeyList.add(((ProductCategoryXProduct)productXCat).getProductCategoryKey());
		}
		return vSubCatKeyList;
	}
}