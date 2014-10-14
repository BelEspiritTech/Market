package com.essot.web.delegate;

import com.essot.web.backend.entity.IEssotEntity;

public interface IEssotDelegate {

	void persistEntity(IEssotEntity entity);
	
	IEssotEntity findEntityById(String id);
    
	void updateEntity(IEssotEntity entity);
    
	void deleteEntity(IEssotEntity entity);
	
}
