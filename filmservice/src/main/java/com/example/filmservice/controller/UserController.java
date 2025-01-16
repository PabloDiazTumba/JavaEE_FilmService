package com.example.filmservice.controller;

import com.example.filmservice.model.User;
import com.example.filmservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Registreringssida - GET
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());  // Skickar en tom User till registreringssidan
        return "register"; // Thymeleaf-sidan för registrering
    }

    // Registrera användare - POST
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        try {
            // Registrera användaren genom service
            userService.registerUser(user.getUsername(), user.getPassword(), user.getEmail());
            return "redirect:/login"; // Gå till login-sidan efter lyckad registrering
        } catch (IllegalArgumentException e) {
            // Om användarnamnet redan finns, visa ett felmeddelande
            model.addAttribute("error", "Användarnamnet finns redan");
            return "register"; // Visa registreringssidan igen med felmeddelandet
        }
    }

    // Login-sida - GET
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Thymeleaf-sidan för login
    }

    // Logout-sida - GET
    @GetMapping("/logout")
    public String logout() {
        // Tömmer säkerhetskonteksten vid logout
        return "redirect:/login"; // Omdirigerar till login efter logout
    }
}