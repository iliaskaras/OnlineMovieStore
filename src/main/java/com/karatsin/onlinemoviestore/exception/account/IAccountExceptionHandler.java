package com.karatsin.onlinemoviestore.exception.account;

import org.springframework.http.ResponseEntity;

import com.karatsin.onlinemoviestore.exception.customer.CustomerWithEmailExistException;
import com.karatsin.onlinemoviestore.responses.ErrorResponse;
import com.karatsin.onlinemoviestore.responses.IErrorResponse;

public interface IAccountExceptionHandler {
	
	public ResponseEntity<? extends IErrorResponse> handleException(AccountNotFoundException ex);
	public ResponseEntity<? extends IErrorResponse> handleException(AccountWithEmailExistException ex);
	public ResponseEntity<? extends IErrorResponse> handleException(InvalidAccountUsernameException ex);
	public ResponseEntity<? extends IErrorResponse> handleException(InvalidAccountPasswordException ex);
	public ResponseEntity<? extends IErrorResponse> handleException(Exception ex);
	public ResponseEntity<ErrorResponse> handleException(CustomerWithEmailExistException ex);

}
