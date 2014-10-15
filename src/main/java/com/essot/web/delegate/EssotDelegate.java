package com.essot.web.delegate;

import org.springframework.beans.factory.annotation.Autowired;

import com.essot.web.backend.dao.DAOFactory;
import com.essot.web.backend.entity.IEssotEntity;
import com.essot.web.util.ResourceReader;

public abstract class EssotDelegate {
	
	@Autowired
	private ResourceReader resourceReader;
	
	@Autowired
	protected DAOFactory daoFactory; 
	
	/**
	 * Returns the message from the bundle for the given key
	 * @param key
	 * @return
	 */
	public String getMessage(String key){
		String retValue = null;
		try{
			retValue = resourceReader.getMessage(key);
		}catch(Exception e){
			
		}
		
		return retValue;
	}
	
	/**
	 * Returns the message from the bundle for the given key with the given args
	 * @param key
	 * @return
	 */
	public String getMessage(String key, Object[] args){
		String retValue = null;
		try{
			retValue = resourceReader.getMessage(key, args);
		}catch(Exception e){
			
		}
		
		return retValue;
	}
	
	public abstract void persistEntity(IEssotEntity entity);
	
	public abstract IEssotEntity findEntityById(Integer id);
    
	public abstract void updateEntity(IEssotEntity entity);
    
	public abstract void deleteEntity(IEssotEntity entity);
}
