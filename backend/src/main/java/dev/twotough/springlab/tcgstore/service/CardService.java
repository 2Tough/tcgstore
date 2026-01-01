package dev.twotough.springlab.tcgstore.service;

import dev.twotough.springlab.tcgstore.model.Card;
import dev.twotough.springlab.tcgstore.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> listAll() {
        return cardRepository.findAll();
    }

    public Optional<Card> getById(Long id) {
        return cardRepository.findById(id);
    }

    public List<Card> findByGame(String game) {
        return cardRepository.findByGameIgnoreCase(game);
    }
}