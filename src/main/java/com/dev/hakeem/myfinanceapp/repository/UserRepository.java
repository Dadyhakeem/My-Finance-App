package com.dev.hakeem.myfinanceapp.repository;

import com.dev.hakeem.myfinanceapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
