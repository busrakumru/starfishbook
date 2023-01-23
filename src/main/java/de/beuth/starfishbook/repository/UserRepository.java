package de.beuth.starfishbook.repository;

import de.beuth.starfishbook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserid(Long userid);

    User findUserByEmail(String email);

}
