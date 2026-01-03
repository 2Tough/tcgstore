package dev.twotough.springlab.tcgstore;

import dev.twotough.springlab.tcgstore.dto.CartDto;
import dev.twotough.springlab.tcgstore.model.Cart;
import dev.twotough.springlab.tcgstore.model.User;
import dev. twotough.springlab.tcgstore.repository.CartRepository;
import dev.twotough. springlab.tcgstore.repository.UserRepository;
import dev.twotough.springlab.tcgstore.service.CartService;
import org. junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org. mockito.Mock;
import org.mockito.junit.jupiter. MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api. Assertions.*;
import static org. mockito. Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CartService cartService;

    // =============== TESTS PARA getCartByUserId ===============

    @Test
    void testGetCartByUserIdSuccess() {
        // Arrange (setup)
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setUsername("testuser");

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setUser(user);
        cart.setItems(Collections.emptyList()); // si tienes setter

        // Configurar los mocks para devolver datos cuando se llaman
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(cartRepository.findByUser(user)).thenReturn(Optional.of(cart));

        // Act (ejecutar)
        CartDto result = cartService.getCartByUserId(userId);

        // Assert (verificar)
        assertNotNull(result);
        assertEquals(userId, result.getUserId());
        assertEquals(1L, result.getId());

        // Verificar que los mocks fueron llamados
        verify(userRepository, times(1)).findById(userId);
        verify(cartRepository, times(1)).findByUser(user);
    }

    @Test
    void testGetCartByUserIdNotFound() {
        // Arrange
        Long userId = 999L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        // TODO: ¿Qué excepción lanza tu CartService si el usuario no existe?
        // Completa este test:  assertThrows(Exception.class, () -> cartService.getCartByUserId(userId));
        assertThrows(RuntimeException.class, () -> cartService.getCartByUserId(userId));
    }

    // =============== TESTS PARA addToCart ===============

    @Test
    void testAddToCartSuccess() {
        // Arrange
        Long userId = 1L;
        Long cardId = 1L;
        Integer quantity = 2;

        User user = new User();
        user.setId(userId);

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setUser(user);

        // TODO: configura los mocks:
        // 1. when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        // 2. when(cartRepository.findByUser(user)).thenReturn(Optional.of(cart));
        // 3. when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(cartRepository.findByUser(user)).thenReturn(Optional.of(cart));
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        // Act
        CartDto result = cartService. addToCart(userId, cardId, quantity);

        // Assert
        assertNotNull(result);
        assertEquals(userId, result.getUserId());
        // TODO: verifica que cartRepository.save fue llamado
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    // =============== TESTS PARA removeItem ===============

    @Test
    void testRemoveItemSuccess() {
        // Arrange
        Long itemId = 1L;
        // TODO: crea un CartItem mock y configura cartItemRepository. findById

        // Act
        // cartService.removeItem(itemId);

        // Assert
        // TODO: verifica que cartItemRepository. deleteById fue llamado
        // verify(cartItemRepository, times(1)).deleteById(itemId);
    }

    // =============== TESTS PARA clearCart ===============

    @Test
    void testClearCartSuccess() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setUser(user);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(cartRepository.findByUser(user)).thenReturn(Optional.of(cart));

        // Act
        cartService. clearCart(userId);

        // Assert
        // TODO: verifica que los items se eliminaron
        // (esto depende de cómo implementaste clearCart)
    }
}