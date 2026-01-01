package dev.twotough.springlab.tcgstore.repository;

import dev.twotough.springlab.tcgstore.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

}
