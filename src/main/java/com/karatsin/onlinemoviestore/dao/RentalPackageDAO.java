package com.karatsin.onlinemoviestore.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.karatsin.onlinemoviestore.entity.RentalPackage;


@Repository
public class RentalPackageDAO implements IRentalPackageDAO {

	/* session factory injection */
	@Autowired
	private SessionFactory sessionFactory;

	/* Returns a rental package given the rental package id */
	@Override
	public RentalPackage getRentalPackageById(int theRentalPackageID) {
		
		Session currentSession = sessionFactory.getCurrentSession();
			
		RentalPackage theRentalPackage = currentSession.get(RentalPackage.class, theRentalPackageID);
			
		return theRentalPackage;
	}
	
}
