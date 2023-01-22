package de.beuth.starfishbook.security;

import de.beuth.starfishbook.model.User;
import de.beuth.starfishbook.repository.AuthCRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private AuthCRepository authRepository;

    public CustomUserDetailsService(AuthCRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = authRepository.findUserByEmail(email);
        // .orElseThrow(() -> new UsernameNotFoundException("Dieser Benutzer konnte
        // nicht gefunden werden."));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                getAuthority(user));
        // Collections.emptyList());
    }

    private Set getAuthority(User user) {

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRoles().toString()));

        return authorities;
    }
}
