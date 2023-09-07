package sefakpsz.allInOnce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sefakpsz.allInOnce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findProductByTitle(String title);
}
