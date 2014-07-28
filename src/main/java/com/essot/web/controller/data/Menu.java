package com.essot.web.controller.data;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	private Integer categoryID;
	
	private String categoryName;
	
	private List<Menu> subCategories = new ArrayList<Menu>();

	/**
	 * @return the categoryID
	 */
	public Integer getCategoryID() {
		return categoryID;
	}

	/**
	 * @param categoryID the categoryID to set
	 */
	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the subCategories
	 */
	public List<Menu> getSubCategories() {
		return subCategories;
	}

	/**
	 * @param subCategories the subCategories to set
	 */
	public void setSubCategories(List<Menu> subCategories) {
		this.subCategories = subCategories;
	}
	
	/**
	 * @param subMenu the subCategory to add
	 */
	public void addSubCategory(Menu subMenu) {
		this.subCategories.add(subMenu);
	}
}
