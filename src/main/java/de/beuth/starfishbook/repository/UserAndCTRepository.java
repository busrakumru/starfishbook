package de.beuth.starfishbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.beuth.starfishbook.model.User;

@Repository
public interface UserAndCTRepository extends JpaRepository<User, String> {

    User findByEmailIdIgnoreCase(String emailId);
    User findUserByEmail(String email);
    
}
