package edu.kata.spring_boot_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import edu.kata.spring_boot_security.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
