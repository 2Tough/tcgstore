package dev.twotough.springlab.tcgstore.controller;

import dev.twotough.springlab.tcgstore.dto.LoginRequest;
import dev.twotough.springlab.tcgstore.dto.LoginResponse;
import dev.twotough.springlab.tcgstore.dto.RegisterRequest;
import dev.twotough.springlab.tcgstore.model.User;
import dev.twotough.springlab.tcgstore.model.enums.Role;
import dev.twotough.springlab.tcgstore.service.UserService;
import dev.twotough.springlab. tcgstore.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework. web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        // 1. Buscar usuario
        User user = userService.getByUsername(request.getUsername())
                .orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // 2. Verificar password con BCrypt
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // 3. Generar token
        String token = jwtUtil.generateToken(user.getId(), user.getUsername());

        // 4. Devolver respuesta
        return ResponseEntity.ok(new LoginResponse(token, user.getId(), user.getUsername()));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(Role.USER); // ← siempre USER, nunca ADMIN desde registro
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(user));
    }
}