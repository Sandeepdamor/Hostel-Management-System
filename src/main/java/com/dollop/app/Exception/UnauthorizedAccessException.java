package com.dollop.app.Exception;

public class UnauthorizedAccessException extends RuntimeException
{

	
	private static final long serialVersionUID = 1L;

	public UnauthorizedAccessException() {
		super();
	}

	public UnauthorizedAccessException(String message) {
		super(message);
	}
	
	

}
