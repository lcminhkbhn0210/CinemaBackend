package com.example.cinema.management.services;

import org.springframework.security.core.Authentication;

public interface TokenService {

    String generateJwt(Authentication auth);
}
