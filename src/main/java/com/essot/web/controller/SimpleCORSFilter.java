package com.essot.web.controller;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.stereotype.Component;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

@Component
public class SimpleCORSFilter implements ContainerResponseFilter {

	public ContainerResponse filter(ContainerRequest req,
			ContainerResponse contResp) {
		 ResponseBuilder resp = Response.fromResponse(contResp.getResponse());
	        resp.header("Access-Control-Allow-Origin", "*")
	                .header("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
	 
	        String reqHead = req.getHeaderValue("Access-Control-Request-Headers");
	 
	        if(null != reqHead && !reqHead.equals("")){
	            resp.header("Access-Control-Allow-Headers", reqHead);
	        }
	 
	        contResp.setResponse(resp.build());
	            return contResp;
	    }
	}
