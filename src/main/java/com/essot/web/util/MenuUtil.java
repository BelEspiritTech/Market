package com.essot.web.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
		Collections.sort(allCategories, EssotComparatorFactory.getInstance(EssotComparatorEnum.GET_MENU));
		for(MenuData details : allCategories){
			if(details.getParentCategoryID().equals(categoryID) || subCategories.contains(details.getParentCategoryID())){
				subCategories.add(details.getCategoryID());
			}
		}
		
		return subCategories;
	}
	
	/**
	 * 
	 * @param categoryID
	 * @return
	 */
	public static String getChildCategoryForSecondLevel(Integer categoryID){
		String retValue = "";
		
		List<MenuData> allCategories = MenuUtil.getCategories();
		Collections.sort(allCategories, EssotComparatorFactory.getInstance(EssotComparatorEnum.GET_MENU));
		for(MenuData details : allCategories){
			if(details.getParentCategoryID().equals(categoryID)){
				retValue = details.getCategoryName();
			}
		}
		
		if(retValue == null || "".equals(retValue)){
			retValue = "FEATURED PRODUCTS";
		}
		
		return retValue;
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

		//Get All the PROD_X_CAT
		List<IEssotEntity> prodXCat = getProductXCatList(getSubCategoryList(daoFactory.getDAO(EssotDAOEnum.PRODUCT_CATEGORY).readAllData()), daoFactory);
		//Get All Relevant SKUS
		Set<Integer> validSubCategory = getValidSubCat(prodXCat, daoFactory);
		
		MenuUtil.clearMenuCache();		//Clear the Menu Cache before the fresh build.
		
		List<MenuData> list = new ArrayList<MenuData>();
		Set<Integer> categoryKeys = new LinkedHashSet<Integer>();
		for(Integer catKey: validSubCategory){
			IEssotEntity subCategory = daoFactory.getDAO(EssotDAOEnum.PRODUCT_CATEGORY).findEntityById(catKey);
			if(subCategory != null){
				Integer parentKey = ((ProductCategory)subCategory).getParentCategoryKey();
				while(parentKey != 0){
					IEssotEntity category = daoFactory.getDAO(EssotDAOEnum.PRODUCT_CATEGORY).findEntityById(parentKey);
					if(category != null && !categoryKeys.contains(parentKey)){
						categoryKeys.add(parentKey);
						parentKey = ((ProductCategory)category).getParentCategoryKey();
						MenuData catData = new MenuData();
						catData.setCategoryID(((ProductCategory)category).getProductCategoryKey());
						catData.setCategoryName(((ProductCategory)category).getName());
						catData.setParentCategoryID(((ProductCategory)category).getParentCategoryKey());
						catData.setPriority(((ProductCategory)category).getPriority());
						list.add(catData);
						MenuUtil.addCategoryToCache(catData);
					}
					if(categoryKeys.contains(parentKey)){
						break;
					}					
				}				
				if(!categoryKeys.contains(((ProductCategory)subCategory).getProductCategoryKey())){			
					MenuData subCatData = new MenuData();
					subCatData.setCategoryID(((ProductCategory)subCategory).getProductCategoryKey());
					subCatData.setCategoryName(((ProductCategory)subCategory).getName());
					subCatData.setParentCategoryID(((ProductCategory)subCategory).getParentCategoryKey());
					subCatData.setPriority(((ProductCategory)subCategory).getPriority());
					categoryKeys.add(((ProductCategory)subCategory).getProductCategoryKey());
					list.add(subCatData);
					MenuUtil.addCategoryToCache(subCatData);					
				}
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
		List<IEssotEntity> prodXCatList = new ArrayList<IEssotEntity>();
		for(IEssotEntity subcat : list){
			Collection<Object> catKey = new ArrayList<Object>();
			catKey.add(((ProductCategory)subcat).getProductCategoryKey());
			List<IEssotEntity> prodXCats = daoFactory.getDAO(EssotDAOEnum.PRODUCT_CATEGORY_X_PRODUCT).getFilteredListOnPrimarKey(catKey);
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
		Set<Integer> vSubCatKeyList = new LinkedHashSet<Integer>();
		for(IEssotEntity productXCat : prodXCategoryList){
			IEssotEntity prod = daoFactory.getDAO(EssotDAOEnum.PRODUCT).
					findEntityById(((ProductCategoryXProduct)productXCat).getSkuName());
			if(prod != null && ((Product)prod).getActiveFlag().equalsIgnoreCase("Y")){
				
				if(!vSubCatKeyList.contains(((ProductCategoryXProduct)productXCat).getProductCategoryKey())){
					vSubCatKeyList.add(((ProductCategoryXProduct)productXCat).getProductCategoryKey());
				}
			}
		}
		
		return vSubCatKeyList;
	}
}