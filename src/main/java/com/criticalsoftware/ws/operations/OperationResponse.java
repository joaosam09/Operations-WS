package com.criticalsoftware.ws.operations;

public class OperationResponse {
	private int status;
	private String message;
	private double result;	
	private String time;
		
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
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
