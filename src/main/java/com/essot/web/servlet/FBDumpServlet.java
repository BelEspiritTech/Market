package com.essot.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.transaction.annotation.Transactional;

import com.essot.web.backend.entity.concrete.Product;
import com.essot.web.util.HTTPUtil;

/**
 * Servlet implementation class FBDumpServlet
 */
@Transactional
public class FBDumpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String PRODUCT_PARAM = "product";

    /**
     * Default constructor. 
     */
    public FBDumpServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		String sku = request.getParameter(PRODUCT_PARAM);
		
		JSONObject productJSON = HTTPUtil.getProductDetailsJSON(sku);
		
		JSONObject productDetails = (JSONObject)productJSON.get("productDetails");
		
		PrintWriter out = response.getWriter();
		
	    out.println("<!DOCTYPE html>");
	    out.println("<html>");
	    	out.println("<head>");
	    		out.println("<meta property=\"og:title\" content=\"Essot India | "+sku+"\" />");
	    		out.println("<meta property=\"og:description\" content=\"" +(String)productDetails.get("longDescription")+" \" />");
	    		out.println("<meta property=\"og:image\" content=\"http://54.169.51.153/essot_img/"+sku+"_share.jpg\" />");
	    		out.println("<meta property=\"og:url\" content=\"http://54.169.51.153/product/"+sku+"\" />");
	    	out.println("</head>");
	    	out.println("<body>");
	    		out.println("<p>"+(String)productDetails.get("longDescription")+"</p>");
	    		out.println("<img src=\"http://54.169.51.153/essot_img/"+sku+"_share.jpg\"/>");
	    		out.println("</body>");
	    out.println("</html>");
	}

	
	
	/*
	 * 
	 * <!DOCTYPE html>
    <html>
        <head>
            <meta property="og:title" content="<?php echo $data->title; ?>" />
            <meta property="og:description" content="<?php echo $data->description; ?>" />
            <meta property="og:image" content="<?php echo $data->image; ?>" />
            <!-- etc. -->
        </head>
        <body>
            <p><?php echo $data->description; ?></p>
            <img src="<?php echo $imageUrl; ?>">
        </body>
    </html>
	 * 
	 * 
	 * 
	 */

}
