package com.essot.web.test;

import com.essot.web.util.HTTPUtil;

public class TestHttp {
	
	public static void main(String[] args){
	
	try{
		System.out.println(HTTPUtil.isBannerAvailable("DataGramz005R"));
	}catch(Exception e){
		System.out.println("Exception : "+e.getMessage());
	}
	
	}
	
}
