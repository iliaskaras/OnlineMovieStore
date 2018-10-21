package com.karatsin.onlinemoviestore.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.karatsin.onlinemoviestore.dao.IGenreTypeDAO;
import com.karatsin.onlinemoviestore.entity.GenreType;
import com.karatsin.onlinemoviestore.rest.controller.exception.account.InvalidAccountUsernameException;
import com.karatsin.onlinemoviestore.rest.controller.exception.genretype.GenreTypeNotFoundException;

@Service
public class GenreTypeService implements IGenreTypeService{

	/* inject the user dao */
	@Autowired
	private IGenreTypeDAO genreTypeDAO;
		
	@Transactional
	@Override
	public List<GenreType> getGenreTypes() {
		return genreTypeDAO.getGenreTypes();
	}

	@Transactional
	@Override
	public void saveGenreType(GenreType theGenreType) {
		genreTypeDAO.saveGenreType(theGenreType);
	}

	@Transactional
	@Override
	public GenreType getGenreTypeById(int theId) {
		GenreType theGenreType = genreTypeDAO.getGenreTypeById(theId);
		
		if (theGenreType == null)
			throw new GenreTypeNotFoundException("GenreType with id :"+theId+", not found!"); 
		
		return theGenreType;
	}

	@Transactional
	@Override
	public GenreType getGenreTypeByType(String theGenreType) {
		
		GenreType genreType = genreTypeDAO.getGenreTypeByType(theGenreType);
		
		if (genreType == null)
			throw new GenreTypeNotFoundException("GenreType with Genre Type :"+theGenreType+", not found!"); 
		
		return genreType;
	}

	@Transactional
	@Override
	public void deleteGenreType(int theId) {
		genreTypeDAO.deleteGenreType(theId);
	}
	

}
