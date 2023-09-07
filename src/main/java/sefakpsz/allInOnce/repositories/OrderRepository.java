package sefakpsz.allInOnce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sefakpsz.allInOnce.entities.Order;
import sefakpsz.allInOnce.entities.User;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findOrdersByUser(User user);
}
