package com.criticalsoftware.ws.operations;

import java.util.Date;

public class OperationValidResponse {
	private double result;
	private Date time;
	
	public double getResult() {
		return result;
	}
	
	public void setResult(double result) {
		this.result = result;
	}
	
	public Date getTime() {
		return time;
	}
	
	public void setTime(Date date) {
		this.time = date;
	}
}
