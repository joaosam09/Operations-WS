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

import com.criticalsoftware.ws.models.OperationRequestModel;
import com.criticalsoftware.ws.operations.OperationManager;;

public class OperationsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
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
		    		    
		    String jsonData = sb.toString();					    		   
		    ObjectMapper objectMapper = new ObjectMapper();
		    
		    //Serializes the received JSON to a new OperationRequestModel
		    OperationRequestModel operationModel = objectMapper.readValue(jsonData, OperationRequestModel.class);		      
		    			
		} catch (Exception e) {
			response.getWriter().println("Invalid JSON Object.");
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
