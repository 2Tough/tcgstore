package dev.twotough.springlab.tcgstore.controller;

import dev.twotough.springlab.tcgstore.dto.AddToCartRequest;
import dev.twotough.springlab.tcgstore.dto.CartDto;
import dev.twotough.springlab.tcgstore.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    // Obtener carrito por userId
    @GetMapping("/api/users/{userId}/cart")
    public ResponseEntity<CartDto> getCart(@PathVariable Long userId) {
        CartDto dto = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(dto);
    }

    // Añadir al carrito (usa AddToCartRequest en el body)
    @PostMapping("/api/cart/add")
    public ResponseEntity<CartDto> addToCart(@Valid @RequestBody AddToCartRequest request) {
        CartDto dto = cartService.addToCart(request.getUserId(), request.getCardId(), request.getQuantity());
        return ResponseEntity.ok(dto);
    }

    // Eliminar item del carrito por itemId
    @DeleteMapping("/api/cart/items/{itemId}")
    public ResponseEntity<Void> removeItem(@PathVariable Long itemId) {
        cartService.removeItem(itemId);
        return ResponseEntity.noContent().build();
    }

    // Vaciar carrito del usuario
    @PostMapping("/api/users/{userId}/cart/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}