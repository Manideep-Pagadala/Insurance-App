package com.his.exception;

public class PlansNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PlansNotFoundException() {
	}

	public PlansNotFoundException(String msg) {
		super(msg);
	}

}
