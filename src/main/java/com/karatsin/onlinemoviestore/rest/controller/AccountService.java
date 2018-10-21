package com.karatsin.onlinemoviestore.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.karatsin.onlinemoviestore.dao.IAccountDAO;
import com.karatsin.onlinemoviestore.dao.ICustomerDAO;
import com.karatsin.onlinemoviestore.entity.Account;
import com.karatsin.onlinemoviestore.entity.Customer;
import com.karatsin.onlinemoviestore.rest.controller.exception.account.AccountNotFoundException;
import com.karatsin.onlinemoviestore.rest.controller.exception.account.InvalidAccountUsernameException;
import com.karatsin.onlinemoviestore.rest.controller.exception.customer.CustomerNotFoundException;

@Service
public class AccountService implements IAccountService{
	
	    /* inject the user dao */
		@Autowired
		private IAccountDAO accountDAO;
		
		@Override
		@Transactional
		public List<Account> getAccounts() {
			return accountDAO.getAccounts();
		}

		@Override
		@Transactional
		public void saveAccount(Account theCustomer) {

			accountDAO.saveAccount(theCustomer);
		}

		@Override
		@Transactional
		public Account getAccountById(int theAccountId) {
			
			Account theAccount = accountDAO.getAccountById(theAccountId);
			
			if (theAccount == null)
				throw new AccountNotFoundException("Account with id :"+theAccountId+", not found!"); 
			
			return theAccount;
		}
		
		@Override
		@Transactional
		public Account getAccountByUsername(String theUsername) {
			
			Account theAccount = accountDAO.getAccountByUsername(theUsername);
			
			if (theAccount == null)
				throw new InvalidAccountUsernameException("Account with username :"+theUsername+", not found!"); 
			
			return theAccount;
		}


		@Override
		@Transactional
		public void deleteAccount(int theAccountId) {
			
			accountDAO.deleteAccount(theAccountId);
		}
		
	
}
