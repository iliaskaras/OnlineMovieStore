package com.karatsin.onlinemoviestore.rest.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.karatsin.onlinemoviestore.entity.Account;
import com.karatsin.onlinemoviestore.entity.GenreType;
import com.karatsin.onlinemoviestore.entity.Movie;
import com.karatsin.onlinemoviestore.entity.RentalPackage;
import com.karatsin.onlinemoviestore.entity.Transaction;
import com.karatsin.onlinemoviestore.entity.TransactionWrapper;
import com.karatsin.onlinemoviestore.session.UserSession;

@RestController
@RequestMapping("/api")
public class OrderSubmissionRestController {
	
	@Autowired
	IGenreTypeService genreTypeService;
	@Autowired
	IMovieService movieService;
	@Autowired
	IAccountService accountService;
	@Autowired
	ITransactionService transactionService;
	@Autowired
	IRentalPackageService rentalPackageService;
	
	/* Creation of order post form method, calculates the end date given the number of days to watch in transaction,
	 * and saves the transaction in its initial unpaid form. */
	@PostMapping("/orderSubmission/title={movieTitle}/id={movieId}/submitOrder")
	public ModelAndView createOrderForm(
									@PathVariable String movieTitle,
									@PathVariable int movieId,
									@RequestParam("numberOfDaysToWatch") int numberOfDaysToWatch,
									HttpSession session, Model model) throws ParseException {
		if(numberOfDaysToWatch==0) {
			return getMoviesPage(model);
		}
		
		int accountId = UserSession.getCustomerId(session);
		
		Account loggedInAccount = accountService.getAccountById(accountId);
		Movie movieToRental = movieService.getMovieById(movieId);

		Calendar calendar = Calendar.getInstance();
		Date rentalDateTime = calendar.getTime();
		Date rentalEndDateTime = calculateRentalEndDate(rentalDateTime,numberOfDaysToWatch);

		Transaction transaction = new Transaction.TransactionBuilder()
				.setTransactionId(0).setAccountId(loggedInAccount.getId()).setPaid(0)
				.setMovieId(movieToRental.getId()).setTransactionDate(rentalDateTime)
				.setRentalEndDate(rentalEndDateTime).setTransactionAmount(0f).setTransactionComment("non paid")
				.setNumberOfOrderDays(numberOfDaysToWatch).build();

		transactionService.saveTransaction(transaction);
	
		return getMoviesPage(model);
	}
	
	/* Updates the given transaction to paid and calculates the total amount. Loads the same page with the updated transaction loaded */	
	@GetMapping("/orderSubmission/payorder/transactionId={transactionId}")
	public ModelAndView payTransaction(@PathVariable int transactionId, Model model, HttpSession session) {
		Transaction theTransaction = transactionService.getTransactionsById(transactionId);
		if(theTransaction.getPaid()==0) {
			float totalTransactionAmount = calculateTransactionTotalAmount(theTransaction,session);
			theTransaction.setPaid(1);
			theTransaction.setTransactionAmount(totalTransactionAmount);
			theTransaction.setTransactionComment("paid");
			transactionService.saveTransaction(theTransaction);
			return loadCustomerTransactionsPage(model,session);
		} else {
			return loadCustomerTransactionsPage(model,session);
		}
	}

	/* 
	 * Calculates the transaction amount based on the movie's rental package 
	 * 
	 * Rental Package Types : normal (1), middle (2), expensive (3)
	 * normal : first 3 days = 1 , other days = 0.5
	 * middle : first 3 days = 1.2 , other days = 0.7
	 * expensive : : first 3 days = 1.4 , other days = 0.9
	 * */
	private float calculateTransactionTotalAmount(Transaction theTransaction, HttpSession session) {
		int accountId = UserSession.getCustomerId(session);
		
		Account loggedInAccount = accountService.getAccountById(accountId);
		Movie movieToRental = movieService.getMovieById(theTransaction.getMovieId());
		RentalPackage rentalPackage = rentalPackageService.getRentalPackageById(movieToRental.getRentalPackageId());
		
		int totalDaysWatched = theTransaction.getRentalEndDate().getDay()-theTransaction.getTransactionDate().getDay();
		float totalTransactionAmount = 0f;
		for(int i=0; i < totalDaysWatched; i++) {
			if(i<3)
				totalTransactionAmount+=rentalPackage.getRentalEconomicPrice();
			else
				totalTransactionAmount+=rentalPackage.getAdditionalPrice();
		}
		
		return totalTransactionAmount;
	}
	
	/* loads the transaction history of the logged in customer. 
	 * The customer can pay the transactions from that page,
	 * and if the transaction is paid can click Play button and watch it. */
	private ModelAndView loadCustomerTransactionsPage(Model model, HttpSession session) {
		int accountId = UserSession.getCustomerId(session);
		
		List<Transaction> transactions = transactionService.getTransactionsByAccount(accountId);
		
		List<TransactionWrapper> transactionWrapper = new ArrayList<>();
		
		Movie movie = new Movie();
		for(int i=0; i< transactions.size(); i++) {
			movie = movieService.getMovieById(transactions.get(i).getMovieId());	
			transactionWrapper.add(new TransactionWrapper(transactions.get(i),movie));
		}

	    model.addAttribute("transactionWrapper", transactionWrapper);
	    
	    return new ModelAndView("customer_transactions", (Map<String, ?>) model);
	}

	/* Calculates the end date based on the number of days of transaction. */
	@SuppressWarnings("deprecation")
	private Date calculateRentalEndDate(Date rentalDateTime, int numberOfOrderDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, numberOfOrderDays);
		Date rentalEndDateTime = calendar.getTime();
		return rentalEndDateTime;
	}
	
	/* adding attributes to our model and return the movies_form */
	private ModelAndView getMoviesPage(Model model) {
		List<Movie> movies = movieService.getMovies();
		List<GenreType> genreTypes = genreTypeService.getGenreTypes();
		GenreType genreType = new GenreType();
		
		ArrayList<String> genreTypesList = new ArrayList<String>();

		for(GenreType currentGenreType : genreTypes) {
			genreTypesList.add(currentGenreType.getGenreType());
		}
		
		model.addAttribute("movies", movies);
		model.addAttribute("genreType", genreType);
	    model.addAttribute("genreTypesList", genreTypesList);
		return new ModelAndView("movies_form", (Map<String, ?>) model);
		
	}


}
