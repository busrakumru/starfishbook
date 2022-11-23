package de.beuth.starfishbook.repository;

import de.beuth.starfishbook.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
  
    User findUserByEmail(String email);
    //User findByEmailId(String emailId);


}
