package dev.twotough.springlab.tcgstore.repository;

import dev.twotough.springlab.tcgstore.model.Cart;
import dev.twotough.springlab.tcgstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByUser(User user);
}
