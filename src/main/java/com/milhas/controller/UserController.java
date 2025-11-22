package com.milhas.controller;

import com.milhas.entity.User;
import com.milhas.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService s) { this.userService = s; }

    @GetMapping("/me")
    public ResponseEntity<?> me(Principal principal) {
        if (principal == null) return ResponseEntity.status(401).build();
        Optional<User> u = userService.findByEmail(principal.getName());
        return u.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(404).build());
    }

    @PutMapping("/me")
    public ResponseEntity<?> update(Principal principal, @RequestBody User payload) {
        if (principal == null) return ResponseEntity.status(401).build();
        var uOpt = userService.findByEmail(principal.getName());
        if (uOpt.isEmpty()) return ResponseEntity.status(404).build();
        var u = uOpt.get();
        u.setName(payload.getName());
        return ResponseEntity.ok(userService.update(u));
    }
}
