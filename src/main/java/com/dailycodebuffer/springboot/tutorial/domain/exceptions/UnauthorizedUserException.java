package com.dailycodebuffer.springboot.tutorial.domain.exceptions;

public class UnauthorizedUserException extends RuntimeException{

	 public UnauthorizedUserException(String message) {
	        super(message);
	    }
	
}
