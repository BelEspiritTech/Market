package com.essot.web.controller.data;

import java.util.ArrayList;
import java.util.List;

public class MenuData {
	private Integer categoryID;
	
	private String categoryName;
	
	private Integer parentCategoryID;
	
	private Integer priority;
	
	private List<MenuData> subCategories = new ArrayList<MenuData>();

	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getParentCategoryID() {
		return parentCategoryID;
	}

	public void setParentCategoryID(Integer parentCategoryID) {
		this.parentCategoryID = parentCategoryID;
	}

	public List<MenuData> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<MenuData> subCategories) {
		this.subCategories = subCategories;
	}
	
	public void addSubCategory(MenuData subMenu) {
		this.subCategories.add(subMenu);
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	public String toString(){
		return categoryName + " " + categoryID;
		
	}
}
