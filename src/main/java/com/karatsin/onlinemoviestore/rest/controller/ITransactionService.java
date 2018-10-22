package com.karatsin.onlinemoviestore.rest.controller;

import java.util.List;

import com.karatsin.onlinemoviestore.entity.Transaction;

public interface ITransactionService {

	public List<Transaction> getTransactions();

	public void saveTransaction(Transaction theTransaction);
	
	public Transaction getTransactionsById(int theTransactionId);
	
	public List<Transaction> getTransactionsByAccount(int theAccountId);
	
	public boolean accountHasUnpaidTransactions(int theAccountId);

	public void deleteTransactionsByAccount(int theAccountId);
	
	public void deleteTransaction(int theId);
}
