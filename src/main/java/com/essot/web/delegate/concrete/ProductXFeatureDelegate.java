package com.essot.web.delegate.concrete;

import org.springframework.beans.factory.annotation.Autowired;

import com.essot.web.backend.dao.IEssotDAO;
import com.essot.web.backend.entity.IEssotEntity;
import com.essot.web.delegate.EssotDelegate;

public class ProductXFeatureDelegate extends EssotDelegate {

	@Autowired
	IEssotDAO productXFeatureDAO;
	
	public void persistEntity(IEssotEntity entity) {
		productXFeatureDAO.persistEntity(entity);
	}

	public IEssotEntity findEntityById(Integer id) {
		return productXFeatureDAO.findEntityById(id);
	}

	public void updateEntity(IEssotEntity entity) {
		productXFeatureDAO.updateEntity(entity);
	}

	public void deleteEntity(IEssotEntity entity) {
		productXFeatureDAO.deleteEntity(entity);
	}

}
