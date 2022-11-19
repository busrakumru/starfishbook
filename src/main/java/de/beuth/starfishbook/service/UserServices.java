package de.beuth.starfishbook.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import de.beuth.starfishbook.model.User;
import de.beuth.starfishbook.repository.UserRepository;
import net.bytebuddy.utility.RandomString;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailService emailService;

    public void register(User user, String siteURL)
            throws UnsupportedEncodingException, MessagingException {

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
        user.setEnabled(false);

        userRepository.save(user);

        /*
         * SimpleMailMessage mailMessage = new SimpleMailMessage();
         * mailMessage.setTo(user.getEmail());
         * mailMessage.setSubject("Complete Registration!");
         * mailMessage.setFrom("kkumrubusra@gmail.com");
         * mailMessage.setText("To confirm your account, please click here : "
         * +"http://localhost:8080/confirm-account?token="+user.getVerificationCode());
         * 
         * emailService.sendEmail(mailMessage);
         * 
         * //sendVerificationEmail(user, siteURL);
         */

        String toAddress = user.getEmail();
        String fromAdress = "kkumrubusra@gmail.com";
        String senderName = "company";
        String subject = "Please verify your registration";
        String content = "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAdress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);

    }

    private void sendVerificationEmail(User user, String siteURL)
            throws MessagingException, UnsupportedEncodingException {

        String toAddress = user.getEmail();
        String fromAdress = "kkumrubusra@gmail.com";
        String senderName = "company";
        String subject = "Please verify your registration";
        String content = "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAdress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);

    }

}
