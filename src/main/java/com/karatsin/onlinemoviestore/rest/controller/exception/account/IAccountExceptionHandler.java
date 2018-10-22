package com.karatsin.onlinemoviestore.rest.controller.exception.account;

import org.springframework.http.ResponseEntity;

import com.karatsin.onlinemoviestore.rest.controller.exception.customer.CustomerWithEmailExistException;
import com.karatsin.onlinemoviestore.rest.response.ErrorResponse;
import com.karatsin.onlinemoviestore.rest.response.IErrorResponse;

public interface IAccountExceptionHandler {
	
	public ResponseEntity<? extends IErrorResponse> handleException(AccountNotFoundException ex);
	public ResponseEntity<? extends IErrorResponse> handleException(AccountWithEmailExistException ex);
	public ResponseEntity<? extends IErrorResponse> handleException(InvalidAccountUsernameException ex);
	public ResponseEntity<? extends IErrorResponse> handleException(InvalidAccountPasswordException ex);
	public ResponseEntity<? extends IErrorResponse> handleException(Exception ex);
	public ResponseEntity<ErrorResponse> handleException(CustomerWithEmailExistException ex);

}
