package com.essot.web.controller.data;


/**
 * @author I068440
 *
 */

public class CategoryDetails {
	
	private String categoryName;
	private Integer categoryID;
	private Integer parentCategoryID;
	
	public String getCategoryName() {
		return categoryName.toLowerCase().replaceAll(" ", "");
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}

	public Integer getParentCategoryID() {
		return parentCategoryID;
	}

	public void setParentCategoryID(Integer parentCategoryID) {
		this.parentCategoryID = parentCategoryID;
	}
}
