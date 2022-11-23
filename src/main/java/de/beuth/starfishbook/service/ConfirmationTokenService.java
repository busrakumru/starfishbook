package de.beuth.starfishbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.beuth.starfishbook.repository.ConfirmationTokenRepository;

@Service
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    public ConfirmationTokenService(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    public Boolean delete(Long id) {
        this.confirmationTokenRepository.deleteById(id);
        return this.confirmationTokenRepository.existsById(id);
    }
    
}
