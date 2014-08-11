package com.essot.web.upload;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.essot.web.backend.dao.DAOFactory;
import com.essot.web.backend.entity.IEssotEntity;
import com.essot.web.backend.entity.concrete.Product;
import com.essot.web.backend.entity.concrete.ProductCategory;
import com.essot.web.backend.entity.concrete.ProductCategoryXProduct;
import com.essot.web.backend.entity.concrete.ProductXENCode;
import com.essot.web.backend.entity.concrete.ProductXFeature;
import com.essot.web.backend.entity.concrete.ProductXTechSpec;
import com.essot.web.controller.data.MenuData;
import com.essot.web.upload.data.BasicExcelData;
import com.essot.web.util.MenuUtil;

public class ExcelDataHelper {
	
	@Autowired
	private DAOFactory daoFactory;
	/**
	 * 
	 * @param categoryName
	 * @param parentCategoryName
	 */
	public void createCategoryObject(String categoryName, String parentCategoryName){
		ProductCategory entity = new ProductCategory();
		
		entity.setAccessKey(1);
		entity.setActiveFlag("Y");
		entity.setCategoryTitle(categoryName);
		entity.setCreatedBy(1);
		entity.setCreationDate(new Date());
		entity.setDescription("Description for "+categoryName);
		entity.setEndDate(new Date());
		entity.setName(categoryName);
		entity.setOwnedBy(1);
		entity.setParentCategoryKey(MenuUtil.getMenu(parentCategoryName).getCategoryID());
		entity.setPriority(0);
		entity.setResourceKey(1);
		entity.setSequenceId(1);
		entity.setStartDate(new Date());
		entity.setStatus(0);
		entity.setUpdateDate(new Date());
		entity.setUpdatedBy(1);
		
		Integer id = (Integer)this.persistEntity(entity);
		
		MenuData data = new MenuData();
		
		data.setCategoryID(id);
		data.setCategoryName(categoryName);
		data.setParentCategoryID(entity.getParentCategoryKey());
		data.setPriority(entity.getPriority());
		
		MenuUtil.addCategoryToCache(data);
				
	}
	
	/**
	 * 
	 * @param categoryName
	 * @param parentCategoryName
	 */
	public void updateProductInfo(BasicExcelData data){
		//First Update Product X Category
		this.updateProductCategory(data);
		//Second update Prodcut
		this.updateProduct(data);
	}
	
	
	/**
	 * 
	 * @param data
	 */
	private void updateProductCategory(BasicExcelData data){
		
		ProductCategoryXProduct blank = new ProductCategoryXProduct();
		blank.setSkuName(data.getSkuName());
		
		Collection<Object> categoryList = new ArrayList<Object>();
		categoryList.add(MenuUtil.getMenu(data.getSubCategory()).getCategoryID());
		
		List<IEssotEntity> productList = daoFactory.getDAOClass(blank).getFilteredListOnPrimarKey(categoryList);
		boolean isExist = false;
		
		if(productList != null && !productList.isEmpty()){
			for(IEssotEntity product : productList ) {
				if(blank.getSkuName().equalsIgnoreCase(((ProductCategoryXProduct)product).getSkuName())){
					isExist = true;
					break;
				}				
			}
		}
		
		if(!isExist){
			ProductCategoryXProduct entity = new ProductCategoryXProduct();
			
			entity.setActiveFlag("Y");
			entity.setCreatedBy(0);
			entity.setCreationDate(new Date());
			entity.setEndDate(new Date());
			entity.setProductCategoryKey(MenuUtil.getMenu(data.getSubCategory()).getCategoryID());
			entity.setSequenceId(0);
			entity.setSkuName(data.getSkuName());
			entity.setStartDate(new Date());
			entity.setStatus(0);
			entity.setUpdateDate(new Date());
			entity.setUpdatedBy(0);
			
			this.persistEntity(entity);
		}
		
	}
	
	/**
	 * 
	 * @param data
	 */
	private void updateProduct(BasicExcelData data){
		
		Product blank = new Product();
		blank.setSkuName(data.getSkuName());
		
		Collection<Object> skuList = new ArrayList<Object>();
		skuList.add(data.getSkuName());
		
		List<IEssotEntity> productList = daoFactory.getDAOClass(blank).getFilteredListOnPrimarKey(skuList);
		boolean isExist = false;
		
		if(productList != null && !productList.isEmpty()){
			for(IEssotEntity product : productList ) {
				if(blank.getSkuName().equalsIgnoreCase(((Product)product).getSkuName())){
					isExist = true;
					blank = (Product)product;
					break;
				}				
			}
		}
		
		if(!isExist){
			Product entity = new Product();
			
			entity.setActiveFlag(data.getActiveFlag());
			entity.setCreatedBy(0);
			entity.setCreationDate(new Date());
			entity.setSkuName(data.getSkuName());
			entity.setUpdateDate(new Date());
			entity.setUpdatedBy(0);
			entity.setAccessKey(0);
			entity.setAssignedFlag(0);
			entity.setB2cNowPrice(data.getPrice());
			entity.setBrandId("");
			entity.setBrandName("");
			entity.setComponentSubType(0);
			entity.setComponentType(0);
			entity.setDescription(data.getShortDesc());
			entity.setLongDescription(data.getLongDesc());
			entity.setEndDateActive(new Date());
			entity.setIsMarkedForDeletion(0);
			entity.setLocalUpdateFlag("");
			entity.setMfgId(0);
			entity.setMfgSku("");
			entity.setMinimumOrderQty(0);
			entity.setModelKey(0);
			entity.setName(data.getProductName());
			entity.setOwnedBy(0);
			entity.setParentSkuName("");
			entity.setPickableFlag("");
			entity.setPreConfigFlag(0);
			entity.setPriority(0);
			entity.setProductColor("");
			entity.setQtyPerUnit(0d);
			entity.setResourceKey(0);
			entity.setSellableFlag(0);
			entity.setServiceFlag(0);
			entity.setServiceMaintenanceModelKey(0);
			entity.setShippableFlag("");
			entity.setShippingDiscountFlag("");
			entity.setSizeChart("");
			entity.setSkuAuthority("");
			entity.setStartDateActive(new Date());
			entity.setStatusCode(0);
			entity.setUnitOfMeasure("");
			entity.setUnitOfMeasureCode(0);
			entity.setValidFlag(0);
			
			this.persistEntity(entity);
		}else{
			//Product Already exists. Update product
			blank.setB2cNowPrice(data.getPrice());
			blank.setDescription(data.getShortDesc());
			blank.setLongDescription(data.getLongDesc());
			blank.setActiveFlag(data.getActiveFlag());
			blank.setName(data.getProductName());
			
			this.updateEntity(blank);
			
		}
		
	}
	
	/**
	 * 
	 * @param data
	 */
	public void pushAddonData(BasicExcelData data){
		this.pushEnCodes(data);
		this.pushFeatures(data);
		this.pushTechSpecs(data);
	}
	
	/**
	 * 
	 * @param data
	 */
	public void pushEnCodes(BasicExcelData data){
		List<String> enCodes = data.getEncodes();
		
		//getAll EnCodes
		ProductXENCode blank = new ProductXENCode();
		blank.setSkuName(data.getSkuName());
		
		Collection<Object> skuNameList = new ArrayList<Object>();
		skuNameList.add(data.getSkuName());
		
		List<IEssotEntity> enCodeList = daoFactory.getDAOClass(blank).getFilteredListOnPrimarKey(skuNameList);
		
		if(enCodeList != null && !enCodeList.isEmpty()){
			for(IEssotEntity enCode : enCodeList ) {
				if(enCodes.contains(((ProductXENCode)enCode).getEnCode())){
					enCodes.remove(((ProductXENCode)enCode).getEnCode());
				}							
			}
		}
		
		for(String enCode : enCodes){
			ProductXENCode entity = new ProductXENCode();
			
			entity.setSkuName(data.getSkuName());
			entity.setEnCode(enCode);
			entity.setCreatedBy(0);
			entity.setCreationDate(new Date());
			entity.setUpdateDate(new Date());
			entity.setUpdatedBy(0);
			
			this.persistEntity(entity);
		}
	}
	
	/**
	 * 
	 * @param data
	 */
	public void pushFeatures(BasicExcelData data){
		List<String> features = data.getFeatures();
		//getAll Features
		ProductXFeature blank = new ProductXFeature();
		blank.setSkuName(data.getSkuName());
		
		Collection<Object> skuNameList = new ArrayList<Object>();
		skuNameList.add(data.getSkuName());
		
		List<IEssotEntity> featureList = daoFactory.getDAOClass(blank).getFilteredListOnPrimarKey(skuNameList);
		Integer maxValue = 0;
		
		if(featureList != null && !featureList.isEmpty()){
			for(IEssotEntity feature : featureList ) {
				if(features.contains(((ProductXFeature)feature).getValue())){
					features.remove(((ProductXFeature)feature).getValue());
				}
				if(maxValue < ((ProductXFeature)feature).getFeatureKey()){
					maxValue = ((ProductXFeature)feature).getFeatureKey();
				}				
			}
		}
		
		for(String feature : features){
			ProductXFeature entity = new ProductXFeature();
			
			entity.setSkuName(data.getSkuName());
			entity.setEndDate(new Date());
			entity.setActiveFlag("Y");
			entity.setAssignType(0);
			entity.setDataType(0);
			entity.setFeatureKey(++maxValue);
			entity.setRelationType(0);
			entity.setSequenceNumber(0);
			entity.setStartDate(new Date());
			entity.setStatus(0);
			entity.setValue(feature);
			
			this.persistEntity(entity);
		}
	}
	
	
	/**
	 * 
	 * @param data
	 */
	public void pushTechSpecs(BasicExcelData data){
		List<String> specs = data.getTechSpecs();
		
		//getAll EnCodes
		ProductXTechSpec blank = new ProductXTechSpec();
		blank.setSkuName(data.getSkuName());
		
		Collection<Object> skuNameList = new ArrayList<Object>();
		skuNameList.add(data.getSkuName());
		
		List<IEssotEntity> specList = daoFactory.getDAOClass(blank).getFilteredListOnPrimarKey(skuNameList);
		
		if(specList != null && !specList.isEmpty()){
			for(IEssotEntity techSpec : specList ) {
				if(specs.contains((((ProductXTechSpec)techSpec).getTechKey())+":"+(((ProductXTechSpec)techSpec).getValue()))){
					specs.remove((((ProductXTechSpec)techSpec).getTechKey())+":"+(((ProductXTechSpec)techSpec).getValue()));
				}							
			}
		}
		for(String spec : specs){
			ProductXTechSpec entity = new ProductXTechSpec();
			String[] splitSpec = spec.split(":");
			
			entity.setSkuName(data.getSkuName());
			entity.setActiveFlag("Y");
			entity.setAssignType(0);
			entity.setDataType(0);
			entity.setEndDate(new Date());
			entity.setRelationType(0);
			entity.setSequenceNumber(0);
			entity.setStartDate(new Date());
			entity.setStatus(0);
			entity.setTechKey(splitSpec[0]);
			entity.setValue(splitSpec[1]);
				
			this.persistEntity(entity);
		}
	}
	
	/**
	 * 
	 * @param entity
	 */
	private Serializable persistEntity(IEssotEntity entity){
		return daoFactory.getDAOClass(entity).persistEntity(entity);
	}
	
	/**
	 * 
	 * @param entity
	 */
	private void updateEntity(IEssotEntity entity){
		daoFactory.getDAOClass(entity).updateEntity(entity);
	}
}
