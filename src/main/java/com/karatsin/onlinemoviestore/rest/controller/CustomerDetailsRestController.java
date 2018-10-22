package com.karatsin.onlinemoviestore.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import com.karatsin.onlinemoviestore.entity.Movie;
import com.karatsin.onlinemoviestore.entity.Transaction;
import com.karatsin.onlinemoviestore.entity.TransactionWrapper;
import com.karatsin.onlinemoviestore.exception.transaction.TransactionNotFoundException;
import com.karatsin.onlinemoviestore.rest.services.IMovieService;
import com.karatsin.onlinemoviestore.rest.services.ITransactionService;
import com.karatsin.onlinemoviestore.session.UserSession;

@RestController
@RequestMapping("/api")
public class CustomerDetailsRestController {
	
	@Autowired
	IMovieService movieService;
	@Autowired
	ITransactionService transactionService;

	/* Loads the Order history form to the logged in customer. 
	 * 
	 * Shows the transaction details, and if the customer has paid the transaction will be able to Play the movie
	 * otherwise will reload the same page. */
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/customer/details/orders/history")
	public ModelAndView showOrderHistoryForm(WebRequest request, Model model, HttpSession session) {
		int accountId = UserSession.getCustomerId(session);
		List<Transaction> transactions;
		
		try {
			transactions = transactionService.getTransactionsByAccount(accountId);
		} catch(TransactionNotFoundException ex) {
			ex.printStackTrace();
			return new ModelAndView("home_logged_in");
		}
		
		List<TransactionWrapper> transactionWrapper = new ArrayList<>();
		
		Movie movie = new Movie();
		for(int i=0; i< transactions.size(); i++) {
			movie = movieService.getMovieById(transactions.get(i).getMovieId());	
			transactionWrapper.add(new TransactionWrapper(transactions.get(i),movie));
		}

	    model.addAttribute("transactionWrapper", transactionWrapper);
	    
	    return new ModelAndView("customer_transactions", (Map<String, ?>) model);

	}	
	
	@PostMapping("/customer/details/orders/history")
	public ModelAndView postOrderHistoryForm(HttpSession session) {
		
		try {
			UserSession.getCustomerId(session);
		} catch (IllegalStateException ex) {
			return new ModelAndView("login_form");
		}

		return new ModelAndView("movies_form");
	}

}
