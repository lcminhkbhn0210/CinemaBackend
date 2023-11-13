package com.example.cinema.management.controllers;

import com.example.cinema.management.model.User;
import com.example.cinema.management.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping()
    public ResponseEntity<List<User>> getAllUser(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @GetMapping("/name/{userId}")
    public ResponseEntity<String> getNameByUserId(@PathVariable long userId){
        return new ResponseEntity<>(userService.getNameByUserId(userId), HttpStatus.OK);
    }

}
