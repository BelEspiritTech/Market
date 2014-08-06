package com.essot.web.controller.data;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GetProductCategoryResponse {
	
	private List<ProductCategoryDetails> products;
	
	private List<String> bannerList;
	
	private String categories; 

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
	
}
