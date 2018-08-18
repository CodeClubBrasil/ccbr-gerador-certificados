package org.codeclubbrasil.certificategenerator.exception;

/**
 * Exceção utilizada quando informado um template inválido
 */
public class InvalidTemplateException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidTemplateException(String message) {
        super(message);
    }

    public InvalidTemplateException(String message, Throwable cause) {
        super(message, cause);
    }

}
