package com.karatsin.onlinemoviestore.rest.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.karatsin.onlinemoviestore.entity.Movie;
import com.karatsin.onlinemoviestore.rest.services.IGenreTypeService;
import com.karatsin.onlinemoviestore.rest.services.IMovieService;
import com.karatsin.onlinemoviestore.entity.GenreType;

@RestController
@RequestMapping("/api")
public class MovieRestController {
	
	@Autowired
	IMovieService movieService;
	@Autowired
	IGenreTypeService genreTypeService;
	

	/* loading of the movies form, that the user can filter by genres */
	@GetMapping(value = "/movies/all")
	public ModelAndView showMoviesForm(WebRequest request, Model model) {
		List<Movie> movies = movieService.getMovies();
		GenreType genreType = new GenreType();

		model.addAttribute("movies", movies);
	    model.addAttribute("genreType", genreType);
	    return new ModelAndView("movies_form", (Map<String, ?>) model);
	    
	}	
	
	/* filtering of movies by genre type */
	@GetMapping(value = "/movies/all/genreType/")
	public ModelAndView showMoviesByGenreType(@Valid @ModelAttribute("genreType") GenreType genreTypeDTO, WebRequest request, Model model) {
		GenreType genreType = genreTypeService.getGenreTypeByType(genreTypeDTO.getGenreType());
		List<Movie> movies = movieService.getMoviesByGenreType(genreType.getGenreTypeId());
		
		model.addAttribute("movies", movies);
	    model.addAttribute("genreType", genreType);
	    return new ModelAndView("movies_form", (Map<String, ?>) model);

	}	
	
	/* loading of genre types list for the form's select drop down */
	@ModelAttribute("genreTypesList")
	public ArrayList<String> getGenreTypesList() {
		List<GenreType> genreTypes = genreTypeService.getGenreTypes();
		ArrayList<String> genreTypesList = new ArrayList<String>();

		for(GenreType genreType : genreTypes) {
			genreTypesList.add(genreType.getGenreType());
		}
		
	    return genreTypesList;
	}
	
	/* Get movie by a given movie id */
	@GetMapping("/movies/id=/{movieId}")
	public Movie getMovies(@PathVariable int movieId){
		
		Movie theMovie = movieService.getMovieById(movieId);
		
		return theMovie;
	}
	
	/* Get all movies */
	@GetMapping("/movies")
	public List<Movie> getMovies(){
	
		return movieService.getMovies();
	}
	
	/* Get the form that the user uses to submit an order for that movie */
	@GetMapping("/movies/title={movieTitle}/id={movieId}")
	public ModelAndView getMovieSubmitOrderForm(@PathVariable String movieTitle, @PathVariable int movieId) {
		
		Movie theMovie = movieService.getMovieByTitle(movieTitle, movieId);
		
		return new ModelAndView("movie_submit_order", "theMovie", theMovie);
	}
	
	@DeleteMapping("/movies/delete/id=/{movieId}")
	public String deleteMovie(@PathVariable int movieId){
		
		movieService.deleteMovie(movieId);
		
		return "Movie with id: "+movieId+", deleted succesfully!";
		
	}
	
	/* "Mock" form for the user to watch a movie from a paid order.
	 * If the user havent paid the transaction, then will be 
	 * redirected to the home page */
	@GetMapping("/movie/watch/movieId={movieId}/{paid}")
	public ModelAndView playMovie(@PathVariable int movieId, @PathVariable String paid) {
		
		if(paid.equals("true")) {
			Movie theMovie = movieService.getMovieById(movieId);
			return new ModelAndView("watch_movie_form", "theMovie", theMovie);
		} else {
			return new ModelAndView("home_logged_in");
		}
		
	}
	
	
	
}
