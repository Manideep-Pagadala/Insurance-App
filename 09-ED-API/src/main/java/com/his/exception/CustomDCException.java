package com.his.exception;

public class CustomDCException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomDCException() {
	}

	public CustomDCException(String msg) {
		super(msg);
	}

}
