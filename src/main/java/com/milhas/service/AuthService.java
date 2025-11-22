package com.milhas.service;

import com.milhas.security.JwtProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder encoder;

    public AuthService(UserService userService, JwtProvider jwtProvider, PasswordEncoder encoder) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
        this.encoder = encoder;
    }

    public Optional<String> login(String email, String rawPassword) {
        var uOpt = userService.findByEmail(email);
        if (uOpt.isPresent() && encoder.matches(rawPassword, uOpt.get().getPassword())) {
            return Optional.of(jwtProvider.generateToken(email));
        }
        return Optional.empty();
    }
}
