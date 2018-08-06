package com.rest.demo.restdemo.exception;

public class BlogNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BlogNotFoundException(String message) {
		super(message);
	}

}
