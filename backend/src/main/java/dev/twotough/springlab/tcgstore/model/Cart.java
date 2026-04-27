package dev.twotough.springlab.tcgstore.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate(){
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate(){
        LocalDateTime now = LocalDateTime.now();
    }

    public void addItem(CartItem item){
        item.setCart(this);
        this.items.add(item);
    }

    public void removeItem(CartItem item){
        item.setCart(null);
        this.items.remove(item);

    }

    public List<CartItem> getItems() {
        return items!=null?items:new ArrayList<>() ;
    }

    public BigDecimal getTotal(){
        return getItems().stream()
                .map(item -> item.getCard().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal. ZERO, BigDecimal::add);
    }


}
