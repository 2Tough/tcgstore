package dev.twotough.springlab.tcgstore.exception;

/**
 * Lanzar cuando no se encuentra un recurso (ej. User, Card, Cart).
 */
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor de comodidad
    public ResourceNotFoundException(String resourceName, Object id) {
        super(String.format("%s con id '%s' no encontrado", resourceName, id));
    }
}