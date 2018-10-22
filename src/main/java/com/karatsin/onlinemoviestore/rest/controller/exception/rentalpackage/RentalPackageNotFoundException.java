package com.karatsin.onlinemoviestore.rest.controller.exception.rentalpackage;

public class RentalPackageNotFoundException extends RuntimeException{

	public RentalPackageNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public RentalPackageNotFoundException(String message) {
		super(message);
	}

	public RentalPackageNotFoundException(Throwable cause) {
		super(cause);
	}

	
}
