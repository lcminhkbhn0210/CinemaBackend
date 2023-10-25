package com.example.cinema.management.controllers;

import com.example.cinema.management.dto.LoginRequestDTO;
import com.example.cinema.management.dto.LoginResponseDTO;
import com.example.cinema.management.dto.RegisterResponseDTO;
import com.example.cinema.management.dto.RegisterRequestDTO;
import com.example.cinema.management.services.AuthenticationService;
import com.example.cinema.management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginRequestDTO loginRequestDto){
        return new ResponseEntity<>(authenticationService.login(loginRequestDto), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> registerUser(@RequestBody RegisterRequestDTO registerRequestDto){
        return new ResponseEntity<>(authenticationService.register(registerRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestParam("code") String code){
        return new ResponseEntity<>(userService.verifyUser(code), HttpStatus.OK);
    }
}
