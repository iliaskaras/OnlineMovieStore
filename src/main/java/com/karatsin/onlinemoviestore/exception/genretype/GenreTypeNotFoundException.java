package com.karatsin.onlinemoviestore.exception.genretype;

public class GenreTypeNotFoundException extends RuntimeException{

	public GenreTypeNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public GenreTypeNotFoundException(String message) {
		super(message);
	}

	public GenreTypeNotFoundException(Throwable cause) {
		super(cause);
	}

	
}
