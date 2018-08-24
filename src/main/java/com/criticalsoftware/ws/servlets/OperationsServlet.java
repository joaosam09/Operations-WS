package com.criticalsoftware.ws.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.criticalsoftware.ws.operation.OperationManager;
import com.criticalsoftware.ws.operation.OperationResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Http Servlet responsible for processing operation requests and retrieving operation responses to the clients.
 * 
 * @author João Santos
 * @version 1.0
 */
public class OperationsServlet extends HttpServlet {
		
	private static final long serialVersionUID = 5855507729388869984L;
			
	@Override	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {												
	    BufferedReader reader = request.getReader();	        	       	    	
    	OperationResponse jsonResponse = OperationManager.processOperationRequest(reader);    	    	   	
    	ObjectMapper objectMapper = new ObjectMapper();
    	
    	response.setContentType("application/json;charset=UTF-8");
		response.setStatus(jsonResponse.getStatus());
		objectMapper.writeValue(response.getWriter(), jsonResponse);					  	    
	}		
}
