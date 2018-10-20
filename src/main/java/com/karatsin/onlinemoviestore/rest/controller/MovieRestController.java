package com.karatsin.onlinemoviestore.rest.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.karatsin.onlinemoviestore.entity.Movie;
import com.karatsin.onlinemoviestore.entity.PaymentMethod;
import com.karatsin.onlinemoviestore.entity.RegistrationWrapper;
import com.karatsin.onlinemoviestore.entity.Account;
import com.karatsin.onlinemoviestore.entity.Customer;
import com.karatsin.onlinemoviestore.rest.controller.exception.customer.CustomerNotFoundException;
import com.karatsin.onlinemoviestore.rest.controller.exception.customer.CustomerWithEmailExistException;
import com.karatsin.onlinemoviestore.rest.controller.exception.movie.MovieNotFoundException;
import com.karatsin.onlinemoviestore.rest.response.ErrorResponse;

@RestController
@RequestMapping("/api")
public class MovieRestController {
	
	@Autowired
	IMovieService movieService;
	
	/* Define a PostConstruct to load the data at the bean first creation ... only once */
	@PostConstruct
	public void loadData() {	
	}
	
	@GetMapping("/movies/id=/{movieId}")
	public Movie getUsers(@PathVariable int movieId){
		
		Movie theMovie = movieService.getMovieById(movieId);
		
		return theMovie;
	}
	
	@GetMapping("/movies")
	public List<Movie> getAccounts(){
	
		return movieService.getMovies();
	}
	
	@GetMapping("/movies/title={movieTitle}/id={movieId}")
	public ModelAndView getMovieSubmitOrderForm(@PathVariable String movieTitle, @PathVariable int movieId) {
		
		Movie theMovie = movieService.getMovieByTitle(movieTitle, movieId);
		
		return new ModelAndView("movie_submit_order", "theMovie", theMovie);
	}
	
	
	
	@DeleteMapping("/movies/id=/{movieId}")
	public String deleteAccount(@PathVariable int movieId){
		
		movieService.deleteMovie(movieId);
		
		return "Movie with id: "+movieId+", deleted succesfully!";
		
	}
	
//	@PostMapping(value = "/account/registration")
//	public ModelAndView registerUserAccount
//	      (@Valid @ModelAttribute("registrationWrapper") RegistrationWrapper registrationWrapperDTO,
//	       BindingResult registrationWrapperBindingResult,
//	       WebRequest request, Errors errors, Model model){    
//
////		/** If there are errors in the registration form, return the same UI with warning errors  */
////		if(registrationWrapperBindingResult.hasErrors()) {
////          
////            return new ModelAndView("registration_form");
////        }
////		
////
////		try {
////			Customer customer = customerService.customerWithMailExist(registrationWrapperDTO.getCustomer().getEmail());
////		} catch (CustomerWithEmailExistException exception) {
////			exception.printStackTrace();
////			registrationWrapperBindingResult.rejectValue("customer.email", "errors.signup.email","Email address is already in use.");
////		
////			return new ModelAndView("registration_form");
////		} 
////		
////		PaymentMethod paymentMethod = paymentMethodService.getPaymentMethodByType(registrationWrapperDTO.getPaymentMethod().getPaymentType());
////	
////		registrationWrapperDTO.getAccount().setPaymentMethodId(paymentMethod.getId());
////		registrationWrapperDTO.getAccount().setCustomer(registrationWrapperDTO.getCustomer());
////		
////		accountService.saveAccount(registrationWrapperDTO.getAccount());
////		customerService.saveCustomer(registrationWrapperDTO.getCustomer());
////
////	    return new ModelAndView("home_logged_in");
//	   return null;
//	}
//	
	/* When the user will request /account/registration, this method will get called and
	 * the following objects : customerDTO, paymentMethodDTO and accountDTO will back 
	 * up the filling registration form. */
	@GetMapping(value = "/movies/all")
	public ModelAndView showMoviesForm(WebRequest request, Model model) {
		List<Movie> movies = movieService.getMovies();
		
		return new ModelAndView("movies_form", "moviesForm", movies);

	}	

//	@ModelAttribute("paymentMethodList")
//	public ArrayList<String> getPaymentMethodList() {
//		List<PaymentMethod> paymentMethods = paymentMethodService.getPaymentMethods();
//		ArrayList<String> paymentMethodList = new ArrayList<String>();
//
//		for(PaymentMethod paymentMethod : paymentMethods) {
//			 paymentMethodList.add(paymentMethod.getPaymentType());
//		}
//		
//	    return paymentMethodList;
//	}
//	
}
