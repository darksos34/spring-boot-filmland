package com.jdacoder.spring.auth.payload.response;

/* All rights reserved by JdaCoder */

public class MessageResponse {

	private String message;
	public MessageResponse(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}

