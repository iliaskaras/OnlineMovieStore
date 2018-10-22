package com.karatsin.onlinemoviestore.exception.movie;

import org.springframework.http.ResponseEntity;

import com.karatsin.onlinemoviestore.responses.IErrorResponse;

public interface IMovieExceptionHandler {
	
	public ResponseEntity<? extends IErrorResponse> handleException(MovieNotFoundException ex);
	public ResponseEntity<? extends IErrorResponse> handleException(Exception ex);

}
