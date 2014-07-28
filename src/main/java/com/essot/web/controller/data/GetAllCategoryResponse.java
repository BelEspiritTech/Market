package com.essot.web.controller.data;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GetAllCategoryResponse {
		
	private List<CategoryDetails> categories;

	public List<CategoryDetails> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryDetails> categories) {
		this.categories = categories;
	}
	
}
