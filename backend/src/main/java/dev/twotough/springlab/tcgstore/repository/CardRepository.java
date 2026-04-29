package dev.twotough.springlab.tcgstore.repository;

import dev.twotough.springlab.tcgstore.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card,Long> {
    List<Card> findByGameIgnoreCase(String game);
    List<Card> findByNameContainingIgnoreCase(String name);
}
