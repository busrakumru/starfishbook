package de.beuth.starfishbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.beuth.starfishbook.model.User;

@Repository
public interface AuthCRepository extends JpaRepository<User, String> {

    User findUserByEmail(String email);
    
}
