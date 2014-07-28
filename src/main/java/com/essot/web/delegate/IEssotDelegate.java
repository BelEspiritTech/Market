package com.essot.web.delegate;

import com.essot.web.backend.entity.IEssotEntity;

public interface IEssotDelegate {

	void persistEntity(IEssotEntity employee);
	
	IEssotEntity findEntityById(String id);
    
	void updateEntity(IEssotEntity employee);
    
	void deleteEntity(IEssotEntity employee);
	
}
