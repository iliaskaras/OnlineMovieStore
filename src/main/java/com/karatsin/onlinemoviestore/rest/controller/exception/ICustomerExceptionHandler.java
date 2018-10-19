package com.karatsin.onlinemoviestore.rest.controller.exception;

import org.springframework.http.ResponseEntity;

import com.karatsin.onlinemoviestore.rest.response.IErrorResponse;

public interface ICustomerExceptionHandler {
	
	public ResponseEntity<? extends IErrorResponse> handleException(CustomerNotFoundException ex);
	public ResponseEntity<? extends IErrorResponse> handleException(CustomerWithEmailExistException ex);
	public ResponseEntity<? extends IErrorResponse> handleException(PaymentMethodException ex);
	public ResponseEntity<? extends IErrorResponse> handleException(Exception ex);

}
