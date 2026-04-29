package dev.twotough.springlab.tcgstore.service;

import dev.twotough.springlab.tcgstore.dto.CartDto;
import dev.twotough.springlab.tcgstore.dto.CartItemDto;
import dev.twotough.springlab.tcgstore.exception.InsufficientStockException;
import dev.twotough.springlab.tcgstore.exception.ResourceNotFoundException;
import dev.twotough.springlab.tcgstore.model.Cart;
import dev.twotough.springlab.tcgstore.model.CartItem;
import dev.twotough.springlab.tcgstore.entity.Card;
import dev.twotough.springlab.tcgstore.model.User;
import dev.twotough.springlab.tcgstore.repository.CartItemRepository;
import dev.twotough.springlab.tcgstore.repository.CartRepository;
import dev.twotough.springlab.tcgstore.repository.CardRepository;
import dev.twotough.springlab.tcgstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final CardRepository cardRepository;

    @Transactional(readOnly = true)
    public CartDto getCartByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId));

        Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
            Cart c = new Cart();
            c.setUser(user);
            return c;
        });

        return mapToDto(cart);
    }

    @Transactional
    public CartDto addToCart(Long userId, Long cardId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be greater than 0");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId));

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new ResourceNotFoundException("Card", cardId));

        // Validar stock (no reservamos stock todavía)
        if (card.getStock() < quantity) {
            throw new InsufficientStockException(cardId, quantity, card.getStock());
        }

        Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
            Cart c = new Cart();
            c.setUser(user);
            return c;
        });

        Optional<CartItem> existing = cart.getItems().stream()
                .filter(ci -> ci.getCard().getId().equals(cardId))
                .findFirst();

        if (existing.isPresent()) {
            CartItem item = existing.get();
            int newQty = item.getQuantity() + quantity;
            if (card.getStock() < newQty) {
                throw new InsufficientStockException(cardId, newQty, card.getStock());
            }
            item.setQuantity(newQty);
        } else {
            CartItem item = new CartItem();
            item.setCard(card);
            item.setQuantity(quantity);
            cart.addItem(item); // helper in Cart sets cart on item
        }

        Cart saved = cartRepository.save(cart);
        return mapToDto(saved);
    }

    @Transactional
    public void removeItem(Long itemId) {
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem", itemId));
        Cart cart = item.getCart();
        if (cart != null) {
            cart.removeItem(item);
            cartRepository.save(cart);
        } else {
            // if orphan, just delete
            cartItemRepository.delete(item);
        }
    }

    @Transactional
    public void clearCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId));
        Cart cart = cartRepository.findByUser(user).orElse(null);
        if (cart != null) {
            cart.getItems().clear();
            cartRepository.save(cart);
        }
    }

    // --- Mapeo entidad -> DTO ---
    private CartDto mapToDto(Cart cart) {
        CartDto dto = new CartDto();
        dto.setId(cart.getId());
        dto.setUserId(cart.getUser() != null ? cart.getUser().getId() : null);

        dto.setItems(cart.getItems().stream()
                .map(this::mapItemToDto)
                .collect(Collectors.toList()));

        BigDecimal total = dto.getItems().stream()
                .map(CartItemDto::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        dto.setTotal(total);
        return dto;
    }

    private CartItemDto mapItemToDto(CartItem item) {
        CartItemDto dto = new CartItemDto();
        dto.setId(item.getId());
        dto.setCardId(item.getCard().getId());
        dto.setCardName(item.getCard().getName());
        dto.setGame(item.getCard().getGame());
        dto.setPrice(item.getCard().getPrice());
        dto.setQuantity(item.getQuantity());
        dto.setLineTotal(item.getCard().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        return dto;
    }
}