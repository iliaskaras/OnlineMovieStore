package com.karatsin.onlinemoviestore.controller;

import com.karatsin.onlinemoviestore.entity.VideoFormats.AVI;
import com.karatsin.onlinemoviestore.entity.VideoFormats.HDV;
import com.karatsin.onlinemoviestore.entity.VideoFormats.MOV;
import com.karatsin.onlinemoviestore.entity.VideoFormats.MP4;
import com.karatsin.onlinemoviestore.entity.VideoFormats.WMV;

/* Our video player factory class */
public class VideoPlayerFactory {

	   public IVideoPlayer getVideoPlayer(String videoFormatType){
		   
	      if(videoFormatType == null){
	         return null;
	      }		
	      
	      if(videoFormatType.equalsIgnoreCase("AVI")){
	         return new AVI();
	         
	      } else if(videoFormatType.equalsIgnoreCase("HDV")){
	         return new HDV();
	         
	      } else if(videoFormatType.equalsIgnoreCase("MOV")){
	         return new MOV();
	         
	      } else if(videoFormatType.equalsIgnoreCase("MP4")){
		     return new MP4();
		     
		  } else if(videoFormatType.equalsIgnoreCase("WMV")){
			 return new WMV();
		  }
	      
	      return null;
	   }
}
