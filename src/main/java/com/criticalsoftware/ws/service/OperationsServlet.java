package com.criticalsoftware.ws.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidParameterException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.criticalsoftware.ws.operations.OperationManager;
import com.criticalsoftware.ws.operations.OperationRequest;
import com.criticalsoftware.ws.operations.OperationValidResponse;

public class OperationsServlet extends HttpServlet {
		
	private static final long serialVersionUID = 5855507729388869984L;
			
	@Override	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter responseWriter = response.getWriter();
		
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
		    
		    
		    OperationValidResponse result = OperationManager.processRequest(operationRequest);
		    
		    System.out.println("Resultado = " + result);
		    
		    

		    
		} catch (JsonParseException parseException) {			
			responseWriter.println("Invalid JSON format.\n" + parseException.getMessage());
			
			log("Invalid JSON format.\n" + parseException.getMessage());
			
		} catch (JsonMappingException mappingException) {
			responseWriter.println("Unrecognized JSON elements.\n" + mappingException.getMessage());		
			
			log("Unrecognized JSON elements.\n" + mappingException.getMessage());
			
		} catch (InvalidParameterException invalidParamException) {
			responseWriter.println(invalidParamException.getMessage());					
			log(invalidParamException.getMessage());
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
