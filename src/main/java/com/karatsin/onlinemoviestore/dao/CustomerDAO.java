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

	/* session factory injection */
	@Autowired
	private SessionFactory sessionFactory;
	
	/* Returns a list of our customers ordered by their last name */
	@Override
	public List<Customer> getCustomers() {
			
		Session currentSession = sessionFactory.getCurrentSession();
					
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by lastName",
												Customer.class);
			
		List<Customer> customers = theQuery.getResultList();
					
		return customers;
	}

	/* Save or Update a customer */
	@Override
	public void saveCustomer(Customer theCustomer) {

		Session currentSession = sessionFactory.getCurrentSession();
			
		currentSession.saveOrUpdate(theCustomer);
		
	}

	/* Returns a customer given the customer id */
	@Override
	public Customer getCustomerById(int theCustomerID) {
		
		Session currentSession = sessionFactory.getCurrentSession();
			
		Customer theCustomer = currentSession.get(Customer.class, theCustomerID);
			
		return theCustomer;
	}
		
	/* Returns a customer given the customer email */
	@Override
	public Customer getCustomerByEmail(String theEmail) {

		Session currentSession = sessionFactory.getCurrentSession();
			
		Query<Customer> theQuery = currentSession.createQuery("from Customer where email=:theEmail", Customer.class);
		theQuery.setParameter("theEmail", theEmail);
	
		List<Customer> customers = theQuery.getResultList();
			
		if(customers.size() == 0) return null;
			
		return customers.get(0);
	}

	/* Deletes a customer given the customerId */
	@Override
	public void deleteCustomer(int theCustomerID) {
		Session currentSession = sessionFactory.getCurrentSession();
			
		Query theQuery = 
				currentSession.createQuery("delete from Customer where id=:theCustomerID");
		theQuery.setParameter("theCustomerID", theCustomerID);
			
		theQuery.executeUpdate();		
	}
		
}
