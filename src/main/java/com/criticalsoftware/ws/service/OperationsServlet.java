package com.criticalsoftware.ws.service;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.criticalsoftware.ws.operations.OperationManager;
import com.criticalsoftware.ws.operations.OperationRequest;

public class OperationsServlet extends HttpServlet {
		
	private static final long serialVersionUID = 5855507729388869984L;
			
	@Override	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					
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
		    
	    //Serializes the received JSON to a new instance of OperationRequest
	    try {	    
		    String jsonData = sb.toString();					    		   
		    ObjectMapper objectMapper = new ObjectMapper();		    		   
		    OperationRequest operationRequest = objectMapper.readValue(jsonData, OperationRequest.class);		      
		    		    
		    
		} catch (Exception e) {
			response.getWriter().println("Invalid JSON Object.\n" + e.getMessage());
		}		
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("Servlet " + this.getServletName() + " has started");
		super.init();
	}
	
	@Override
	public void destroy() {
		System.out.println("Servlet " + this.getServletName() + " has stopped");
		super.destroy();
	}
}
