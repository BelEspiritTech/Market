package com.essot.web.delegate.concrete;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.essot.web.backend.dao.IEssotDAO;
import com.essot.web.backend.entity.IEssotEntity;
import com.essot.web.backend.entity.concrete.Product;
import com.essot.web.backend.entity.concrete.ProductXENCode;
import com.essot.web.backend.entity.concrete.ProductXFeature;
import com.essot.web.backend.entity.concrete.ProductXTechSpec;
import com.essot.web.controller.data.ProductCategoryDetails;
import com.essot.web.controller.data.ProductDetails;
import com.essot.web.controller.data.ProductEnCodes;
import com.essot.web.controller.data.ProductFeatures;
import com.essot.web.controller.data.ProductTechSpecs;
import com.essot.web.delegate.EssotDelegate;

public class ProductDelegate extends EssotDelegate {

	@Autowired
	IEssotDAO productDAO;
	
	@Autowired
	IEssotDAO productFeatureDAO;
	
	@Autowired
	IEssotDAO productTechSpecDAO;
	
	@Autowired
	IEssotDAO productEnCodeDAO;
	
	public void persistEntity(IEssotEntity entity) {
		productDAO.persistEntity(entity);
	}

	public IEssotEntity findEntityById(String id) {
		return productDAO.findEntityById(id);
	}

	public void updateEntity(IEssotEntity entity) {
		productDAO.updateEntity(entity);
	}

	public void deleteEntity(IEssotEntity entity) {
		productDAO.deleteEntity(entity);
	}
	
	public ProductDetails getDetailsForProduct(String skuName){
		
		ProductDetails details = new ProductDetails();
		Collection<Object> productSKUNames = new ArrayList<Object>();
		
		productSKUNames.add(skuName);
		
		List<IEssotEntity>  products =  productDAO.getFilteredListOnPrimarKey(productSKUNames);
		
		
				
		if(products != null && !products.isEmpty()){
			for(IEssotEntity product : products){
				ProductCategoryDetails productDetails = new ProductCategoryDetails();
				
				productDetails.setDescription(((Product)product).getDescription());
				productDetails.setName(((Product)product).getName());
				productDetails.setSkuName(((Product)product).getSkuName());
				productDetails.setPrice(((Product)product).getB2cNowPrice());
				productDetails.setLongDescription(((Product)product).getDescription());
				
				List<IEssotEntity> features =  productFeatureDAO.getFilteredListOnPrimarKey(productSKUNames);
				
				if(features != null && !features.isEmpty()){
					for(IEssotEntity feature : features){
						ProductFeatures productFeature = new ProductFeatures();
						productFeature.setFeatureKey(((ProductXFeature)feature).getFeatureKey());
						productFeature.setFeatureValue(((ProductXFeature)feature).getValue());
						
						details.addFeature(productFeature);
					}
				}
				
				List<IEssotEntity> enCodes =  productEnCodeDAO.getFilteredListOnPrimarKey(productSKUNames);
				
				if(enCodes != null && !enCodes.isEmpty()){
					for(IEssotEntity enCode : enCodes){
						ProductEnCodes productEncode = new ProductEnCodes();
						productEncode.setEnCode(((ProductXENCode)enCode).getEnCode());
						productEncode.setProdColor(((ProductXENCode)enCode).getProdColor());
						
						details.addEnCode(productEncode);
					}
					
					details.setDefaultEnCode(((ProductXENCode)enCodes.get(0)).getEnCode());					
				}

				
				List<IEssotEntity> specs =  productTechSpecDAO.getFilteredListOnPrimarKey(productSKUNames);
				
				if(specs != null && !specs.isEmpty()){
					for(IEssotEntity spec : specs){
						ProductTechSpecs productSpecs = new ProductTechSpecs();
						productSpecs.setTechKey(((ProductXTechSpec)spec).getTechKey());
						productSpecs.setTechValue(((ProductXTechSpec)spec).getValue());
						
						details.addSpec(productSpecs);
					}
				}
				
				details.setProductDetails(productDetails);
			}
		}
		return details;
	}

}
