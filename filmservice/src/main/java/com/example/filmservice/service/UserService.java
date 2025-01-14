package com.example.filmservice.service;

import com.example.filmservice.model.User;
import com.example.filmservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Registrera en ny användare
    public User registerUser(String username, String password, String email) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Användarnamn finns redan");
        }

        // Skapa en ny användare och hash lösenordet
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // Lösenordet hash:as
        user.setEmail(email);

        return userRepository.save(user); // Spara användaren i databasen
    }

    // Hitta en användare baserat på användarnamn
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}