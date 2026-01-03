package dev.twotough.springlab.tcgstore;

import dev.twotough.springlab.tcgstore.dto.CartDto;
import dev.twotough.springlab.tcgstore.exception.InsufficientStockException;
import dev.twotough.springlab.tcgstore.model.Card;
import dev.twotough.springlab.tcgstore.model.User;
import dev.twotough.springlab.tcgstore.repository.CartItemRepository;
import dev.twotough.springlab.tcgstore.repository.CartRepository;
import dev.twotough.springlab.tcgstore.repository.CardRepository;
import dev.twotough.springlab.tcgstore.repository.UserRepository;
import dev.twotough.springlab.tcgstore.service.CartService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    CartRepository cartRepository;

    @Mock
    CartItemRepository cartItemRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    CardRepository cardRepository;

    @InjectMocks
    CartService cartService;

    @Test
    void addToCart_createsCartAndItem() {
        Long userId = 1L;
        Long cardId = 1L;

        User user = new User();
        user.setId(userId);

        Card card = new Card();
        card.setId(cardId);
        card.setName("Test card");
        card.setPrice(new BigDecimal("10.00"));
        card.setStock(5);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(cardRepository.findById(cardId)).thenReturn(Optional.of(card));
        when(cartRepository.findByUser(user)).thenReturn(Optional.empty());
        when(cartRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        CartDto dto = cartService.addToCart(userId, cardId, 2);

        assertNotNull(dto);
        assertEquals(new BigDecimal("20.00"), dto.getTotal());
        verify(cartRepository, times(1)).save(any());
    }

    @Test
    void addToCart_insufficientStock_throws() {
        Long userId = 1L;
        Long cardId = 1L;

        User user = new User();
        user.setId(userId);

        Card card = new Card();
        card.setId(cardId);
        card.setPrice(new BigDecimal("10.00"));
        card.setStock(1);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(cardRepository.findById(cardId)).thenReturn(Optional.of(card));

        assertThrows(InsufficientStockException.class, () -> cartService.addToCart(userId, cardId, 2));
    }
}