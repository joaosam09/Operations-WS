package com.criticalsoftware.ws.util;

/**
 * Enumeration with http codes description and integer value.
 * 
 * @author João Santos
 * @version 1.0
 */
public enum HttpCode {
	OK(200),
	BADREQUEST(400),
	NOTFOUND(404),
	INTERNALERROR(500);
	
	private int statusCode;
	
	HttpCode(int statusCode){
		this.statusCode = statusCode;
	}
	
	public int statusCode() {
        return statusCode;
    }
}