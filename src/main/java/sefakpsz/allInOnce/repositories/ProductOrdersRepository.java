package sefakpsz.allInOnce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sefakpsz.allInOnce.entities.ProductOrders;

import java.util.List;

public interface ProductOrdersRepository extends JpaRepository<ProductOrders, Integer> {
    List<ProductOrders> findProductOrdersByOrderId(Integer orderId);

    ProductOrders findProductOrdersByProductId(Integer productId);
}
