package com.karatsin.onlinemoviestore.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.karatsin.onlinemoviestore.dao.IAccountDAO;
import com.karatsin.onlinemoviestore.dao.IVideoFormatTypeDAO;
import com.karatsin.onlinemoviestore.entity.Account;
import com.karatsin.onlinemoviestore.entity.VideoFormatType;
import com.karatsin.onlinemoviestore.exception.account.AccountNotFoundException;
import com.karatsin.onlinemoviestore.exception.account.InvalidAccountUsernameException;
import com.karatsin.onlinemoviestore.exception.video_format.VideoFormatTypeNotFoundException;

@Service
public class VideoFormatService implements IVideoFormatService{
	
	    /* inject the user dao */
		@Autowired
		private IVideoFormatTypeDAO videoFormatTypeDAO;
	

		@Override
		@Transactional
		public VideoFormatType getVideoFormatById(int theId) {
			
			VideoFormatType theVideoFormatType = videoFormatTypeDAO.getVideoFormatById(theId);
			
			if (theVideoFormatType == null)
				throw new VideoFormatTypeNotFoundException("Video Format Type with id :"+theId+", not found!"); 
			
			return theVideoFormatType;
		}
		
	
}