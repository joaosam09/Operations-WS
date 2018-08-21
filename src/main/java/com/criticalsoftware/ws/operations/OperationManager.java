package com.criticalsoftware.ws.operations;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class OperationManager {

	private OperationManager() {};
	private static Object lock = new Object();
		
	public static OperationResponse processRequest(String request) {
		synchronized (lock) {
			OperationResponse response = null;
			ObjectMapper objectMapper = new ObjectMapper();	
			
			try {				
				//Deserializes the received JSON to a new instance of OperationRequest and returns a JSON object as a response					    		  
			    OperationRequest operationRequest = objectMapper.readValue(request, OperationRequest.class);			    	    		  
			    String requestedOperation = operationRequest.getOperation().toUpperCase();
			    
			    if(isOperationValid(requestedOperation)) {			    	
			    	Operation newOperation = Operation.valueOf(requestedOperation);					
					double result = newOperation.calculate(operationRequest.getValue1(), operationRequest.getValue2());										
					
					response = createResponse(result, "OK", HttpCodes.OK.statusCode());
					
				} else {					
					response = createResponse(0, "Unknown operation " + requestedOperation, HttpCodes.NOTFOUND.statusCode());				
				}
				
			} catch (JsonParseException parseException) {			
				response = createResponse(0, "Invalid request format. Valid formats: json/application", HttpCodes.BADREQUEST.statusCode());
				
				//log("Invalid JSON format.\n" + parseException.getMessage());
				
			} catch (JsonMappingException mappingException) {
				response = createResponse(0, "Unrecognized JSON elements. Recognized elements: value1; value2; operation", HttpCodes.NOTFOUND.statusCode());		
				
				//log("Unrecognized JSON elements.\n" + mappingException.getMessage());
				
			} catch (IOException ioException) {
				response = createResponse(0, "Internal error.", HttpCodes.INTERNALERROR.statusCode());
				
			}
						
			return response;					
		}			
	}
	
	private static boolean isOperationValid(String operation) {
		for (Operation op : Operation.values()) {
            if(op.toString().equals(operation)) 
            	return true; 
        }
		
		return false;
	}
	
	private static OperationResponse createResponse(double result, String message, int statusCode) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		OperationResponse response = new OperationResponse();
		response.setStatus(statusCode);
		response.setMessage(message);
		response.setResult(result);	
		response.setTime(dateFormat.format(new Date()));
				
		return response;
	}
}
