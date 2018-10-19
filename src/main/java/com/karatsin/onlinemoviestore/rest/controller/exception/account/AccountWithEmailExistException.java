package com.karatsin.onlinemoviestore.rest.controller.exception.account;

public class AccountWithEmailExistException extends RuntimeException{

	public AccountWithEmailExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccountWithEmailExistException(String message) {
		super(message);
	}

	public AccountWithEmailExistException(Throwable cause) {
		super(cause);
	}

	
}
