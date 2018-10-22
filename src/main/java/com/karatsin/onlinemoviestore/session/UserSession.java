package com.karatsin.onlinemoviestore.session;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


public class UserSession {
	
	private static Logger logger = Logger.getLogger(UserSession.class.getName());
	
	public static int getCustomerId(HttpSession session) throws IllegalStateException{
		int accountId = 0;
		
		try {
			accountId = (int) session.getAttribute("accountLoggedInId");
		} catch (IllegalStateException ex) {
			ex.printStackTrace();
			logger.error("Not any account logged in for the current session!");
		}
		
		return accountId;
		
	}

}
