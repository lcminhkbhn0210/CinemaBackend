package com.example.cinema.management.services;

import com.example.cinema.management.dto.LoginRequestDTO;
import com.example.cinema.management.dto.LoginResponseDTO;
import com.example.cinema.management.dto.RegisterResponseDTO;
import com.example.cinema.management.dto.RegisterRequestDTO;

public interface AuthenticationService {

    RegisterResponseDTO register(RegisterRequestDTO registerRequestDto);
    LoginResponseDTO login(LoginRequestDTO loginRequestDto);

}
