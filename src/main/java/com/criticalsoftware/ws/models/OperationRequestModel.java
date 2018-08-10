package com.criticalsoftware.ws.models;

public class OperationRequestModel {
	private double value1;
	private double value2;
	private String operation;
	
	public double getValue1() {
		return value1;
	}
	
	public void setValue1(double value) {
		this.value1 = value;
	}
	
	public double getValue2() {
		return value2;
	}
	
	public void setValue2(double value) {
		this.value2 = value;
	}
	
	public String getOperation() {
		return operation;
	}
	
	public void setOperation(String operation) {
		this.operation = operation;
	}		
}
