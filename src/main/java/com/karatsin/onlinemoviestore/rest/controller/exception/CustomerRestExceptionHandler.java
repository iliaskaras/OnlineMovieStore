package com.karatsin.onlinemoviestore.rest.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.karatsin.onlinemoviestore.rest.controller.MovieRestController;
import com.karatsin.onlinemoviestore.rest.controller.AccountRegistrationRestController;
import com.karatsin.onlinemoviestore.rest.controller.CustomerRestController;
import com.karatsin.onlinemoviestore.rest.response.CustomerErrorResponse;
import com.karatsin.onlinemoviestore.rest.response.IErrorResponse;
import com.karatsin.onlinemoviestore.rest.response.PaymentMethodErrorResponse;

@ControllerAdvice(assignableTypes = CustomerRestController.class)
public class CustomerRestExceptionHandler implements ICustomerExceptionHandler
{

	/* Our customer Exception Handler method
	 * @CustomerErrorResponse : our type of the response body
	 * @CustomerNotFoundException : Exception type to handle / catch */
	@ExceptionHandler
	@Override
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException ex){
		
		CustomerErrorResponse error = new CustomerErrorResponse();
		
		// Not found = 404 code error message 
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage("CustomerRestExceptionHandler exception : "+ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	/* Our customer Exception Handler method
	 * @CustomerErrorResponse : our type of the response body
	 * @CustomerNotFoundException : Exception type to handle / catch */
	@ExceptionHandler
	@Override
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerWithEmailExistException ex){
		
		CustomerErrorResponse error = new CustomerErrorResponse();
		
		// Not found = 400 code error message 
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage("CustomerRestExceptionHandler exception : "+ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	
	/* Our catch all Exception Handler method */
	@ExceptionHandler
	@Override
	public ResponseEntity<CustomerErrorResponse> handleException(Exception ex){
		
		CustomerErrorResponse error = new CustomerErrorResponse();
		
		//  Bad Request = 400 code error message 
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage("general exception : "+ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<? extends IErrorResponse> handleException(PaymentMethodException ex) {
		// TODO Auto-generated method stub
		return null;
	}
}
