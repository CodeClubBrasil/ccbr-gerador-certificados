package org.codeclubbrasil.certificategenerator.exception;

public class GeneratorException extends Exception {

	private static final long serialVersionUID = 1L;

	public GeneratorException() {
		super();
	}

	public GeneratorException(String message) {
		super(message);
	}

	public GeneratorException(String message, Throwable cause) {
		super(message, cause);
	}

	public GeneratorException(Throwable cause) {
		super(cause);
	}

}
