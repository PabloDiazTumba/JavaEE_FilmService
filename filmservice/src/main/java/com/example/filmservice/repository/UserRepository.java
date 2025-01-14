package com.example.filmservice.repository;

import com.example.filmservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Hitta en användare baserat på användarnamn (kan användas för login)
    Optional<User> findByUsername(String username);
}