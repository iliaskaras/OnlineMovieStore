package com.karatsin.onlinemoviestore.rest.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.karatsin.onlinemoviestore.exception.CustomException;
import com.karatsin.onlinemoviestore.rest.controller.MovieRestController;
import com.karatsin.onlinemoviestore.rest.controller.UserRestController;
import com.karatsin.onlinemoviestore.rest.response.UserErrorResponse;

@ControllerAdvice(assignableTypes = UserRestController.class)
public class UserRestExceptionHandler implements IRestExceptionHandler
{

	/* Our user Exception Handler method
	 * @UserErrorResponse : our type of the response body
	 * @UserNotFoundException : Exception type to handle / catch */
	@ExceptionHandler
	@Override
	public ResponseEntity<UserErrorResponse> handleException(CustomException ex){
		
		UserErrorResponse error = new UserErrorResponse();
		
		// Not found = 404 code error message 
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage("UserRestExceptionHandler exception : "+ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	/* Our catch all Exception Handler method */
	@ExceptionHandler
	@Override
	public ResponseEntity<UserErrorResponse> handleException(Exception ex){
		
		UserErrorResponse error = new UserErrorResponse();
		
		// Not found = 404 code error message 
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage("UserRestExceptionHandler exception : "+ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
