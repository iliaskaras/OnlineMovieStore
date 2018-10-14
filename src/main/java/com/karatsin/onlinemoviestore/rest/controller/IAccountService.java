package com.karatsin.onlinemoviestore.rest.controller;

import java.util.List;

import com.karatsin.onlinemoviestore.entity.Account;

public interface IAccountService {

	
	public List<Account> getAccounts();

	public void saveAccount(Account theAccount);

	public Account getAccount(int theId);

	public void deleteAccount(int theId);
	
	
}
