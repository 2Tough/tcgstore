package dev.twotough.springlab.tcgstore.service;

import dev.twotough.springlab.tcgstore.model.User;
import dev.twotough.springlab.tcgstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        // En producción: validar, encriptar password, dto -> entidad
        return userRepository.save(user);
    }

    public Optional<User> getByUsername(String username) {

        return userRepository.findByUsername(username);

    }

    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }
}