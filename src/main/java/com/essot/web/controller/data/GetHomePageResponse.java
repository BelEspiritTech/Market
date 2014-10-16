package com.essot.web.controller.data;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GetHomePageResponse {
		
	private List<CategoryDetails> categories;
	
	private List<ProductCategoryDetails> topProducts;

	/**
	 * 
	 * @return
	 */
	public List<CategoryDetails> getCategories() {
		return categories;
	}

	/**
	 * 
	 * @param categories
	 */
	public void setCategories(List<CategoryDetails> categories) {
		this.categories = categories;
	}

	/**
	 * @return the topProducts
	 */
	public List<ProductCategoryDetails> getTopProducts() {
		return topProducts;
	}

	/**
	 * @param topProducts the topProducts to set
	 */
	public void setTopProducts(List<ProductCategoryDetails> topProducts) {
		this.topProducts = topProducts;
	}	
}
