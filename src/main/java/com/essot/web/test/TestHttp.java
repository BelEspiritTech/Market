package com.essot.web.test;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class TestHttp {
	
	public static void main(String[] args){
		HttpURLConnection conn = null;
	try{
	URL url = new URL("http://ec2-54-169-51-153.ap-southeast-1.compute.amazonaws.com:8080/essotg/rest/product/detail/FuzionBT002");
	conn = (HttpURLConnection) url.openConnection();
	conn.setRequestMethod("GET");
	conn.setRequestProperty("Accept", "application/json");

	if (conn.getResponseCode() != 200) {
		throw new RuntimeException("Failed : HTTP error code : "
				+ conn.getResponseCode());
	}

	JSONObject data= (JSONObject)JSONValue.parse(new InputStreamReader(
			(conn.getInputStream())));
	JSONObject details = (JSONObject)((JSONObject) data.get("details")).get("productDetails");
	
	
	System.out.println("Output from Server .... \n"+ (String)details.get("longDescription"));
	}catch(Exception e){
		System.out.println("Exception : "+e.getMessage());
	}finally{

		conn.disconnect();
	}
	}
	
}
