package dev.twotough.springlab.tcgstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "card_products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "card_id")
    private Card card;

    private Double price;
    private Integer stock;
}