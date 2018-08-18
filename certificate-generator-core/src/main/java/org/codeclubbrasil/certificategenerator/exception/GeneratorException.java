package org.codeclubbrasil.certificategenerator.exception;

/**
 * Exception lançada caso aconteça algum problema na geração
 */
public class GeneratorException extends Exception {

    private static final long serialVersionUID = 1L;

    public GeneratorException(String message) {
        super(message);
    }

    public GeneratorException(String message, Throwable cause) {
        super(message, cause);
    }


}
