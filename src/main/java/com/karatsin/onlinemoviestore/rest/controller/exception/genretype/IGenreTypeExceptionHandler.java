package com.karatsin.onlinemoviestore.rest.controller.exception.genretype;

import org.springframework.http.ResponseEntity;

import com.karatsin.onlinemoviestore.rest.controller.exception.PaymentMethodException;
import com.karatsin.onlinemoviestore.rest.controller.exception.customer.CustomerWithEmailExistException;
import com.karatsin.onlinemoviestore.rest.response.ErrorResponse;
import com.karatsin.onlinemoviestore.rest.response.IErrorResponse;

public interface IGenreTypeExceptionHandler {
	
	public ResponseEntity<? extends IErrorResponse> handleException(GenreTypeNotFoundException ex);
	public ResponseEntity<? extends IErrorResponse> handleException(Exception ex);

}
