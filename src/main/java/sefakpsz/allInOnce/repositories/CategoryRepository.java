package sefakpsz.allInOnce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sefakpsz.allInOnce.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
