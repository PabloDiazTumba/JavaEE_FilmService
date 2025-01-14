package com.example.filmservice.controller;

import com.example.filmservice.model.User;
import com.example.filmservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Registreringssida
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register"; // HTML-formuläret för registrering
    }

    // Registrera användare
    @PostMapping("/register")
    public String registerUser(String username, String password, String email, Model model) {
        try {
            userService.registerUser(username, password, email);
            return "redirect:/login"; // Om registreringen lyckas, gå till login
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Användarnamn finns redan");
            return "register"; // Om användarnamnet redan finns, visa felmeddelande
        }
    }

    // Login-sida
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // HTML-formuläret för inloggning
    }

    // Logout-sida
    @GetMapping("/logout")
    public String logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            SecurityContextHolder.clearContext(); // Töm säkerhetskonteksten vid logout
        }
        return "redirect:/login"; // Gå till login-sidan efter logout
    }
}