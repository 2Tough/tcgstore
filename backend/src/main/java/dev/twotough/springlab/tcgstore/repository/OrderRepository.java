package dev.twotough.springlab.tcgstore.repository;

import dev.twotough.springlab. tcgstore.entity.Order;
import dev.twotough.springlab. tcgstore.model.User;
import org.springframework.data. jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // TODO: Método para obtener órdenes por usuario
    // List<Order> findByUser(User user);
    List<Order> findByUser(User user);
}