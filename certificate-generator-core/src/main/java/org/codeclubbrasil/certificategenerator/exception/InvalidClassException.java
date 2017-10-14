package org.codeclubbrasil.certificategenerator.exception;

public class InvalidClassException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidClassException(String message) {
		super(message);
	}

	public InvalidClassException(String message, Throwable cause) {
		super(message, cause);
	}

}
