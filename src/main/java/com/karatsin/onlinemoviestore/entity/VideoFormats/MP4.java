package com.karatsin.onlinemoviestore.entity.VideoFormats;

import com.karatsin.onlinemoviestore.controller.IVideoPlayer;

public class MP4 implements IVideoPlayer {

	@Override
	public String playVideo(String fileName) {
		return "Decoding and playing MP4 video, the movie is : "+fileName;
		
	}

}
