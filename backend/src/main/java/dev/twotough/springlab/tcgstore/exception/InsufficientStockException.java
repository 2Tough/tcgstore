package dev.twotough.springlab.tcgstore.exception;

import java.math.BigDecimal;

/**
 * Lanzar cuando la cantidad solicitada excede el stock disponible.
 */
public class InsufficientStockException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InsufficientStockException() {
        super();
    }

    public InsufficientStockException(String message) {
        super(message);
    }

    public InsufficientStockException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor de comodidad
    public InsufficientStockException(Long cardId, int requested, int available) {
        super(String.format("Stock insuficiente para la carta id=%d: solicitadas=%d, disponibles=%d", cardId, requested, available));
    }
}