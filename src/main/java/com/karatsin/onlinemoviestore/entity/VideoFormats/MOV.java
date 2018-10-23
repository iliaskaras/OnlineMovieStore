package com.karatsin.onlinemoviestore.entity.VideoFormats;

import com.karatsin.onlinemoviestore.controller.IVideoPlayer;

public class MOV implements IVideoPlayer {

	@Override
	public String playVideo(String fileName) {
		return "Decoding and playing MOV video, the movie is : "+fileName;
		
	}

}
