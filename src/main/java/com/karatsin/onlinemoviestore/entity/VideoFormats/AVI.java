package com.karatsin.onlinemoviestore.entity.VideoFormats;

import com.karatsin.onlinemoviestore.controller.IVideoPlayer;

public class AVI implements IVideoPlayer {

	@Override
	public String playVideo(String fileName) {
		return "Decoding and playing AVI video, the movie is : "+fileName;
		
	}

}
