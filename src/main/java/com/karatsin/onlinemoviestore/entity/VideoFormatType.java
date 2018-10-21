package com.karatsin.onlinemoviestore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="video_format_types")
public class VideoFormatType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="video_format_type_id",nullable = false)
	int videoFormatTypeId;
	
	@Column(name="video_format_type_description",nullable = false)
	String videoFormatTypeDescription;

	public VideoFormatType() {}
	
	public VideoFormatType(int videoFormatTypeId, String videoFormatTypeDescription) {
		this.videoFormatTypeId = videoFormatTypeId;
		this.videoFormatTypeDescription = videoFormatTypeDescription;
	}

	public int getVideoFormatTypeId() {
		return videoFormatTypeId;
	}

	public void setVideoFormatTypeId(int videoFormatTypeId) {
		this.videoFormatTypeId = videoFormatTypeId;
	}

	public String getVideoFormatTypeDescription() {
		return videoFormatTypeDescription;
	}

	public void setVideoFormatTypeDescription(String videoFormatTypeDescription) {
		this.videoFormatTypeDescription = videoFormatTypeDescription;
	}
	
	
}
