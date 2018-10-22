package com.karatsin.onlinemoviestore.exception.account;

public class InvalidAccountPasswordException extends RuntimeException{

	public InvalidAccountPasswordException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidAccountPasswordException(String message) {
		super(message);
	}

	public InvalidAccountPasswordException(Throwable cause) {
		super(cause);
	}

	
}
