package de.beuth.starfishbook.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.beuth.starfishbook.model.ConfirmationToken;

@Repository
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {

    ConfirmationToken findByConfirmationToken(String confirmationToken);
    
}
