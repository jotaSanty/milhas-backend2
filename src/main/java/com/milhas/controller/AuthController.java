package com.milhas.controller;

import com.milhas.entity.User;
import com.milhas.security.JwtProvider;
import com.milhas.service.AuthService;
import com.milhas.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional; // Adicionado

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;

    public AuthController(UserService userService, AuthService authService, PasswordEncoder encoder, JwtProvider jwtProvider){
        this.userService = userService;
        this.authService = authService;
        this.encoder = encoder;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email already in use"));
        }

        // --- ADICIONE ESTAS LINHAS ---
        // Pega a senha original, criptografa com o encoder e define de volta no usuário
        String passwordCriptografada = encoder.encode(user.getPassword());
        user.setPassword(passwordCriptografada);
        // -----------------------------

        User created = userService.createUser(user);
        String token = jwtProvider.generateToken(created.getEmail());

        return ResponseEntity.ok(Map.of("token", token, "user", created));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        // CORREÇÃO: 'var' trocado por 'String'
        String email = body.get("email");
        String pass = body.get("password");
        // CORREÇÃO: 'var' trocado por 'Optional<String>'
        Optional<String> opt = authService.login(email, pass);
        return opt.map(t -> ResponseEntity.ok(Map.of("token", t)))
                .orElseGet(() -> ResponseEntity.status(401).body(Map.of("error", "Invalid credentials")));
    }
}