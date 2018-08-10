package com.criticalsoftware.ws.operations;

public final class OperationManager {

	private OperationManager() {
		
	}
	
	private static boolean checkValidOperation(String operation) {
		return true;
	}
	
	private static double add(double value1, double value2) {
		return (value1 + value2);
	}
 
	private static double subtract(double value1, double value2) {
		return (value1 - value2);
	}
	
	private static double multiply(double value1, double value2) {
		return (value1 * value2);
	}
	
	private static double divide(double value1, double value2) {
		return (value1 / value2);
	}
	
	private static double average(double value1, double value2) {
		return ((value1 + value2) / 2);
	}
}
