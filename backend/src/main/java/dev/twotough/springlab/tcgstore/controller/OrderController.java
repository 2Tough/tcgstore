package dev.twotough.springlab. tcgstore.controller;

import dev.twotough.springlab.tcgstore.entity.Order;
import dev.twotough.springlab.tcgstore. service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework. web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // TODO 1: POST /api/orders - crear orden a partir del carrito
    // - Recibe userId en el body o como parámetro de query
    // - Llama a orderService.createOrder(userId)
    // - Devuelve ResponseEntity. status(201).body(order)
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestParam Long userId) {
        Order order = orderService.createOrder(userId);
        return ResponseEntity.status(201).body(order);
    }

    // TODO 2: GET /api/orders/{orderId} - obtener orden por ID
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // TODO 3: GET /api/orders/user/{userId} - obtener todas las órdenes de un usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable Long userId) {
        List<Order> orders = orderService. getOrdersByUser(userId);
        return ResponseEntity.ok(orders);
    }

    // TODO 4: POST /api/orders/{orderId}/complete - completar orden (simular pago)
    @PostMapping("/{orderId}/complete")
    public ResponseEntity<Order> completeOrder(@PathVariable Long orderId) {
        Order order = orderService.completeOrder(orderId);
        return ResponseEntity.ok(order);
    }

    // TODO 5: POST /api/orders/{orderId}/cancel - cancelar orden
    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<Order> cancelOrder(@PathVariable Long orderId) {
        Order order = orderService.cancelOrder(orderId);
        return ResponseEntity.ok(order);
    }
}