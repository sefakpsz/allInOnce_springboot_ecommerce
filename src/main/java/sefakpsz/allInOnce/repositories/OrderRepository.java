package sefakpsz.allInOnce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sefakpsz.allInOnce.entities.Order;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
