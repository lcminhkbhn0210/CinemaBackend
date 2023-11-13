package com.example.cinema.management.services;


import com.example.cinema.management.dto.LoginRequestDTO;
import com.example.cinema.management.model.Employee;
import com.example.cinema.management.model.User;

import java.util.List;

public interface UserService {
    String checkLogin(LoginRequestDTO loginRequestDto);
    User addUser(User user);

    String deleteUser(long id);
    User updateUser(User user, long id);

    List<User> getAllUserByType(String type);

    User getUserById(long id);

    User addEmployee(User user);

    User updateEmployee(Employee employee, long id);

    String verifyUser(String code);

    List<User> getAllUser();

    String getNameByUserId(long userId);
}
