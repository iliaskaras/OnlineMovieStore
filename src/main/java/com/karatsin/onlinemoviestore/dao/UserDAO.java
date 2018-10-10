package com.karatsin.onlinemoviestore.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import com.karatsin.onlinemoviestore.entity.User;

public class UserDAO implements IUserDAO {

	// need to inject the session factory
		@Autowired
		private SessionFactory sessionFactory;
				
		@Override
		public List<User> getUsers() {
			
			// get the current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
					
			// create a query  ... sort by last name
			Query<User> theQuery = 
					currentSession.createQuery("from User order by lastName",
												User.class);
			
			// execute query and get result list
			List<User> customers = theQuery.getResultList();
					
			// return the results		
			return customers;
		}

		@Override
		public void saveUser(User theCustomer) {

			// get current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
			
			// save/upate the customer ... finally LOL
			currentSession.saveOrUpdate(theCustomer);
			
		}

		@Override
		public User getUser(int theId) {

			// get the current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
			
			// now retrieve/read from database using the primary key
			User theCustomer = currentSession.get(User.class, theId);
			
			return theCustomer;
		}

		@Override
		public void deleteUser(int theId) {

			// get the current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
			
			// delete object with primary key
			Query theQuery = 
					currentSession.createQuery("delete from User where id=:userId");
			theQuery.setParameter("userId", theId);
			
			theQuery.executeUpdate();		
		}
		
}
