package dev.twotough.springlab.tcgstore.controller;

import dev.twotough.springlab.tcgstore.model.User;
import dev.twotough.springlab.tcgstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // TODO: Añade validación simple
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            return ResponseEntity. badRequest().build();
        }
        User saved = userService.createUser(user);
        return ResponseEntity. status(201).body(saved);  // 201 en lugar de 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return userService.getById(id)
                .map(ResponseEntity:: ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-username")
    public ResponseEntity<User> getByUsername(@RequestParam String username) {
        return userService.getByUsername(username)
                .map(ResponseEntity:: ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}