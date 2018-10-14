package com.karatsin.onlinemoviestore.rest.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.karatsin.onlinemoviestore.rest.controller.MovieRestController;
import com.karatsin.onlinemoviestore.rest.response.IErrorResponse;
import com.karatsin.onlinemoviestore.rest.response.MovieErrorResponse;

@ControllerAdvice(assignableTypes = MovieRestController.class)
public class MovieRestExceptionHandler implements IRestExceptionHandler
{

	/* Our user Exception Handler method
	 * @MovieErrorResponse : our type of the response body
	 * @UserNotFoundException : Exception type to handle / catch */
	@ExceptionHandler
	@Override
	public ResponseEntity<MovieErrorResponse> handleException(CustomerException ex){
		
		MovieErrorResponse error = new MovieErrorResponse();
		
		// Not found = 404 code error message 
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage("MovieRestExceptionHandler exception : "+ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	/* Our catch all Exception Handler method */
	@ExceptionHandler
	@Override
	public ResponseEntity<MovieErrorResponse> handleException(Exception ex){
		
		MovieErrorResponse error = new MovieErrorResponse();
		
		// Not found = 404 code error message 
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage("MovieRestExceptionHandler exception : "+ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<? extends IErrorResponse> handleException(PaymentMethodException ex) {
		// TODO Auto-generated method stub
		return null;
	}
}
