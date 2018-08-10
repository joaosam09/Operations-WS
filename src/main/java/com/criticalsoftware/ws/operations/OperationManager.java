package com.criticalsoftware.ws.operations;

public class OperationManager {

	public double add(double value1, double value2) {
		return (value1 + value2);
	}
 
	public double subtract(double value1, double value2) {
		return (value1 - value2);
	}
	
	public double multiply(double value1, double value2) {
		return (value1 * value2);
	}
	
	public double divide(double value1, double value2) {
		return (value1 / value2);
	}
	
	public double average(double value1, double value2) {
		return ((value1 + value2) / 2);
	}
}
