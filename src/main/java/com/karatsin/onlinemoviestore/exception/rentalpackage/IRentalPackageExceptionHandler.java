package com.karatsin.onlinemoviestore.exception.rentalpackage;

import org.springframework.http.ResponseEntity;

import com.karatsin.onlinemoviestore.responses.IErrorResponse;

public interface IRentalPackageExceptionHandler {
	
	public ResponseEntity<? extends IErrorResponse> handleException(RentalPackageNotFoundException ex);
	public ResponseEntity<? extends IErrorResponse> handleException(Exception ex);

}
