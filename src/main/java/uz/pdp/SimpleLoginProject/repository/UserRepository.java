package uz.pdp.SimpleLoginProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.SimpleLoginProject.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findFirstByUsername(String username);
    Optional<User> findFirstByUsernameAndPassword(String username, String password);

}
