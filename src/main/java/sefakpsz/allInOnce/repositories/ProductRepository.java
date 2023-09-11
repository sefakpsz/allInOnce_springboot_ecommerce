package sefakpsz.allInOnce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sefakpsz.allInOnce.entities.Category;
import sefakpsz.allInOnce.entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findProductByTitle(String title);
    List<Product> findProductsByCategory(Category category);
    Product findProductById(Integer id);
}
