package com.karatsin.onlinemoviestore.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.karatsin.onlinemoviestore.entity.Customer;
import com.karatsin.onlinemoviestore.entity.Movie;

@Repository
public class MovieDAO implements IMovieDAO{

	/* session factory injection */
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Movie> getMovies() {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Movie> theQuery = 
				currentSession.createQuery("from Movie order by movieTitle",
												Movie.class);
			
		List<Movie> movies = theQuery.getResultList();
					
		return movies;
	}
	
	@Override
	public List<Movie> getMoviesByGenre(int theGenreTypeId) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Movie> theQuery = 
				currentSession.createQuery("from Movie where genreTypeId=:theGenreTypeId order by movieTitle",
												Movie.class);
		theQuery.setParameter("theGenreTypeId", theGenreTypeId);
		
		List<Movie> movies = theQuery.getResultList();
					
		return movies;
	}

	@Override
	public void saveMovie(Movie theMovie) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Movie getMovieById(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Movie> theQuery = 
				currentSession.createQuery("from Movie where id=:theId",
												Movie.class);
		theQuery.setParameter("theId", theId);
		
		List<Movie> movies = theQuery.getResultList();
					
		return movies.get(0);
	}

	@Override
	public Movie getMovieByTitle(String theTitle, int movieId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Movie> theQuery = currentSession.createQuery("from Movie where movieTitle=:theTitle and id=:movieId", Movie.class);
		theQuery.setParameter("theTitle", theTitle);
		theQuery.setParameter("movieId", movieId);
		
		List<Movie> movies = theQuery.getResultList();
			
		if(movies.size() == 0) return null;
			
		return movies.get(0);
	}

	@Override
	public void deleteMovie(int theId) {
		// TODO Auto-generated method stub
		
	}

}
