package com.karatsin.onlinemoviestore.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.karatsin.onlinemoviestore.dao.IAccountDAO;
import com.karatsin.onlinemoviestore.dao.ICustomerDAO;
import com.karatsin.onlinemoviestore.entity.Account;
import com.karatsin.onlinemoviestore.entity.Customer;
import com.karatsin.onlinemoviestore.rest.controller.exception.CustomerException;

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
		public Account getAccount(int theAccountId) {
			
			return accountDAO.getAccount(theAccountId);
		}

		@Override
		@Transactional
		public void deleteAccount(int theAccountId) {
			
			accountDAO.deleteAccount(theAccountId);
		}
		
		@Transactional(rollbackFor = CustomerException.class)
		public void test(int theAccountId) {
			
			accountDAO.deleteAccount(theAccountId);
		}
	
}
