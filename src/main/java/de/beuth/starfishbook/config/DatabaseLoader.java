package de.beuth.starfishbook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import de.beuth.starfishbook.model.ERoles;
import de.beuth.starfishbook.model.User;
import de.beuth.starfishbook.repository.UserRepository;

@Configuration
public class DatabaseLoader {

    private UserRepository userRepo;
    @Autowired
    PasswordEncoder passwordencoder;

    public DatabaseLoader(UserRepository userRepo) {
        this.userRepo = userRepo;

    }

    //@Bean
    public CommandLineRunner initializeDatabase() {

       /*  User existingUser = userRepo.findUserByEmail(user.getEmail());
        if (existingUser.getRoles() != ERoles.ADMIN) {*/
       /* }
        return null;*/
            return args -> {
                User user1 = new User("admin@gmail.com", passwordencoder.encode("1234"), true, ERoles.ADMIN);
                // User user2 = new User("goncagueld2@gmail.com",passwordencoder.encode("1234"),
                // true, ERoles.USER);
                userRepo.save(user1);
                System.out.println("Database initialized");
            };
    }
}