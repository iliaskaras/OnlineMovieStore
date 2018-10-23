package com.karatsin.onlinemoviestore.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.karatsin.onlinemoviestore.entity.VideoFormatType;

@Repository
public class VideoFormatTypeDAO implements IVideoFormatTypeDAO {

	/* session factory injection */
	@Autowired
	private SessionFactory sessionFactory;
	
	/* Returns a video format type given the id */
	@Override
	public VideoFormatType getVideoFormatById(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
			
		VideoFormatType theVideoFormatType = currentSession.get(VideoFormatType.class, theId);
			
		return theVideoFormatType;
	}
	
}
