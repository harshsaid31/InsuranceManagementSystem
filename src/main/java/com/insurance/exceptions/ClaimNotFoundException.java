package com.insurance.exceptions;

public class ClaimNotFoundException extends Exception {
	public ClaimNotFoundException() {
		super();
	}
	
	public ClaimNotFoundException(String message) {
		super(message);
	}
}
