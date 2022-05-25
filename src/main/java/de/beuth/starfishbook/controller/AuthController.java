package de.beuth.starfishbook.controller;

import de.beuth.starfishbook.entity.User;
import de.beuth.starfishbook.repository.UserRepository;
import de.beuth.starfishbook.request.AuthRequest;
import de.beuth.starfishbook.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }
  // get all notes
  @GetMapping("/all")
  public List<User> index() {
      return userRepository.findAll();

  }
   
   
    @PostMapping(value = "/register")
    public ResponseEntity<User> register(@RequestBody AuthRequest authRequest) {
        Optional<User> userOptional = userRepository.findUserByEmail(authRequest.getEmail());

        if (userOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        User user = new User();
        user.setEmail(authRequest.getEmail());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));

        User created = userRepository.save(user);

        return ResponseEntity.ok(created);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );

        return ResponseEntity.ok(jwtTokenProvider.generateToken(authentication));
    }

}
