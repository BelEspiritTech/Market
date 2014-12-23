package com.essot.web.util;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class HTTPUtil {
	private final static String serverURL    = "http://ec2-54-169-51-153.ap-southeast-1.compute.amazonaws.com:8080";
	private final static String s3ServerURL  = "https://s3-ap-southeast-1.amazonaws.com";
	
	public static JSONObject getProductDetailsJSON(String skuName){
		
		HttpURLConnection conn = null;
		JSONObject retObject = null;
		String restURL = serverURL + "/essotg/rest/product/detail/" + skuName;
		try{
		
			URL url = new URL(restURL);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
	
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
	
			JSONObject data= (JSONObject)JSONValue.parse(new InputStreamReader(
					(conn.getInputStream())));
			retObject = (JSONObject) data.get("details");
		
		}catch(Exception e){
			
		}finally{
			conn.disconnect();
		}
		
		return retObject;
	}
	
	public static boolean isBannerAvailable(String skuName){
		
		boolean isAvailable = false;
		HttpURLConnection conn = null;
		String restURL = s3ServerURL + "/essot/"+ skuName + "_banner.jpg";
		try{
		
			URL url = new URL(restURL);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
	
			if (conn.getResponseCode() == 200) {
				isAvailable =  true;
			}
	
			
		
		}catch(Exception e){
			
		}finally{
			conn.disconnect();
		}
		
		return isAvailable;
	}
}
