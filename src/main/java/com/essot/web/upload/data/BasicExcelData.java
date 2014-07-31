package com.essot.web.upload.data;

import java.util.ArrayList;
import java.util.List;

public class BasicExcelData {
	
	private String category;
	
	private String subCategory;
	
	private String skuName;
	
	private String productName;
	
	private String shortDesc;
	
	private String longDesc;
	
	private Integer price;
	
	private String activeFlag;
	
	private List<String> encodes = new ArrayList<String>();
	
	private List<String> features = new ArrayList<String>();
	
	private List<String> techSpecs = new ArrayList<String>();

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the subCategory
	 */
	public String getSubCategory() {
		return subCategory;
	}

	/**
	 * @param subCategory the subCategory to set
	 */
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	/**
	 * @return the skuName
	 */
	public String getSkuName() {
		return skuName;
	}

	/**
	 * @param skuName the skuName to set
	 */
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the shortDesc
	 */
	public String getShortDesc() {
		return shortDesc;
	}

	/**
	 * @param shortDesc the shortDesc to set
	 */
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	/**
	 * @return the longDesc
	 */
	public String getLongDesc() {
		return longDesc;
	}

	/**
	 * @param longDesc the longDesc to set
	 */
	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	/**
	 * @return the price
	 */
	public Integer getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}

	/**
	 * @return the encodes
	 */
	public List<String> getEncodes() {
		return encodes;
	}

	/**
	 * @param encodes the encodes to set
	 */
	public void setEncodes(List<String> encodes) {
		this.encodes = encodes;
	}
	
	/**
	 * 
	 * @param enCode
	 */
	public void addEnCode(String enCode){
		this.encodes.add(enCode);
	}

	/**
	 * @return the features
	 */
	public List<String> getFeatures() {
		return features;
	}

	/**
	 * @param features the features to set
	 */
	public void setFeatures(List<String> features) {
		this.features = features;
	}
	
	/**
	 * 
	 * @param feature
	 */
	public void addFeature(String feature){
		this.features.add(feature);
	}

	/**
	 * @return the techSpecs
	 */
	public List<String> getTechSpecs() {
		return techSpecs;
	}

	/**
	 * @param techSpecs the techSpecs to set
	 */
	public void setTechSpecs(List<String> techSpecs) {
		this.techSpecs = techSpecs;
	}
	
	/**
	 * 
	 * @param techSpec
	 */
	public void addTechSpec(String techSpec){
		this.techSpecs.add(techSpec);
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	

}
