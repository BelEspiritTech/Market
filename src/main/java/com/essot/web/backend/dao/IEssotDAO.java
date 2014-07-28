package com.essot.web.backend.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.essot.web.backend.entity.IEssotEntity;

public interface IEssotDAO {

	Serializable persistEntity(IEssotEntity entity);		           
	IEssotEntity findEntityById(String id);		           
	void updateEntity(IEssotEntity entity);	           
	void deleteEntity(IEssotEntity entity);	
	
	List<IEssotEntity> readAllData();
	List<IEssotEntity> getFilteredListOnPrimarKey(Collection<Object> keys);
}
