package dev.twotough.springlab.tcgstore.entity;

public enum OrderStatus {
    PENDING,      // Orden creada, pendiente de pago
    COMPLETED,    // Orden pagada/completada
    CANCELLED     // Orden cancelada
}