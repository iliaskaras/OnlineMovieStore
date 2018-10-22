package com.karatsin.onlinemoviestore.exception.customer;

import org.springframework.http.ResponseEntity;

import com.karatsin.onlinemoviestore.responses.IErrorResponse;

public interface ICustomerExceptionHandler {
	
	public ResponseEntity<? extends IErrorResponse> handleException(CustomerNotFoundException ex);
	public ResponseEntity<? extends IErrorResponse> handleException(CustomerWithEmailExistException ex);
	public ResponseEntity<? extends IErrorResponse> handleException(Exception ex);

}
