package com.karatsin.onlinemoviestore.rest.controller.exception.rentalpackage;

import org.springframework.http.ResponseEntity;
import com.karatsin.onlinemoviestore.rest.response.IErrorResponse;

public interface IRentalPackageExceptionHandler {
	
	public ResponseEntity<? extends IErrorResponse> handleException(RentalPackageNotFoundException ex);
	public ResponseEntity<? extends IErrorResponse> handleException(Exception ex);

}
