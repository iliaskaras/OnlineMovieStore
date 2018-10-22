package com.karatsin.onlinemoviestore.exception.account;

public class InvalidAccountUsernameException extends RuntimeException{

	public InvalidAccountUsernameException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidAccountUsernameException(String message) {
		super(message);
	}

	public InvalidAccountUsernameException(Throwable cause) {
		super(cause);
	}

	
}
