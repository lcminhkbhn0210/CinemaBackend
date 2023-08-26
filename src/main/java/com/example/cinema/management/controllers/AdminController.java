package com.example.cinema.management.controllers;


import com.example.cinema.management.model.Employee;
import com.example.cinema.management.model.User;
import com.example.cinema.management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserInforById(@PathVariable("id") long id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/employees/{type}")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllEmployee(@PathVariable("type") String type){
        return userService.getAllUserByType(type);
    }

    @PostMapping("/employee")
    public ResponseEntity<User> addUser(@RequestBody Employee employee){
        return new ResponseEntity<>(userService.addEmployee(employee),HttpStatus.CREATED);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id){
        return new ResponseEntity<>(userService.deleteUser(id),HttpStatus.OK);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<User> updateEployee(@RequestBody Employee employee, @PathVariable("id") long id){
        return new ResponseEntity<>(userService.updateEmployee(employee,id),HttpStatus.ACCEPTED);
    }

    @GetMapping("/customers/{type}")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllCustomer(@PathVariable("type") String type){
        return userService.getAllUserByType(type);
    }

}
