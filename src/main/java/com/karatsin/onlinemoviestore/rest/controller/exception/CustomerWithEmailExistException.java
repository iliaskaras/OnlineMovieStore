package com.karatsin.onlinemoviestore.rest.controller.exception;

public class CustomerWithEmailExistException extends RuntimeException{

	public CustomerWithEmailExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomerWithEmailExistException(String message) {
		super(message);
	}

	public CustomerWithEmailExistException(Throwable cause) {
		super(cause);
	}

	
}
