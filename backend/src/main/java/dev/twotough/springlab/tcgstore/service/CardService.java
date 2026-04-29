package dev.twotough.springlab.tcgstore.service;

import dev.twotough.springlab.tcgstore.entity.Card;
import dev.twotough.springlab.tcgstore.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor  // Usa esto en lugar de constructor manual
public class CardService {

    private final CardRepository cardRepository;

    public List<Card> listAll() {
        return cardRepository. findAll();
    }

    public Optional<Card> getById(Long id) {
        return cardRepository.findById(id);
    }

    public List<Card> findByGame(String game) {
        return cardRepository.findByGameIgnoreCase(game);
    }

    // TODO: Añade este método
    public Card createCard(Card card) {
        // Guarda la tarjeta en BD
        return cardRepository.save(card);
    }
}