package com.karatsin.onlinemoviestore.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.karatsin.onlinemoviestore.entity.GenreType;

@Repository
public class GenreTypeDAO implements IGenreTypeDAO {

	/* session factory injection */
	@Autowired
	private SessionFactory sessionFactory;
	
	/* Returns a list of our customers ordered by their last name */
	@Override
	public List<GenreType> getGenreTypes() {
			
		Session currentSession = sessionFactory.getCurrentSession();
					
		Query<GenreType> theQuery = 
				currentSession.createQuery("from GenreType order by genreTypeId",
												GenreType.class);
			
		List<GenreType> genreTypes = theQuery.getResultList();
					
		return genreTypes;
	}

	/* Save or Update a customer */
	@Override
	public void saveGenreType(GenreType theGenreType) {

		Session currentSession = sessionFactory.getCurrentSession();
			
		currentSession.saveOrUpdate(theGenreType);
		
	}

	/* Returns a customer given the customer id */
	@Override
	public GenreType getGenreTypeById(int theGenreTypeID) {
		
		Session currentSession = sessionFactory.getCurrentSession();
			
		GenreType theGenreType = currentSession.get(GenreType.class, theGenreTypeID);
			
		return theGenreType;
	}
		
	/* Returns a customer given the customer email */
	@Override
	public GenreType getGenreTypeByType(String theGenreType) {

		Session currentSession = sessionFactory.getCurrentSession();
			
		Query<GenreType> theQuery = currentSession.createQuery("from GenreType where genreType=:theGenreType", GenreType.class);
		theQuery.setParameter("theGenreType", theGenreType);
	
		List<GenreType> genreTypes = theQuery.getResultList();
			
		if(genreTypes.size() == 0) return null;
			
		return genreTypes.get(0);
	}

	/* Deletes a customer given the customerId */
	@Override
	public void deleteGenreType(int theGenreTypeID) {
		Session currentSession = sessionFactory.getCurrentSession();
			
		Query theQuery = 
				currentSession.createQuery("delete from GenreType where genreTypeId=:theGenreTypeID");
		theQuery.setParameter("theGenreTypeID", theGenreTypeID);
			
		theQuery.executeUpdate();		
	}
		
}
