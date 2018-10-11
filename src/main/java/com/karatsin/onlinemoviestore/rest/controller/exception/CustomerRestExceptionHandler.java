package com.karatsin.onlinemoviestore.rest.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.karatsin.onlinemoviestore.rest.controller.MovieRestController;
import com.karatsin.onlinemoviestore.rest.controller.CustomerRestController;
import com.karatsin.onlinemoviestore.rest.response.CustomerErrorResponse;

@ControllerAdvice(assignableTypes = CustomerRestController.class)
public class CustomerRestExceptionHandler implements IRestExceptionHandler
{

	/* Our customer Exception Handler method
	 * @CustomerErrorResponse : our type of the response body
	 * @CustomerNotFoundException : Exception type to handle / catch */
	@ExceptionHandler
	@Override
	public ResponseEntity<CustomerErrorResponse> handleException(CustomException ex){
		
		CustomerErrorResponse error = new CustomerErrorResponse();
		
		// Not found = 404 code error message 
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage("CustomerRestExceptionHandler exception : "+ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	/* Our catch all Exception Handler method */
	@ExceptionHandler
	@Override
	public ResponseEntity<CustomerErrorResponse> handleException(Exception ex){
		
		CustomerErrorResponse error = new CustomerErrorResponse();
		
		// Not found = 404 code error message 
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage("CustomerRestExceptionHandler exception : "+ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
