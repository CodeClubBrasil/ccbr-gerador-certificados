package org.codeclubbrasil.certificategenerator.exception;

public class InvalidTemplateException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidTemplateException(String message) {
        super(message);
    }

    public InvalidTemplateException(String message, Throwable cause) {
        super(message, cause);
    }


}
