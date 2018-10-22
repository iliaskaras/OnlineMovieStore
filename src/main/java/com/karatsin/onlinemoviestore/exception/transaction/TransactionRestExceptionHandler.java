package com.karatsin.onlinemoviestore.exception.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.karatsin.onlinemoviestore.responses.TransactionErrorResponse;
import com.karatsin.onlinemoviestore.rest.controller.OrderSubmissionRestController;

@ControllerAdvice(assignableTypes = OrderSubmissionRestController.class)
public class TransactionRestExceptionHandler implements ITransactionExceptionHandler
{

	/* Our user Exception Handler method
	 * @MovieErrorResponse : our type of the response body
	 * @UserNotFoundException : Exception type to handle / catch */
	@ExceptionHandler
	@Override
	public ResponseEntity<TransactionErrorResponse> handleException(TransactionNotFoundException ex){
		
		TransactionErrorResponse error = new TransactionErrorResponse();
		
		// Not found = 404 code error message 
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage("TransactionRestExceptionHandler exception : "+ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	/* Our catch all Exception Handler method */
	@ExceptionHandler
	@Override
	public ResponseEntity<TransactionErrorResponse> handleException(Exception ex){
		
		TransactionErrorResponse error = new TransactionErrorResponse();
		
		// Not found = 404 code error message 
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage("MovieRestExceptionHandler exception : "+ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}


}
