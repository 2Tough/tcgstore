package dev.twotough.springlab.tcgstore.exception;

/**
 * Para errores de negocio que deben devolver 400 Bad Request.
 */
public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}