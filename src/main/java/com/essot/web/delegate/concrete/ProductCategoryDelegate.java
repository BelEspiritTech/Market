package com.essot.web.delegate.concrete;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.essot.web.backend.dao.IEssotDAO;
import com.essot.web.backend.entity.IEssotEntity;
import com.essot.web.backend.entity.concrete.ProductCategory;
import com.essot.web.controller.data.CategoryDetails;
import com.essot.web.controller.data.Menu;
import com.essot.web.controller.data.MenuData;
import com.essot.web.delegate.EssotDelegate;
import com.essot.web.util.MenuUtil;

public class ProductCategoryDelegate extends EssotDelegate {

	@Autowired
	IEssotDAO productCategoryDAO;
	
	public List<CategoryDetails> getAllCategoryInfo(){
		
		List<CategoryDetails> categoryDetails = new ArrayList<CategoryDetails>();
		List<MenuData>  categories = MenuUtil.getCategories();
		if(categories == null || categories.isEmpty()){
			categories = this.getMenuFromDB();
		}
		
		for(MenuData category : categories){
		
			if(category.getPriority() != null && (category.getPriority() > 0 && category.getPriority() <= 6)){
					
				    CategoryDetails categoryDetail = new CategoryDetails();
					
					categoryDetail.setCategoryName(category.getCategoryName());
					categoryDetail.setCategoryID(category.getCategoryID());
					categoryDetail.setParentCategoryID(category.getParentCategoryID());
					
					categoryDetails.add(categoryDetail);
				}				
			}		
		
		return categoryDetails;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Menu> getMenu(){
		List<Menu> retData = new ArrayList<Menu>();
		List<MenuData> data = MenuUtil.getCategories();
		if(data == null || data.isEmpty()){
			data = this.getMenuFromDB();
		}
		
		for(MenuData item : data){
			Menu menu = new Menu();
			
			if(item.getParentCategoryID() == null || item.getParentCategoryID() == 0){
				menu.setCategoryID(item.getCategoryID());
				menu.setCategoryName(item.getCategoryName());
				
				retData.add(menu);
			}else{
				
				for(Menu retItem : retData){
					if(retItem.getCategoryID().intValue() == item.getParentCategoryID().intValue()){
						
						menu.setCategoryID(item.getCategoryID());
						menu.setCategoryName(item.getCategoryName());
						
						retItem.addSubCategory(menu);
						
						break;
					}
				}
			}
		}
			
		return retData;
	}
	
	public void persistEntity(IEssotEntity entity) {
		productCategoryDAO.persistEntity(entity);
	}

	public IEssotEntity findEntityById(String id) {
		return productCategoryDAO.findEntityById(id);
	}

	public void updateEntity(IEssotEntity entity) {
		productCategoryDAO.updateEntity(entity);
	}

	public void deleteEntity(IEssotEntity entity) {
		productCategoryDAO.deleteEntity(entity);
	}
	
	/**
	 * 
	 * @return
	 */
	public synchronized List<MenuData> getMenuFromDB(){
		
		List<MenuData> data = new ArrayList<MenuData>();
		if((MenuUtil.getCategories() == null || MenuUtil.getCategories().isEmpty())){
			List<IEssotEntity> dbData = productCategoryDAO.readAllData();
			for(IEssotEntity category :dbData){
				MenuData item = new MenuData();
				
				item.setCategoryID(((ProductCategory)category).getProductCategoryKey());
				item.setCategoryName(((ProductCategory)category).getCategoryTitle());
				item.setPriority(((ProductCategory)category).getPriority());
				item.setParentCategoryID(((ProductCategory)category).getParentCategoryKey());
				
				data.add(item);
				MenuUtil.addCategoryToCache(item);
			}
		}
		
		return data;
	}
	
	/**
	 * 
	 */
	public void clearCache(){
		MenuUtil.clearMenuCache();
	}

}
