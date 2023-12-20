package com.example.blog.application.security;

import com.example.blog.application.payload.UserDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class JwtAuthResponse {
    private String token;

    private UserDto user;
//    This is for refresh Token
    private String refreshToken;
//    private String userName;
}
