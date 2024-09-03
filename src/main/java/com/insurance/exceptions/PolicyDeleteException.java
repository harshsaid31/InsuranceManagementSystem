package com.insurance.exceptions;

public class PolicyDeleteException extends Exception {
	public PolicyDeleteException() {
		super();
	}
	
	public PolicyDeleteException(String message) {
		super(message);
	}
}
