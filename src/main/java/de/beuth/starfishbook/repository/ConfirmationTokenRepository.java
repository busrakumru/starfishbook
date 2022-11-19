package de.beuth.starfishbook.repository;

import org.springframework.data.repository.CrudRepository;

import de.beuth.starfishbook.model.ConfirmationToken;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {

    ConfirmationToken findByConfirmationToken(String confirmationToken);
    
}
