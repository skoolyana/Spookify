package com.dailycodebuffer.springboot.tutorial.domain.exceptions;

public class AlreadyExistsException extends RuntimeException  {

	public AlreadyExistsException(String message) {
        super(message);
    }
	
	
}
