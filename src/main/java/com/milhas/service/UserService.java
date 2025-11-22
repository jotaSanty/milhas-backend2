package com.milhas.service;

import com.milhas.entity.User;
import com.milhas.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User createUser(User u) {
        u.setPassword(encoder.encode(u.getPassword()));
        return userRepository.save(u);
    }

    public User update(User u) {
        return userRepository.save(u);
    }
}
