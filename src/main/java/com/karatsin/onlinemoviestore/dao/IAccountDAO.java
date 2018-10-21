package com.karatsin.onlinemoviestore.dao;

import java.util.List;

import com.karatsin.onlinemoviestore.entity.Account;

public interface IAccountDAO {

	public List<Account> getAccounts();

	public void saveAccount(Account theAccount);

	public Account getAccountById(int theId);
	
	public Account getAccountByUsername(String theUsername);

	public void deleteAccount(int theId);
	
}
