package com.karatsin.onlinemoviestore.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.karatsin.onlinemoviestore.entity.Transaction;

@Repository
public class TransactionDAO implements ITransactionDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Transaction> getTransactions() {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query  ... sort by username
		Query<Transaction> theQuery = 
				currentSession.createQuery("from Transaction order by transactionId",
									Transaction.class);
		
		// execute query and get result list
		List<Transaction> transactions = theQuery.getResultList();
				
		// return the results		
		return transactions;
	}

	@Override
	public void saveTransaction(Transaction theTransaction) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save/upate the Account
		currentSession.saveOrUpdate(theTransaction);
		
	}

	@Override
	public List<Transaction> getTransactionsByAccount(int theAccountId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Transaction> theQuery = currentSession.createQuery("from Transaction where accountId=:theAccountId", Transaction.class);
		theQuery.setParameter("theAccountId", theAccountId);
	
		List<Transaction> transactions = theQuery.getResultList();
			
		if(transactions.size() == 0) return null;
			
		return transactions;
	}
	
	@Override
	public List<Transaction> getUnpaidTransactions(int theAccountId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Transaction> theQuery = currentSession.createQuery("from Transaction where accountId=:theAccountId and paid=:unpaid", Transaction.class);
		theQuery.setParameter("theAccountId", theAccountId);
		theQuery.setParameter("unpaid", 0);
		
		List<Transaction> transactions = theQuery.getResultList();
			
		if(transactions.size() == 0) return null;
			
		return transactions;
	}

	@Override
	public void deleteTransactionsByAccount(int theAccountId) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query theQuery = 
				currentSession.createQuery("delete from Transaction where accountId=:theAccountId");
		theQuery.setParameter("theAccountId", theAccountId);
		
		theQuery.executeUpdate();	
		
	}
	
	@Override
	public void deleteTransaction(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query theQuery = 
				currentSession.createQuery("delete from Transaction where transactionId=:theId");
		theQuery.setParameter("theId", theId);
		
		theQuery.executeUpdate();		
	}

	@Override
	public Transaction getTransactionsById(int theTransactionId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Transaction> theQuery = currentSession.createQuery("from Transaction where transactionId=:theTransactionId", Transaction.class);
		theQuery.setParameter("theTransactionId", theTransactionId);
	
		List<Transaction> transactions = theQuery.getResultList();
			
		if(transactions.size() == 0) return null;
			
		return transactions.get(0);
	}

	
	

}
