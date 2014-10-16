package com.essot.web.delegate.concrete;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;

import com.essot.web.backend.dao.IEssotDAO;
import com.essot.web.backend.entity.IEssotEntity;
import com.essot.web.backend.entity.concrete.ProductCategory;
import com.essot.web.controller.data.CategoryDetails;
import com.essot.web.controller.data.GetProductCategoryResponse;
import com.essot.web.controller.data.Menu;
import com.essot.web.controller.data.MenuData;
import com.essot.web.delegate.EssotDelegate;
import com.essot.web.util.MenuUtil;

public class ProductCategoryDelegate extends EssotDelegate {

	@Autowired
	IEssotDAO productCategoryDAO;
	
	public List<CategoryDetails> getDisplayCategories(){
		
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
		
		List<MenuData> leftOvers = new ArrayList<MenuData>();
		
		for(MenuData item : data){
			boolean added = false;
			Menu menu = new Menu();
			menu.setCategoryID(item.getCategoryID());
			menu.setCategoryName(item.getCategoryName());
			
			if(item.getParentCategoryID() == null || item.getParentCategoryID() == 0){
				retData.add(menu);
				added = true;
			}else{
				for(Menu retItem : retData){
					if(retItem.getCategoryID().intValue() == item.getParentCategoryID().intValue()){
						retItem.addSubCategory(menu);
						added = true;
						break;
					}else{
						for(Menu subItem : retItem.getSubCategories()){
							if(subItem.getCategoryID().intValue() == item.getParentCategoryID().intValue()){
								subItem.addSubCategory(menu);
								added = true;
								break;
							}
						}
						
						if(added){
							break;
						}
					}
				}
			}
			
			if(!added){
				leftOvers.add(item);
			}
		}
		
		if(!leftOvers.isEmpty()){
			for(MenuData item : leftOvers){
				Menu menu = new Menu();
				boolean added = false;
				menu.setCategoryID(item.getCategoryID());
				menu.setCategoryName(item.getCategoryName());
				
				for(Menu retItem : retData){
					if(retItem.getCategoryID().intValue() == item.getParentCategoryID().intValue()){
						retItem.addSubCategory(menu);
						added = true;				
						break;
					}else{
						for(Menu subItem : retItem.getSubCategories()){
							if(subItem.getCategoryID().intValue() == item.getParentCategoryID().intValue()){
								subItem.addSubCategory(menu);
								added = true;
								break;
							}
						}
						
						if(added){
							break;
						}
					}
				}
			}
		}
			
		return retData;
	}
	
	/**
	 * 
	 */
	public GetProductCategoryResponse getAllCategoriesList(String key){
		GetProductCategoryResponse response = new GetProductCategoryResponse();
		List<String> categoriesNames = new LinkedList<String>();
		String categories = "";
		int parentCategoryKey = 0;
		Integer intKey = new Integer(key);
		
		do{
			IEssotEntity  category =  productCategoryDAO.findEntityById(intKey);
			
			if(category != null){
				if("Y".equalsIgnoreCase(((ProductCategory)category).getActiveFlag())){
					categoriesNames.add(((ProductCategory)category).getName());
					parentCategoryKey = ((ProductCategory)category).getParentCategoryKey().intValue();
					if(parentCategoryKey != 0){
						intKey = new Integer(parentCategoryKey);
					 }
					}
				}
		}while(parentCategoryKey != 0);	
		ListIterator<String> itr = categoriesNames.listIterator(categoriesNames.size());
		while(itr.hasPrevious()){
			categories += (String)itr.previous()+" | ";
		}
		response.setCategories(categories);
		return response;
	}
	
	public void persistEntity(IEssotEntity entity) {
		productCategoryDAO.persistEntity(entity);
	}

	public IEssotEntity findEntityById(Integer id) {
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
		
		List<MenuData> data = MenuUtil.setValidCategoryCache(daoFactory);
		return data;
	}
	
	/**
	 * 
	 */
	public void clearCache(){
		MenuUtil.clearMenuCache();
	}


}
