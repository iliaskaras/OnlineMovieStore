package com.karatsin.onlinemoviestore.exception.account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.karatsin.onlinemoviestore.exception.customer.CustomerWithEmailExistException;
import com.karatsin.onlinemoviestore.responses.ErrorResponse;
import com.karatsin.onlinemoviestore.responses.IErrorResponse;
import com.karatsin.onlinemoviestore.rest.controller.AccountRestController;

@ControllerAdvice(assignableTypes = AccountRestController.class)
public class AccountRestExceptionHandler implements IAccountExceptionHandler
{

	/* Our customer Exception Handler method
	 * @CustomerErrorResponse : our type of the response body
	 * @CustomerNotFoundException : Exception type to handle / catch */
	@ExceptionHandler
	@Override
	public ResponseEntity<ErrorResponse> handleException(AccountNotFoundException ex){
		
		ErrorResponse error = new ErrorResponse();
		
		// Not found = 404 code error message 
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage("AccountRestExceptionHandler exception : "+ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<? extends IErrorResponse> handleException(InvalidAccountUsernameException ex) {
		ErrorResponse error = new ErrorResponse();
		
		// Not found = 404 code error message 
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage("AccountRestExceptionHandler exception : "+ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<? extends IErrorResponse> handleException(InvalidAccountPasswordException ex) {
		ErrorResponse error = new ErrorResponse();
		
		// Not found = 404 code error message 
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage("AccountRestExceptionHandler exception : "+ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	/* Our customer Exception Handler method
	 * @CustomerErrorResponse : our type of the response body
	 * @CustomerNotFoundException : Exception type to handle / catch */
	@ExceptionHandler
	@Override
	public ResponseEntity<ErrorResponse> handleException(AccountWithEmailExistException ex){
		
		ErrorResponse error = new ErrorResponse();
		
		// Not found = 400 code error message 
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage("AccountRestExceptionHandler exception : "+ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	
	/* Our catch all Exception Handler method */
	@ExceptionHandler
	@Override
	public ResponseEntity<ErrorResponse> handleException(Exception ex){
		
		ErrorResponse error = new ErrorResponse();
		
		//  Bad Request = 400 code error message 
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage("general exception : "+ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	/* Our customer Exception Handler method
	 * @CustomerErrorResponse : our type of the response body
	 * @CustomerNotFoundException : Exception type to handle / catch */
	@ExceptionHandler
	@Override
	public ResponseEntity<ErrorResponse> handleException(CustomerWithEmailExistException ex){
		
		ErrorResponse error = new ErrorResponse();
		
		// Not found = 400 code error message 
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage("CustomerRestExceptionHandler exception : "+ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}


	
}
