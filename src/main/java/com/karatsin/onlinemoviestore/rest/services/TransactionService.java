package com.karatsin.onlinemoviestore.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.karatsin.onlinemoviestore.dao.ITransactionDAO;
import com.karatsin.onlinemoviestore.entity.Account;
import com.karatsin.onlinemoviestore.entity.Transaction;
import com.karatsin.onlinemoviestore.exception.account.AccountNotFoundException;
import com.karatsin.onlinemoviestore.exception.transaction.TransactionNotFoundException;

@Repository
public class TransactionService implements ITransactionService {

	@Autowired
	private ITransactionDAO transactionDAO;
	
	@Override
	@Transactional
	public List<Transaction> getTransactions() {
		return transactionDAO.getTransactions();
	}

	@Override
	@Transactional
	public void saveTransaction(Transaction theTransaction) {
		transactionDAO.saveTransaction(theTransaction);		
	}

	@Override
	@Transactional
	public List<Transaction> getTransactionsByAccount(int theAccountId) {
		List<Transaction> theTransactions = transactionDAO.getTransactionsByAccount(theAccountId);
		
		if (theTransactions==null || theTransactions.size()==0)
			throw new TransactionNotFoundException("Account with id :"+theAccountId+", doesn't have any transactions!"); 
		
		return theTransactions;
	}
	
	@Override
	@Transactional
	public boolean accountHasUnpaidTransactions(int theAccountId) {
		List<Transaction> theTransactions = transactionDAO.getUnpaidTransactions(theAccountId);
		
		if(theTransactions==null)
			return false;
		else
			return true;
	}


	@Override
	@Transactional
	public void deleteTransactionsByAccount(int theAccountId) {
		transactionDAO.deleteTransactionsByAccount(theAccountId);		
	}

	@Override
	@Transactional
	public void deleteTransaction(int theId) {
		transactionDAO.deleteTransaction(theId);
	}

	@Override
	@Transactional
	public Transaction getTransactionsById(int theTransactionId) {
		return transactionDAO.getTransactionsById(theTransactionId);
	}

}
