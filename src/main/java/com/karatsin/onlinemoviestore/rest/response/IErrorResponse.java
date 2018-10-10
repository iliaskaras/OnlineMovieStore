package com.karatsin.onlinemoviestore.rest.response;

/* Interface for ErrorResponses, so our IRestExceptionHandler implementations avoid compilation errors */
public interface IErrorResponse {

	public int getStatus();
	
	public void setStatus(int status);
	
	public String getMessage();
	
	public void setMessage(String message);
	
	public long getTimeStamp();
	
	public void setTimeStamp(long timeStamp);
	
}
