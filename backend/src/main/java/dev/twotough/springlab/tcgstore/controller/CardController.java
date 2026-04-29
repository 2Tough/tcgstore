package dev.twotough.springlab.tcgstore.controller;

import dev.twotough.springlab.tcgstore.entity.Card;
import dev.twotough.springlab.tcgstore.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    // Listar todas las tarjetas
    @GetMapping
    public ResponseEntity<List<Card>> listAll() {
        List<Card> cards = cardService.listAll();
        return ResponseEntity.ok(cards);
    }

    // Obtener tarjeta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Card> getById(@PathVariable Long id) {
        return cardService.getById(id)
                .map(ResponseEntity:: ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Buscar tarjetas por juego
    @GetMapping("/game/{game}")
    public ResponseEntity<List<Card>> findByGame(@PathVariable String game) {
        List<Card> cards = cardService.findByGame(game);
        return ResponseEntity.ok(cards);
    }

    // TODO: Crear tarjeta nueva
    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        if (card.getName() == null || card.getName().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Card saved = cardService.createCard(card);
        return ResponseEntity.status(201).body(saved);  // 201 Created
    }
}