package com.karatsin.onlinemoviestore.rest.controller.exception;

import org.springframework.http.ResponseEntity;

import com.karatsin.onlinemoviestore.rest.response.IErrorResponse;

public interface IRestExceptionHandler {
	
	public ResponseEntity<? extends IErrorResponse> handleException(CustomerException ex);
	public ResponseEntity<? extends IErrorResponse> handleException(PaymentMethodException ex);
	public ResponseEntity<? extends IErrorResponse> handleException(Exception ex);

}
