package com.essot.web.controller.data;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GetProductCategoryResponse {
	
	private List<ProductCategoryDetails> products;
	
	private List<String> bannerList;

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
}
