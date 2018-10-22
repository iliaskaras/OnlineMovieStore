package com.karatsin.onlinemoviestore.rest.controller.exception.customer;

import org.springframework.http.ResponseEntity;
import com.karatsin.onlinemoviestore.rest.response.IErrorResponse;

public interface ICustomerExceptionHandler {
	
	public ResponseEntity<? extends IErrorResponse> handleException(CustomerNotFoundException ex);
	public ResponseEntity<? extends IErrorResponse> handleException(CustomerWithEmailExistException ex);
	public ResponseEntity<? extends IErrorResponse> handleException(Exception ex);

}
