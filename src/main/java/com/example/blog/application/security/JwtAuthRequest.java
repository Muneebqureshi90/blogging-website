package com.example.blog.application.security;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtAuthRequest {

    private String userName;
    private String password;

}
