package dev.twotough.springlab.tcgstore.service;

import dev.twotough.springlab.tcgstore.dto.CardInfo;
import dev.twotough.springlab.tcgstore.dto.PokeWalletSearchResponse;
import dev.twotough.springlab.tcgstore.dto.PokeWalletSearchResult;
import dev.twotough.springlab.tcgstore.entity.Card;
import dev.twotough.springlab.tcgstore.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final PokeWalletClient pokeWalletClient;

    public List<Card> listAll() {
        return cardRepository.findAll();
    }

    public Optional<Card> getById(Long id) {
        return cardRepository.findById(id);
    }

    public List<Card> findByGame(String game) {
        return cardRepository.findByGameIgnoreCase(game);
    }

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    public Mono<List<Card>> searchCards(String query) {
        return pokeWalletClient.searchCards(query)
                .map(PokeWalletSearchResponse::results)
                .map(results -> results.stream()
                        .map(this::mapToCard)
                        .toList());
    }

    private Card mapToCard(PokeWalletSearchResult result) {
        CardInfo info = result.cardInfo();

        Card card = new Card();
        card.setGame("Pokemon");
        card.setName(info.name());
        card.setRarity(info.rarity());
        card.setStage(info.stage());
        card.setDescription(info.cardText());
        card.setHp(parseInteger(info.hp()));
        card.setRetreat(parseInteger(info.retreatCost()));
        card.setSetName(info.setName());
        card.setSetId(info.setId());
        card.setLocalId(info.cardNumber());
        card.setTypes(info.cardType());

        return card;
    }

    private Integer parseInteger(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return (int) Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}