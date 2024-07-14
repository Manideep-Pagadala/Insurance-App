package com.his.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = CitizenNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleInvalidSSN(CitizenNotFoundException e) {
		ErrorResponse errorResponse = new ErrorResponse("Ex002", e.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception e) {
		ErrorResponse errorResponse = new ErrorResponse("Ex001", e.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
