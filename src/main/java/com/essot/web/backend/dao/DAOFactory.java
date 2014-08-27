package com.essot.web.backend.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.essot.web.backend.dao.concrete.RelatedSKUDAO;
import com.essot.web.backend.entity.IEssotEntity;
import com.essot.web.backend.entity.concrete.Product;
import com.essot.web.backend.entity.concrete.ProductCategory;
import com.essot.web.backend.entity.concrete.ProductCategoryXProduct;
import com.essot.web.backend.entity.concrete.ProductXENCode;
import com.essot.web.backend.entity.concrete.ProductXFeature;
import com.essot.web.backend.entity.concrete.ProductXTechSpec;

public class DAOFactory {
	
	@Autowired
	private IEssotDAO productDAO;
	
	@Autowired
	private IEssotDAO productCategoryDAO;
	
	@Autowired
	private IEssotDAO productCategoryXProductDAO;
	
	@Autowired
	private IEssotDAO productFeatureDAO;
	
	@Autowired
	private IEssotDAO productEnCodeDAO;
	
	@Autowired
	private IEssotDAO productTechSpecDAO;
	
	@Autowired
	private IEssotDAO relatedSKUDAO;
	
	public IEssotDAO getDAOClass(IEssotEntity entity){
		if(entity instanceof Product){
			return productDAO;
		}else if (entity instanceof ProductCategory){
			return productCategoryDAO;
		}else if (entity instanceof ProductCategoryXProduct){
			return productCategoryXProductDAO;
		}else if (entity instanceof ProductXENCode){
			return productEnCodeDAO;
		}else if (entity instanceof ProductXFeature){
			return productFeatureDAO;
		}else if (entity instanceof ProductXTechSpec){
			return productTechSpecDAO;
		}else if (entity instanceof RelatedSKUDAO){
			return relatedSKUDAO;	
		}else {
		
			return null;
		}
	}
}
