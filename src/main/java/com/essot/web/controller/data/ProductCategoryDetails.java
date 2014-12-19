package com.essot.web.controller.data;

import java.util.ArrayList;
import java.util.List;


public class ProductCategoryDetails {
	
	private String skuName;
	
	private String name;
	
	private String description;
	
	private String longDescription;
	
	private Integer price;
	
	private Integer priority;
	
	private List<String> topFeatures = new ArrayList<String>();

	public String getSkuName() {
		return skuName;
	}
	
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	/**
	 * @return the longDescription
	 */
	public String getLongDescription() {
		return longDescription;
	}

	/**
	 * @param longDescription the longDescription to set
	 */
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	/**
	 * @return the priority
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	/**
	 * @return the topFeatures
	 */
	public List<String> getTopFeatures() {
		return topFeatures;
	}

	/**
	 * @param topFeatures the topFeatures to set
	 */
	public void setTopFeatures(List<String> topFeatures) {
		this.topFeatures = topFeatures;
	}
	
	/**
	 * @param topFeatures the topFeatures to set
	 */
	public void addTopFeatures(String topFeatures) {
		this.topFeatures.add(topFeatures);
	}
}
