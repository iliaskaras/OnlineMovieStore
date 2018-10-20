package com.karatsin.onlinemoviestore.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.karatsin.onlinemoviestore.entity.Movie;

@RestController
@RequestMapping("/api")
public class OrderSubmissionRestController {
	
	@Autowired
	IMovieService movieService;
	
	@PostMapping("/orderSubmission/title={movieTitle}/id={movieId}/submitOrder")
	public ModelAndView showSubmissionOrderForm(@PathVariable String movieTitle,
									@PathVariable int movieId,
									@RequestParam("numberOfDaysToWatch") int numberOfDaysToWatch
									) {
		
		
		
		return new ModelAndView("movie_submit_order");
	}

}
