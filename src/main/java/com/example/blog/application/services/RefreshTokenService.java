package com.example.blog.application.services;

import com.example.blog.application.entity.RefreshToken;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(String userName);
    RefreshToken verifyRefreshToken(String refreshToken);

}
