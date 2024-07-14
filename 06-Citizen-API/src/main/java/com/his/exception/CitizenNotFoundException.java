package com.his.exception;

public class CitizenNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CitizenNotFoundException() {
	}

	public CitizenNotFoundException(String msg) {
		super(msg);
	}

}
