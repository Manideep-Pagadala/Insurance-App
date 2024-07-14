package com.his.exception;

public class MyInvalidApplicationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MyInvalidApplicationException() {
	}

	public MyInvalidApplicationException(String msg) {
		super(msg);
	}

}
