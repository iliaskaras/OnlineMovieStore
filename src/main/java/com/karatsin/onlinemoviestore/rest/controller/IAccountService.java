package com.karatsin.onlinemoviestore.rest.controller;

import java.util.List;

import com.karatsin.onlinemoviestore.entity.Account;

public interface IAccountService {

	
	public List<Account> getAccounts();

	public void saveAccount(Account theAccount);

	public Account getAccountById(int theId);
	
	public Account getAccountByUsername(String theUsername);

	public void deleteAccount(int theId);
	
	
}
