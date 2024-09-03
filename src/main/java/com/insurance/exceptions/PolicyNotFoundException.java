package com.insurance.exceptions;

public class PolicyNotFoundException extends Exception {
	public PolicyNotFoundException() {
		super();
	}
	
	public PolicyNotFoundException(String message) {
		super(message);
	}
}
