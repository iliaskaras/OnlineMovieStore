package com.karatsin.onlinemoviestore.entity.VideoFormats;

import com.karatsin.onlinemoviestore.controller.IVideoPlayer;

public class HDV implements IVideoPlayer {

	@Override
	public String playVideo(String fileName) {
		return "Decoding and playing HDV video, the movie is : "+fileName;
		
	}

}
