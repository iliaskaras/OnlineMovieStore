package com.karatsin.onlinemoviestore.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.karatsin.onlinemoviestore.entity.Customer;

@Repository
public class CustomerDAO implements ICustomerDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
				
	@Override
	public List<Customer> getCustomers() {
			
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
					
		// create a query  ... sort by last name
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by lastName",
												Customer.class);
			
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
					
		// return the results		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
			
		// save/upate the customer ... finally LOL
		currentSession.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomerById(int theCustomerID) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
			
		// now retrieve/read from database using the primary key
		Customer theCustomer = currentSession.get(Customer.class, theCustomerID);
			
		return theCustomer;
	}
		
	@Override
	public Customer getCustomerByEmail(String theEmail) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
			
		// now retrieve/read from database using the primary key
		Query<Customer> theQuery = currentSession.createQuery("from Customer where email=:theEmail", Customer.class);
		theQuery.setParameter("theEmail", theEmail);

		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
			
		if(customers.size() == 0) return null;
			
		return customers.get(0);
	}

	@Override
	public void deleteCustomer(int theCustomerID) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
			
		// delete object with primary key
		Query theQuery = 
				currentSession.createQuery("delete from Customer where id=:theCustomerID");
		theQuery.setParameter("theCustomerID", theCustomerID);
			
		theQuery.executeUpdate();		
	}
		
}
