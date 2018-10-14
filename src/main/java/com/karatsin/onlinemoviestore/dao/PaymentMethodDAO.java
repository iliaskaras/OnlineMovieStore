package com.karatsin.onlinemoviestore.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.karatsin.onlinemoviestore.entity.PaymentMethod;

@Repository
public class PaymentMethodDAO implements IPaymentMethodDAO{

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	public List<PaymentMethod> getPaymentMethods() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query  ... sort by last name
		Query<PaymentMethod> theQuery = 
				currentSession.createQuery("from PaymentMethod order by paymentType",
											PaymentMethod.class);
		
		// execute query and get result list
		List<PaymentMethod> paymentMethods = theQuery.getResultList();
				
		// return the results		
		return paymentMethods;
	}

	@Override
	public void savePaymentMethod(PaymentMethod thePaymentMethod) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save/upate the customer ... finally LOL
		currentSession.saveOrUpdate(thePaymentMethod);
		
	}

	@Override
	public PaymentMethod getPaymentMethodById(int thePaymentMethodId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		PaymentMethod theCustomer = currentSession.get(PaymentMethod.class, thePaymentMethodId);
		
		return theCustomer;
	}
	
	@Override
	public PaymentMethod getPaymentMethodByType(String thePaymentType) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		Query<PaymentMethod> theQuery = currentSession.createQuery("from PaymentMethod where paymentType=:thePaymentType", PaymentMethod.class);
		theQuery.setParameter("thePaymentType", thePaymentType);

		// execute query and get result list
		List<PaymentMethod> paymentMethod = theQuery.getResultList();
		
		if(paymentMethod.size() == 0) return null;
		
		return paymentMethod.get(0);
	}

	@Override
	public void deletePaymentMethodById(int thePaymentMethodID) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query theQuery = 
				currentSession.createQuery("delete from PaymentMethod where id=:thePaymentMethodID");
		theQuery.setParameter("thePaymentMethodID", thePaymentMethodID);
		
		theQuery.executeUpdate();		
	}	
	
	@Override
	public void deletePaymentMethodByType(String thePaymentMethodType) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query theQuery = 
				currentSession.createQuery("delete from PaymentMethod where paymentType=:thePaymentMethodType");
		theQuery.setParameter("thePaymentMethodType", thePaymentMethodType);
		
		theQuery.executeUpdate();		
	}	
}
