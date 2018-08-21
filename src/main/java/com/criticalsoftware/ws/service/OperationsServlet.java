package com.criticalsoftware.ws.service;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.criticalsoftware.ws.operations.OperationManager;
import com.criticalsoftware.ws.operations.OperationResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OperationsServlet extends HttpServlet {
		
	private static final long serialVersionUID = 5855507729388869984L;
			
	@Override	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					
		response.setContentType("application/json;charset=UTF-8");
				
		//Reads the JSON request
		StringBuilder sb = new StringBuilder();
	    BufferedReader reader = request.getReader();
	    	    
	    try {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            sb.append(line).append('\n');
	        }
	    } finally {
	        reader.close();
	    }		    	    
	    
	    //Processes the request retrieves a response
    	String jsonData = sb.toString();			    		   		    	      		    		   		    		    	    
    	OperationResponse jsonResponse = OperationManager.processRequest(jsonData);	    	  	     	    	  
    	    	
    	ObjectMapper objectMapper = new ObjectMapper();
    	
    	try {
    		response.setStatus(jsonResponse.getStatus());
			objectMapper.writeValue(response.getWriter(), jsonResponse);
			
		} catch (JsonProcessingException e) {
			response.getWriter().print("JSON serialization error while creating response.");
		}    	    
	}		
}
