package de.beuth.starfishbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import de.beuth.starfishbook.model.User;
import de.beuth.starfishbook.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean delete(Long id) {
        this.userRepository.deleteById(id);
        return this.userRepository.existsById(id);
    }

    public User findUserById(Long id) {
        return this.userRepository.findByUserid(id);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

   

}
