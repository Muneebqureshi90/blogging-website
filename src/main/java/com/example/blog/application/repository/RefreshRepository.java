package com.example.blog.application.repository;

import com.example.blog.application.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshRepository extends JpaRepository<RefreshToken,String> {


    Optional<RefreshToken> findByRefreshToken(String token);
}
