package com.karatsin.onlinemoviestore.exception.video_format;

import org.springframework.http.ResponseEntity;

import com.karatsin.onlinemoviestore.responses.IErrorResponse;

public interface IVideoFormatTypeExceptionHandler {
	
	public ResponseEntity<? extends IErrorResponse> handleException(VideoFormatTypeNotFoundException ex);
	public ResponseEntity<? extends IErrorResponse> handleException(Exception ex);

}
