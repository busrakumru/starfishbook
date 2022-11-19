package de.beuth.starfishbook.repository;

import org.springframework.data.repository.CrudRepository;

import de.beuth.starfishbook.model.User;

public interface UserAndCTRepository extends CrudRepository<User, String> {

    User findByEmailIdIgnoreCase(String emailId);
    
}
