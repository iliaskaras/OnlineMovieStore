package com.karatsin.onlinemoviestore.exception.genretype;

import org.springframework.http.ResponseEntity;

import com.karatsin.onlinemoviestore.responses.IErrorResponse;

public interface IGenreTypeExceptionHandler {
	
	public ResponseEntity<? extends IErrorResponse> handleException(GenreTypeNotFoundException ex);
	public ResponseEntity<? extends IErrorResponse> handleException(Exception ex);

}
