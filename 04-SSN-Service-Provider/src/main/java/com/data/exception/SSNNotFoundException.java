package com.data.exception;

public class SSNNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SSNNotFoundException() {
	}

	public SSNNotFoundException(String msg) {
		super(msg);
	}

}
