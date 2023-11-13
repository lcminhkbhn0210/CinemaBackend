package com.example.cinema.management.services.impl;

import com.example.cinema.management.dto.LoginRequestDTO;
import com.example.cinema.management.exceptions.ResourceNotFoundException;
import com.example.cinema.management.model.Employee;
import com.example.cinema.management.model.Role;
import com.example.cinema.management.model.Type;
import com.example.cinema.management.model.User;
import com.example.cinema.management.repositories.RoleRepository;
import com.example.cinema.management.repositories.UserRepository;
import com.example.cinema.management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserServiceImp implements UserService, UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;
    private final UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;




    public UserServiceImp(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;

    }

    @Override
    public String checkLogin(LoginRequestDTO loginRequestDto) {
        User user = userRepository.getUserByUsername(loginRequestDto.getUsername());
        if (user != null) {
            return "Mat khau dang nhap khong dung";
        } else {
            return "Tai khoan khong ton tai";
        }
    }

    @Override
    public User addUser(User user) {
        User userdb = userRepository.getUserByUsername(user.getUsername());
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByAuthority("USER").get();
        roles.add(role);
        user.setAuthorities(roles);
        user.setType(Type.CUSTOMER);
        user.setPassword(encoder.encode(user.getPassword()));
        if (userdb == null) {
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public String deleteUser(long id) {
        userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "Id", id)
        );
        userRepository.deleteById(id);
        return "Xoa thanh cong";
    }

    @Override
    public User updateUser(User user, long id) {
        User userExitting = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "Id", id)
        );
        userExitting.setName(user.getName());
        userExitting.setType(user.getType());
        userExitting.setUsername(user.getUsername());
        userExitting.setPassword(user.getPassword());
        return userRepository.save(userExitting);
    }

    @Override
    public List<User> getAllUserByType(String type) {
        type = type.toUpperCase();
        return userRepository.getUserByType(Type.valueOf(type));
    }

    @Override
    public User getUserById(long id) {
        boolean check = userRepository.findById(id).isPresent();
        if (check) {
            return userRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public User addEmployee(User user) {
        User userdb = userRepository.getUserByUsername(user.getUsername());
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByAuthority("ADMIN").get();
        roles.add(role);
        user.setAuthorities(roles);
        user.setType(Type.EMPLOYEE);
        user.setPassword(encoder.encode(user.getPassword()));
        if (userdb == null) {
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public User updateEmployee(Employee employee, long id) {
        Employee userExitting = userRepository.findEmployeeById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "Id", id)
        );
        userExitting.setName(employee.getName());
        userExitting.setType(employee.getType());
        userExitting.setUsername(employee.getUsername());
        userExitting.setPassword(employee.getPassword());
        userExitting.setSalary(employee.getSalary());
        return userRepository.save(userExitting);
    }

    @Override
    public String verifyUser(String code) {
        User user = userRepository.findByVerificationCode(code);
        if(user==null || user.isStatus()){
            return "Xac thuc that bai";
        }else {
            user.setStatus(true);
            user.setVerificationCode(null);
            userRepository.save(user);
            return "Xac thuc thanh cong";
        }
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
        for(int i=0; i< users.size(); i++){

        }
        return users;
    }

    @Override
    public String getNameByUserId(long userId) {
        if(userRepository.findById(userId).isPresent()){
            return userRepository.findById(userId).get().getName();
        }
        return "Khong ton tai user";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("User", "username", username)
        );
    }
}
