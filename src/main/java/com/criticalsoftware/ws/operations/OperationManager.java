package com.criticalsoftware.ws.operations;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class OperationManager {

	private OperationManager() {};
	private static Object lock = new Object();
	
	@SuppressWarnings("serial")
	private static List<String> validOperations = new ArrayList<String>() {{ add("ADD");
																			 add("SUBTRACT");
																			 add("MULTIPLY");
																			 add("DIVIDE");
																			 add("AVERAGE");}};
		
	private static boolean isOperationValid(String operation) {
		return validOperations.contains(operation.toUpperCase()) ? true : false;		
	}	
	
	public static double calculate(OperationRequest request) throws InvalidParameterException {
		synchronized (lock) {
			if(isOperationValid(request.getOperation())) {
				double result = 0.0;
				
				switch (request.getOperation().toUpperCase()) {
					case "ADD": 
						result = add(request.getValue1(), request.getValue2());
						break;
					case "SUBTRACT":
						result = subtract(request.getValue1(), request.getValue2());
						break;
					case "MULTIPLY":
						result = multiply(request.getValue1(), request.getValue2());
						break;
					case "DIVIDE":
						result = divide(request.getValue1(), request.getValue2());
						break;
					case "AVERAGE":
						result = average(request.getValue1(), request.getValue2());
						break;			
				}
				
				return result;
				
			}else {
				throw new InvalidParameterException("Unrecognized operation value: " + request.getOperation() + "\n" + 
													"Recognized values: add, subtract, multiply, divide, average");
			}	
		}			
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
