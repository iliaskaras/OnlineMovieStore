package com.karatsin.onlinemoviestore.rest.controller.exception.movie;

import org.springframework.http.ResponseEntity;

import com.karatsin.onlinemoviestore.rest.controller.exception.PaymentMethodException;
import com.karatsin.onlinemoviestore.rest.response.IErrorResponse;

public interface IMovieExceptionHandler {
	
	public ResponseEntity<? extends IErrorResponse> handleException(MovieNotFoundException ex);
	public ResponseEntity<? extends IErrorResponse> handleException(Exception ex);

}
