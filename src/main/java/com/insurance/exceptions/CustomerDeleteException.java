package com.insurance.exceptions;

public class CustomerDeleteException extends Exception {
	public CustomerDeleteException() {
		super();
	}
	
	public CustomerDeleteException(String message) {
		super(message);
	}
}
