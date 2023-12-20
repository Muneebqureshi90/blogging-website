package com.example.blog.application.repository;

import com.example.blog.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

//    Create for USerDetailsService in security
    Optional<User> findByEmail(String email);
}
