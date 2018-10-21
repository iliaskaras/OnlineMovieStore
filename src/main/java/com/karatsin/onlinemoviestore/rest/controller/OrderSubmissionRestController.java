package com.karatsin.onlinemoviestore.rest.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.karatsin.onlinemoviestore.entity.Account;
import com.karatsin.onlinemoviestore.entity.Movie;
import com.karatsin.onlinemoviestore.entity.Transaction;

@RestController
@RequestMapping("/api")
public class OrderSubmissionRestController {
	
	@Autowired
	IMovieService movieService;
	@Autowired
	IAccountService accountService;
	@Autowired
	ITransactionService transactionService;
	
	@PostMapping("/orderSubmission/title={movieTitle}/id={movieId}/submitOrder")
	public ModelAndView createOrderForm(@PathVariable String movieTitle,
									@PathVariable int movieId,
									@RequestParam("numberOfDaysToWatch") int numberOfDaysToWatch,
									HttpSession session) {
		int accountId;
		
		try {
			accountId = (int) session.getAttribute("accountLoggedInId");
		} catch (IllegalStateException ex) {
			ex.printStackTrace();
			return new ModelAndView("login_form");
		}
		
		Account loggedInAccount = accountService.getAccountById(accountId);
		Movie movieToRental = movieService.getMovieById(movieId);

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		Transaction transaction = new Transaction(0,loggedInAccount.getId(),0,date,date,0f,"");
		
		transactionService.saveTransaction(transaction);
		
		return new ModelAndView("order_confirmation_form");
	}
	
//	@GetMapping("/orderConfirmation/title={movieTitle}/id={movieId}/submitOrder")
//	public ModelAndView orderConfirmationForm(@PathVariable String movieTitle,
//									@PathVariable int movieId,
//									@RequestParam("numberOfDaysToWatch") int numberOfDaysToWatch,
//									HttpSession session) {
//
//		Account loggedInAccount = accountService.getAccountById(accountId);
//		Movie movieToRental = movieService.getMovieById(movieId);
//		
//		
//		
//		return new ModelAndView("order_confirmation_form");
//	}
//	
//	

	
	
	@GetMapping("/orderSubmission/title={movieTitle}/id={movieId}/submitOrder")
	public ModelAndView showSubmissionOrderForm(@PathVariable String movieTitle,
									@PathVariable int movieId,
									@RequestParam("numberOfDaysToWatch") int numberOfDaysToWatch
									) {
		
		
		
		return new ModelAndView("movie_submit_order");
	}

}
