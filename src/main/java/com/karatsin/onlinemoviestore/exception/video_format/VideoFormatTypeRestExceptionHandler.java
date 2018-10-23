package com.karatsin.onlinemoviestore.exception.video_format;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.karatsin.onlinemoviestore.responses.TransactionErrorResponse;
import com.karatsin.onlinemoviestore.responses.VideoFormatErrorResponse;
import com.karatsin.onlinemoviestore.rest.controller.MovieRestController;
import com.karatsin.onlinemoviestore.rest.controller.OrderSubmissionRestController;

@ControllerAdvice(assignableTypes = MovieRestController.class)
public class VideoFormatTypeRestExceptionHandler implements IVideoFormatTypeExceptionHandler
{

	/* Our user Exception Handler method
	 * @MovieErrorResponse : our type of the response body
	 * @UserNotFoundException : Exception type to handle / catch */
	@ExceptionHandler
	@Override
	public ResponseEntity<VideoFormatErrorResponse> handleException(VideoFormatTypeNotFoundException ex){
		
		VideoFormatErrorResponse error = new VideoFormatErrorResponse();
		
		// Not found = 404 code error message 
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage("VideoFormatTypeRestExceptionHandler exception : "+ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	/* Our catch all Exception Handler method */
	@ExceptionHandler
	@Override
	public ResponseEntity<VideoFormatErrorResponse> handleException(Exception ex){
		
		VideoFormatErrorResponse error = new VideoFormatErrorResponse();
		
		// Not found = 404 code error message 
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage("VideoFormatTypeRestExceptionHandler exception : "+ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}


}
