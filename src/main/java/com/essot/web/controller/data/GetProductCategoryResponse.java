package com.essot.web.controller.data;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GetProductCategoryResponse {
	
	private List<ProductCategoryDetails> products;
	
	private List<String> bannerList;
	
	private String categories; 
	
	private String headerText = "FEATURED PRODUCTS";

	public List<ProductCategoryDetails> getProducts() {
		return products;
	}

	public void setProducts(List<ProductCategoryDetails> products) {
		this.products = products;
	}

	public List<String> getBannerList() {
		return bannerList;
	}

	public void setBannerList(List<String> bannerList) {
		this.bannerList = bannerList;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	/**
	 * @return the headerText
	 */
	public String getHeaderText() {
		return headerText;
	}

	/**
	 * @param headerText the headerText to set
	 */
	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}	
}
