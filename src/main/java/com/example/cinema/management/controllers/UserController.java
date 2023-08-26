package com.example.cinema.management.controllers;

import com.example.cinema.management.model.User;
import com.example.cinema.management.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllCustomer(@RequestParam("type") String type){
        return userService.getAllUserByType(type);
    }

}
