package com.criticalsoftware.ws.operations;

public enum HttpCodes {
	OK(200),
	BADREQUEST(400),
	NOTFOUND(404),
	INTERNALERROR(500);
	
	private int statusCode;
	
	HttpCodes(int statusCode){
		this.statusCode = statusCode;
	}
	
	public int statusCode() {
        return statusCode;
    }
}
