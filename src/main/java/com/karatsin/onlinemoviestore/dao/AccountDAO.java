package com.karatsin.onlinemoviestore.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.karatsin.onlinemoviestore.entity.Account;
import com.karatsin.onlinemoviestore.entity.Customer;

@Repository
public class AccountDAO implements IAccountDAO {

	// need to inject the session factory
		@Autowired
		private SessionFactory sessionFactory;
				
		@Override
		public List<Account> getAccounts() {
			
			// get the current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
					
			// create a query  ... sort by username
			Query<Account> theQuery = 
					currentSession.createQuery("from Account order by username",
												Account.class);
			
			// execute query and get result list
			List<Account> accounts = theQuery.getResultList();
					
			// return the results		
			return accounts;
		}

		@Override
		public void saveAccount(Account theAccount) {

			// get current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
			
			// save/upate the Account
			currentSession.saveOrUpdate(theAccount);
			
		}

		@Override
		public Account getAccount(int theAccountId) {

			// get the current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
			
			// now retrieve/read from database using the primary key
			Account theAccount = currentSession.get(Account.class, theAccountId);
			
			return theAccount;
		}

		@Override
		public void deleteAccount(int theAccountId) {

			// get the current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
			
			// delete object with primary key
			Query theQuery = 
					currentSession.createQuery("delete from Account where id=:theAccountId");
			theQuery.setParameter("theAccountId", theAccountId);
			
			theQuery.executeUpdate();		
		}
		
}
