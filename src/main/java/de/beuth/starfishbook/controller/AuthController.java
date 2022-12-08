package de.beuth.starfishbook.controller;

import de.beuth.starfishbook.model.ConfirmationToken;
import de.beuth.starfishbook.model.User;
import de.beuth.starfishbook.repository.ConfirmationTokenRepository;
import de.beuth.starfishbook.repository.UserRepository;
import de.beuth.starfishbook.repository.AuthCRepository;
import de.beuth.starfishbook.response.JwtResponse;
import de.beuth.starfishbook.security.JwtTokenProvider;
import de.beuth.starfishbook.service.ConfirmationTokenService;
import de.beuth.starfishbook.service.EmailService;
import de.beuth.starfishbook.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "https://localhost:8100")
@RestController
@RequestMapping("/auth/")
public class AuthController {

    @Autowired
    private AuthCRepository authRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationRepository;

    @Autowired
    private ConfirmationTokenService confirmationService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthCRepository authRepository, PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("users")
    public List<User> getUsers() {
        return this.authRepository.findAll();
    }

    @PostMapping(value = "register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User existingUser = authRepository.findUserByEmail(user.getEmail());

        if (existingUser != null) {
            return ResponseEntity.badRequest().build();

        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User c = authRepository.save(user);

            // token
            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationRepository.save(confirmationToken);

            // sendeverification email
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Bitte bestätigen Sie ihre Anmeldung");
            mailMessage.setText("Bitte verifizieren Sie sich, indem sie auf den Link klicken! "
                    + "http://localhost:8443/auth/confirm?token=" +
                    confirmationToken.getConfirmationToken());

            emailService.sendEmail(mailMessage);
            return ResponseEntity.ok(c);

        }

    }

    @RequestMapping(value = "confirm", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) {

        ConfirmationToken token = confirmationRepository.findByConfirmationToken(confirmationToken);
        User user = authRepository.findUserByEmail(token.getUser().getEmail());

        if (user == null || user.isEnabled()) {
            modelAndView.setViewName("error");

        } else {
            user.setEnabled(true);
            authRepository.save(user);
            this.confirmationService.delete(token.tokenid);

            modelAndView.setViewName("accountVerified");
        }

        return modelAndView;

    }

    @DeleteMapping("/confirmationToken/{id}")
    public Boolean delete(@PathVariable Long id) {
        return this.confirmationService.delete(id);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        User existingUser = authRepository.findUserByEmail(user.getEmail());

        if (existingUser.isEnabled() == true) {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(),
                            user.getPassword()));
            final String token = jwtTokenProvider.generateToken(authentication);
            return ResponseEntity.ok(new JwtResponse(token));

        } else {
            return ResponseEntity.badRequest().build();

        }

    }

    @DeleteMapping("/users/{id}")
    public Boolean deleteUser(@PathVariable Long id) {
        return this.userService.delete(id);
    }


}