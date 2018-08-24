package com.criticalsoftware.ws.operation;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import com.criticalsoftware.ws.util.HttpCode;
import com.criticalsoftware.ws.util.SystemLogger;

/**
 * Class responsible for managing operation requests and retrieving responses.
 * 
 * @author João Santos
 * @version 1.0
 */
public class OperationManager {

	/**
	 * The constructor is private to not allow instantiation of the class.
	 */
	private OperationManager() {};
	
	private static Object lock = new Object();	
	
	/**
	 * Processes an operation request and retrieves an operation response.
	 * @param request Reader with the request info.
	 * @return OperationResponse Returns an instance with the status of the request, description message, result of
	 * 		   the calculation and date of the operation.
	 */
	public static OperationResponse processOperationRequest(Reader request) {
		synchronized (lock) {
			OperationResponse response = null;						
			ObjectMapper objectMapper = new ObjectMapper();						
			Logger logger = SystemLogger.getLogger(OperationManager.class.getName());
			
			try {															
				//Deserializes the received JSON to a new instance of OperationRequest
			    OperationRequest operationRequest = objectMapper.readValue(request, OperationRequest.class);			    	    		  
			    String requestedOperation = operationRequest.getOperation().toUpperCase();
			    			    
			    if(isOperationValid(requestedOperation)) {			    	
			    	Operation newOperation = Operation.valueOf(requestedOperation);					
					double result = newOperation.calculate(operationRequest.getValue1(), operationRequest.getValue2());										
					
					response = createResponse(result, "OK", HttpCode.OK);
					
				} else {					
					response = createResponse(0, "Unknown operation " + requestedOperation, HttpCode.NOTFOUND);					
				}				
			} catch (JsonParseException e) {			
				response = createResponse(0, "Invalid request format. Valid formats: json/application", HttpCode.BADREQUEST);				
				logger.log(Level.SEVERE, e.getMessage());				
			} catch (JsonMappingException e) {
				response = createResponse(0, "Unrecognized JSON elements. Recognized elements: value1; value2; operation", HttpCode.NOTFOUND);		
				logger.log(Level.SEVERE, e.getMessage());				
			} catch (IOException e) {
				response = createResponse(0, "Internal error.", HttpCode.INTERNALERROR);
				logger.log(Level.SEVERE, e.getMessage());						
			} finally{
				for(Handler h:logger.getHandlers())
				{
				    h.close();   //must call h.close or a .LCK file will remain.
				}
			}		
			
			return response;
		}			
	}
		
	/**
	 * Checks if the specified operation is valid.
	 * @param operation String with the intended operation.
	 * @return boolean Returns true if the operation valid and false if not.
	 */
	private static boolean isOperationValid(String operation) {
		for (Operation op : Operation.values()) {
            if(op.toString().equals(operation)) 
            	return true; 
        }
		
		return false;
	}
	
	/**
	 * Creates a new instance of OperationResponse with the specified parameters and retrieves it.
	 * @param result Double value representing the result of the calculation.
	 * @param message String with the description message.
	 * @param httpCode HttpCode representing the success or failure of the operation.
	 * @return OperationResponse Retrieves the response with the specified parameters and the current date.
	 */
	private static OperationResponse createResponse(double result, String message, HttpCode httpCode) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");		
		OperationResponse response = new OperationResponse();
		
		response.setStatus(httpCode.statusCode());
		response.setMessage(message);
		response.setResult(result);	
		response.setTime(dateFormat.format(new Date()));
				
		return response;
	}
}
