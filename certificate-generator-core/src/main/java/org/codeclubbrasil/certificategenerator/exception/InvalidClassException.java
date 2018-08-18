package org.codeclubbrasil.certificategenerator.exception;

/**
 * Exceção utilizada para quando informada uma classe inválida
 */
public class InvalidClassException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidClassException(String message) {
        super(message);
    }

    public InvalidClassException(String message, Throwable cause) {
        super(message, cause);
    }

}
