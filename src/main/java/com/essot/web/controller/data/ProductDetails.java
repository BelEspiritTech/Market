package com.essot.web.controller.data;

import java.util.ArrayList;
import java.util.List;

public class ProductDetails {
	private ProductCategoryDetails productDetails;
	
	private List<ProductFeatures> features;
	
	private List<ProductTechSpecs> specs;
	
	private List<ProductEnCodes> enCodes;
	
	private List<RelatedProductDetails> relatedskus;
	
	public List<RelatedProductDetails> getRelatedskus() {
		return relatedskus;
	}

	public void setRelatedskus(List<RelatedProductDetails> relatedskus) {
		this.relatedskus = relatedskus;
	}

	private String defaultEnCode = "default";

	public ProductCategoryDetails getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(ProductCategoryDetails productDetails) {
		this.productDetails = productDetails;
	}

	public List<ProductFeatures> getFeatures() {
		return features;
	}

	public void setFeatures(List<ProductFeatures> features) {
		this.features = features;
	}
	
	public void addFeature(ProductFeatures feature){
		if(this.features == null){
			this.features = new ArrayList<ProductFeatures>();
		}
		
		this.features.add(feature);
	}

	/**
	 * @return the specs
	 */
	public List<ProductTechSpecs> getSpecs() {
		return specs;
	}

	/**
	 * @param specs the specs to set
	 */
	public void setSpecs(List<ProductTechSpecs> specs) {
		this.specs = specs;
	}
	
	public void addSpec(ProductTechSpecs spec){
		if(this.specs == null){
			this.specs = new ArrayList<ProductTechSpecs>();
		}
		
		this.specs.add(spec);
	}

	public List<ProductEnCodes> getEnCodes() {
		return enCodes;
	}

	public void setEnCodes(List<ProductEnCodes> enCodes) {
		this.enCodes = enCodes;
	}
	
	public void addEnCode(ProductEnCodes enCode){
		if(this.enCodes == null){
			this.enCodes = new ArrayList<ProductEnCodes>();
		}
		
		this.enCodes.add(enCode);

	}
	public void addRelatedSKUs(RelatedProductDetails relskus){
		if(this.relatedskus == null){
			this.relatedskus = new ArrayList<RelatedProductDetails>();
		}
		
		this.relatedskus.add(relskus);

	}

	public String getDefaultEnCode() {
		return defaultEnCode;
	}

	public void setDefaultEnCode(String defaultEnCode) {
		this.defaultEnCode = defaultEnCode;
	}	

}
