package dev.twotough.springlab.tcgstore.entity;

import dev.twotough.springlab.tcgstore.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok. Getter;
import lombok.NoArgsConstructor;
import lombok. Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType. IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // TODO: Relación con Cart (opcional, puedes guardar los items directamente)
    // @OneToMany(cascade = CascadeType.ALL)
    // private List<CartItem> items;

    @Column(nullable = false)
    private BigDecimal totalAmount;

    @Column(nullable = false)
    @Enumerated(EnumType. STRING)
    private OrderStatus status = OrderStatus.PENDING;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column
    private LocalDateTime completedAt;
}