package com.criticalsoftware.ws.operation;

/**
 * Class representing an operation response.
 * Contains status code, description message, result of the operation and date of the operation.
 *  
 * @author João Santos
 * @version 1.0
 */
public class OperationResponse {	
	private int statusCode;
	private String message;
	private double result;	
	private String time;
		
	public int getStatus() {
		return statusCode;
	}
	
	public void setStatus(int status) {
		this.statusCode = status;
	}
	
	public double getResult() {
		return result;
	}
	
	public void setResult(double result) {
		this.result = result;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String date) {
		this.time = date;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
