package com.elearning.rest.webservices.restfulwebservices.exception;

import java.util.Date;

public class ExceptionResponse {

	private Date timeStamp;
	private String message;
	private String details;
	public ExceptionResponse(Date date, String message, String details) {
		super();
		this.timeStamp = date;
		this.message = message;
		this.details = details;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}
	
	
}
