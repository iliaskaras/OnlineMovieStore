package com.karatsin.onlinemoviestore.rest.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api")
public class OrderSubmissionRestController {
	
	@Autowired
	IMovieService movieService;
	@Autowired
	IAccountService accountService;

	
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
		
		
		
		return new ModelAndView("movie_submit_order");
	}
	
	
	
	@GetMapping("/orderSubmission/title={movieTitle}/id={movieId}/submitOrder")
	public ModelAndView showSubmissionOrderForm(@PathVariable String movieTitle,
									@PathVariable int movieId,
									@RequestParam("numberOfDaysToWatch") int numberOfDaysToWatch
									) {
		
		
		
		return new ModelAndView("movie_submit_order");
	}

}
