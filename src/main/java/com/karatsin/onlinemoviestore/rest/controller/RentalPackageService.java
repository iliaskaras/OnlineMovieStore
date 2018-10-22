package com.karatsin.onlinemoviestore.rest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.karatsin.onlinemoviestore.dao.IRentalPackageDAO;
import com.karatsin.onlinemoviestore.entity.RentalPackage;
import com.karatsin.onlinemoviestore.rest.controller.exception.rentalpackage.RentalPackageNotFoundException;

@Service
public class RentalPackageService implements IRentalPackageService{
	
	    /* inject the rental package dao */
		@Autowired
		private IRentalPackageDAO rentalPackageDAO;

		@Override
		@Transactional
		public RentalPackage getRentalPackageById(int theRentalPackageId) {
			RentalPackage theRentalPackage = rentalPackageDAO.getRentalPackageById(theRentalPackageId);
			
			if (theRentalPackage == null)
				throw new RentalPackageNotFoundException("RentalPackage with id :"+theRentalPackageId+", not found!"); 
			
			return theRentalPackage;
		}
		
	
}
