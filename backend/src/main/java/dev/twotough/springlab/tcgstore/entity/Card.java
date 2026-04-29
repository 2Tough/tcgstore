package dev.twotough.springlab.tcgstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // "swsh3-136" — usamos el ID de TCGDex directamente
    private String game;           // "Pokemon", "Magic", "Yugioh"


    private String name;           // "Furret"
    private String image;          // URL de la imagen
    private String rarity;         // "Uncommon"
    private String stage;          // "Stage1"
    private String evolveFrom;     // "Sentret"
    private String description;    // texto descriptivo
    private Integer hp;            // 110
    private Integer retreat;       // 1 (costo de retirada)

    // Del set
    private String setName;        // "Darkness Ablaze"
    private String setId;          // "swsh3"
    private String localId;        // "136" (número en el set)

    // Tipos y debilidades como texto simple por ahora
    private String types;          // "Colorless"
    private String weaknesses;     // "Fighting ×2"

    private Integer stock;
    private BigDecimal price;

    // Precio (no viene de la API, lo manejas tú)
    // private Double price;
    // private Integer stock;

    // Magic específico (para después)
    // private String manaCost;
    // private String power;

    // Yugioh específico (para después)
    // private Integer attack;
    // private Integer defense;
}