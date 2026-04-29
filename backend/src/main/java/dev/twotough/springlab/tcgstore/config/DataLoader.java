package dev.twotough.springlab.tcgstore.config;

import dev.twotough.springlab.tcgstore.entity.Card;
import dev.twotough.springlab.tcgstore.model.User;
import dev.twotough.springlab.tcgstore.repository.CardRepository;
import dev.twotough.springlab.tcgstore.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {

    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    public DataLoader(CardRepository cardRepository, UserRepository userRepository) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (cardRepository.count() == 0) {
            Card c1 = new Card();
            c1.setName("Pikachu V");
            c1.setGame("Pokemon");
            c1.setRarity("Ultra Rare");
            c1.setImage("https://example.com/pikachu.jpg");
            c1.setDescription("Carta de ejemplo: Pikachu V");

            Card c2 = new Card();
            c2.setName("Black Lotus");
            c2.setGame("Magic");
            c2.setRarity("Rare");
            c2.setImage("https://example.com/blacklotus.jpg");
            c2.setDescription("Carta legendaria de ejemplo");

            cardRepository.save(c1);
            cardRepository.save(c2);
        }

        if (userRepository.count() == 0) {
            User u = new User();
            u.setUsername("demo");
            u.setEmail("demo@example.com");
            u.setPassword("changeme");
            u.setFirstName("Demo");
            u.setLastName("User");
            userRepository.save(u);
            System.out.println("Seeded demo user id=" + u.getId());
        }
    }
}