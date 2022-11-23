package de.beuth.starfishbook.controller;

import de.beuth.starfishbook.model.ConfirmationToken;
import de.beuth.starfishbook.model.User;
import de.beuth.starfishbook.repository.ConfirmationTokenRepository;
import de.beuth.starfishbook.repository.UserRepository;
import de.beuth.starfishbook.request.AuthRequest;
import de.beuth.starfishbook.response.JwtResponse;
import de.beuth.starfishbook.security.JwtTokenProvider;
import de.beuth.starfishbook.service.EmailService;

import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@CrossOrigin(origins = "https://localhost:8100")
@RestController
@RequestMapping("/auth/")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    // private UserRepository userRepository;

    //@Autowired
    // private UserRepository userRepository;
    //private UserAndCTRepository userctRepo;

    @Autowired
    private ConfirmationTokenRepository confirmationRepository;

    @Autowired
    private EmailService emailService;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    @GetMapping("users")
     public List<User> getUsers() {
     return this.userRepository.findAll();
     }

    @GetMapping(value = "register")
    public ModelAndView displayRegistration(ModelAndView modelAndView, User user) {
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @PostMapping(value = "register")
    public ResponseEntity<User> register(@RequestBody User user) {
       User existingUser = userRepository.findUserByEmail(user.getEmail());

        if (existingUser != null) {
            return ResponseEntity.badRequest().build();
        
        } else {
//passwort encode hinzufügen

            // user = new User();
            // user.setEmail(authRequest.getEmail());
            // user.setPassword(passwordEncoder.encode(user.getPassword()));
            //user.setEmailId(user.getEmail());
            //user.setEnabled(true);
            User c = userRepository.save(user);

            //token
            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationRepository.save(confirmationToken);

            //sendeverification url
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Bitte bestätigen Sie ihre Anmeldung");
            //mailMessage.setFrom("kkumrubusra@gmail.com");
            mailMessage.setText("Bitte verifizieren Sie sich, indem sie auf den Link klicken! "
                    + "http://localhost:8443/auth/confirm?token=" +
                    confirmationToken.getConfirmationToken());

            emailService.sendEmail(mailMessage);

            System.out.println("LINK WURDE VERSENDET");

            return ResponseEntity.ok(c);

        }

    }

    @RequestMapping(value = "confirm", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView confirmUserAccount(ModelAndView modelAndView ,@RequestParam("token") String confirmationToken) {

        ConfirmationToken token = confirmationRepository.findByConfirmationToken(confirmationToken);
        User user = userRepository.findUserByEmail(token.getUser().getEmail());

                System.out.println("KEINE SCHLEIFE");


        if (user == null || user.isEnabled()) {
            System.out.println("IF - FUNKTIONIER NICHT ");

        } else {
            user.setEnabled(true);
            userRepository.save(user);

                    System.out.println("IF SCHLEIFE!! TOKEN DRINNE");


                    modelAndView.setViewName("accountVerified");
        }

        return modelAndView;

    
    // public ResponseEntity<ConfirmationToken> confirmUserAccount(@RequestParam("token") String confirmationToken) {

    //     ConfirmationToken token = confirmationRepository.findByConfirmationToken(confirmationToken);
    //     User user = userRepository.findUserByEmail(token.getUser().getEmail());

    //             System.out.println("KEINE SCHLEIFE");


    //     if (user == null || user.isEnabled()) {
    //         System.out.println("IF - FUNKTIONIER NICHT ");
    //         return ResponseEntity.badRequest().build();

    //     } else {
    //         user.setEnabled(true);
    //         userRepository.save(user);

    //                 System.out.println("IF SCHLEIFE!! TOKEN DRINNE");


    //                 return ResponseEntity.ok().build();

    //     }


        // User user = userctRepo.findByEmailIdIgnoreCase(token.getUser().getEmail());
        // user.setEnabled(false);
        // userRepository.save(user);
    }
    /*
     * @RequestMapping(value = "confirm-account", method = { RequestMethod.GET,
     * RequestMethod.POST })
     * public User confirmUserAccount(User user, @RequestParam("token") String
     * confirmationToken) {
     * ConfirmationToken token =
     * confirmationRepository.findByConfirmationToken(confirmationToken);
     * 
     * if (token != null) {
     * userctRepo.findByEmailIdIgnoreCase(token.getUser().getEmailId());
     * user.setEmailId("NEU");
     * user.setEnabled(true);
     * userRepository.save(user);
     * }
     * return user;
     * }
     

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        if (user.isEnabled() == true) {

            user.setEmailId("hallo");
        } else {

            user.setEmailId("nope");

        }

        userRepository.save(user);
        return ResponseEntity.ok().build();
    }*/

    /*
     * @PostMapping(value = "register")
     * public ModelAndView registerUser(ModelAndView modelAndView, User user) {
     * 
     * User existingUser =
     * userRepository.findByEmailIdIgnoreCase(user.getEmailId());
     * if (existingUser != null) {
     * modelAndView.addObject("message", "This email already exists!");
     * modelAndView.setViewName("error");
     * } else {
     * userRepository.save(user);
     * 
     * ConfirmationToken confirmationToken = new ConfirmationToken(user);
     * 
     * confirmationRepository.save(confirmationToken);
     * 
     * SimpleMailMessage mailMessage = new SimpleMailMessage();
     * 
     * mailMessage.setTo(user.getEmailId());
     * mailMessage.setSubject("ConfirmationMail");
     * mailMessage.setFrom("kkumrubusra@gmail.com");
     * mailMessage.setText("please verify yourself by clicking the following link:"
     * + "http://localhost:8080/confirm-account?token=" +
     * confirmationToken.getConfirmationToken());
     * 
     * emailService.sendEmail(mailMessage);
     * 
     * modelAndView.addObject("emailId", user.getEmailId());
     * 
     * modelAndView.setViewName("completed!!!");
     * 
     * }
     * 
     * return modelAndView;
     * 
     * }
     * 
     * 
     * @RequestMapping(value = "confirm-account", method = { RequestMethod.GET,
     * RequestMethod.POST })
     * public ModelAndView confirmUserAccount(ModelAndView
     * modelAndView, @RequestParam("token") String confirmationToken) {
     * ConfirmationToken token =
     * confirmationRepository.findByConfirmationToken(confirmationToken);
     * 
     * if (token != null) {
     * User user =
     * userRepository.findByEmailIdIgnoreCase(token.getUser().getEmailId());
     * user.setEnabled(true);
     * userRepository.save(user);
     * modelAndView.setViewName("accountVerified");
     * } else {
     * modelAndView.addObject("message", "The link is invalid or broken!");
     * modelAndView.setViewName("error");
     * }
     * 
     * return modelAndView;
     * }
     */

    /*
     * public AuthController(UserRepository userRepository, PasswordEncoder
     * passwordEncoder,
     * AuthenticationManager authenticationManager, JwtTokenProvider
     * jwtTokenProvider) {
     * this.userRepository = userRepository;
     * this.passwordEncoder = passwordEncoder;
     * this.authenticationManager = authenticationManager;
     * this.jwtTokenProvider = jwtTokenProvider;
     * }
     * 
     * // get all users
     * 
     * @GetMapping("users")
     * public List<User> getUsers() {
     * return this.userRepository.findAll();
     * }
     * 
     * @PostMapping(value = "/register")
     * public ResponseEntity<User> register(@RequestBody AuthRequest authRequest) {
     * Optional<User> userOptional =
     * userRepository.findUserByEmail(authRequest.getEmail());
     * 
     * if (userOptional.isPresent()) {
     * return ResponseEntity.badRequest().build();
     * }
     * 
     * User user = new User();
     * user.setEmail(authRequest.getEmail());
     * user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
     * User created = userRepository.save(user);
     * return ResponseEntity.ok(created);
     * }
     * 
     * @PostMapping(value = "/login")
     * public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
     * Authentication authentication = authenticationManager.authenticate(
     * new UsernamePasswordAuthenticationToken(
     * authRequest.getEmail(),
     * authRequest.getPassword()));
     * final String token = jwtTokenProvider.generateToken(authentication);
     * return ResponseEntity.ok(new JwtResponse(token));
     * }
     */
}
