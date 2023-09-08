package sefakpsz.allInOnce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sefakpsz.allInOnce.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);
    User findUserById(Integer userId);
}
