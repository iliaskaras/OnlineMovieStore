package com.karatsin.onlinemoviestore.responses;


/* Custom error response class , a simple POJO 
 * We have different error response pojo classes because of the possibility to want to print more than 
 * status,message and timeStamp for a specific POJO class*/
public class VideoFormatErrorResponse<T> implements IErrorResponse{
	
	private int status;
	private String message;
	private long timeStamp;
	
	public VideoFormatErrorResponse() { }
	
	public VideoFormatErrorResponse(int status, String message, long timeStamp) {
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
	}

	@Override
	public int getStatus() {
		return status;
	}
	
	@Override
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
	@Override
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public long getTimeStamp() {
		return timeStamp;
	}
	
	@Override
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	

}
