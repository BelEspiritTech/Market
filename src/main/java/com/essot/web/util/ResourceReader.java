package com.essot.web.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class ResourceReader {
	
	@Autowired
	private MessageSource resource;
	
	/**
	 * Returns message for the given key
	 * @param key
	 * @return
	 */
	public String getMessage(String key){ 
		return this.getMessage(key,null);
	}
	
	/**
	 * Returns message for the given key subsituted with the provided arguments
	 * @param key
	 * @param args
	 * @return
	 */
	public String getMessage(String key, Object[] args){ 
		return resource.getMessage(key,args, Locale.getDefault());
	}
}
