package com.karatsin.onlinemoviestore.dao;

import java.util.List;

import com.karatsin.onlinemoviestore.entity.Transaction;

public interface ITransactionDAO {

	public List<Transaction> getTransactions();
	
	public Transaction getTransactionsById(int theTransactionId);

	public void saveTransaction(Transaction theTransaction);
	
	public List<Transaction> getTransactionsByAccount(int theAccountId);
	
	public List<Transaction> getUnpaidTransactions(int theAccountId);

	public void deleteTransactionsByAccount(int theAccountId);
	
	public void deleteTransaction(int theId);
}
