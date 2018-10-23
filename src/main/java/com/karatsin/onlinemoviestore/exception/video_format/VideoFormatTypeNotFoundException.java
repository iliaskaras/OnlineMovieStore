package com.karatsin.onlinemoviestore.exception.video_format;

public class VideoFormatTypeNotFoundException extends RuntimeException{

	public VideoFormatTypeNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public VideoFormatTypeNotFoundException(String message) {
		super(message);
	}

	public VideoFormatTypeNotFoundException(Throwable cause) {
		super(cause);
	}

	
}
