package dev.twotough.springlab. tcgstore.service;

import dev.twotough.springlab.tcgstore.dto.CartDto;
import dev.twotough.springlab.tcgstore.model.Cart;
import dev.twotough.springlab.tcgstore.entity.Order;
import dev. twotough.springlab.tcgstore.entity.OrderStatus;
import dev.twotough.springlab.tcgstore.model.User;
import dev.twotough.springlab.tcgstore.repository.OrderRepository;
import dev.twotough.springlab. tcgstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util. List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartService cartService;

    // TODO 1: createOrder - crea una orden a partir del carrito del usuario
    // - Obtén el usuario por ID
    // - Obtén el carrito del usuario
    // - Si el carrito está vacío, lanza excepción
    // - Crea una Order con el total del carrito
    // - Guarda la orden en BD
    // - Limpia el carrito (clearCart)
    // - Devuelve la Order creada
    public Order createOrder(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        CartDto cartDto = cartService.getCartByUserId(userId);

        if (cartDto. getItems().isEmpty()) {
            throw new RuntimeException("El carrito está vacío");
        }

        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(cartDto.getTotal());
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());

        Order savedOrder = orderRepository. save(order);
        cartService.clearCart(userId);

        return savedOrder;
    }

    // TODO 2: completeOrder - marca una orden como completada
    // - Busca la orden por ID
    // - Si no existe, lanza excepción
    // - Cambia el status a COMPLETED
    // - Asigna completedAt = ahora
    // - Guarda y devuelve
    public Order completeOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        order.setStatus(OrderStatus. COMPLETED);
        order.setCompletedAt(LocalDateTime.now());

        return orderRepository.save(order);
    }

    // TODO 3: getOrderById - obtiene una orden por ID
    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    // TODO 4: getOrdersByUser - obtiene todas las órdenes de un usuario
    public List<Order> getOrdersByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return orderRepository.findByUser(user);
    }

    // TODO 5: cancelOrder - cancela una orden
    public Order cancelOrder(Long orderId) {
        Order order = orderRepository. findById(orderId)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        if (order.getStatus() == OrderStatus.COMPLETED) {
            throw new RuntimeException("No puedes cancelar una orden completada");
        }

        order.setStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order);
    }
}