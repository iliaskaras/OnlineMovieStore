package com.karatsin.onlinemoviestore.entity.VideoFormats;

import com.karatsin.onlinemoviestore.controller.IVideoPlayer;

public class WMV implements IVideoPlayer {

	@Override
	public String playVideo(String fileName) {
		return "Decoding and playing WMV video, the movie is : "+fileName;
		
	}

}
