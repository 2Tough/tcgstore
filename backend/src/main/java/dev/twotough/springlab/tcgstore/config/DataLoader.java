package dev.twotough.springlab.tcgstore.config;

import dev.twotough.springlab.tcgstore.model.Card;
import dev.twotough.springlab.tcgstore.repository.CardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {

    private final CardRepository cardRepository;

    public DataLoader(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (cardRepository.count() == 0) {
            Card c1 = new Card();
            c1.setName("Pikachu V");
            c1.setGame("Pokemon");
            c1.setRarity("Ultra Rare");
            c1.setPrice(new BigDecimal("19.99"));
            c1.setStock(5);
            c1.setImageUrl(null);
            c1.setDescription("Carta de ejemplo: Pikachu V");

            Card c2 = new Card();
            c2.setName("Black Lotus");
            c2.setGame("Magic");
            c2.setRarity("Rare");
            c2.setPrice(new BigDecimal("99999.99"));
            c2.setStock(1);
            c2.setImageUrl(null);
            c2.setDescription("Carta legendaria de ejemplo");

            cardRepository.save(c1);
            cardRepository.save(c2);

            System.out.println("Seeded cards: " + cardRepository.count());
        } else {
            System.out.println("Cards already present: " + cardRepository.count());
        }
    }
}