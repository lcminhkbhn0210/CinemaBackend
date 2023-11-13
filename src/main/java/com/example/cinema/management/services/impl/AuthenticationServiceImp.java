package com.example.cinema.management.services.impl;

import com.example.cinema.management.dto.*;
import com.example.cinema.management.model.Customer;
import com.example.cinema.management.model.Role;
import com.example.cinema.management.model.Type;
import com.example.cinema.management.model.User;
import com.example.cinema.management.repositories.RoleRepository;
import com.example.cinema.management.repositories.UserRepository;
import com.example.cinema.management.services.AuthenticationService;
import com.example.cinema.management.services.EmailService;
import com.example.cinema.management.services.TokenService;
import com.example.cinema.management.services.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationServiceImp implements AuthenticationService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository  userRepository;

    @Autowired
    private RoleRepository  roleRepository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;
    @Override
    public RegisterResponseDTO register(RegisterRequestDTO registerRequestDto) {
        try {
            registerRequestDto.setPassword(encoder.encode(registerRequestDto.getPassword()));
            Set<Role> roles = new HashSet<>();
            Role role = roleRepository.findByAuthority("USER").get();
            roles.add(role);
            Customer customer = new Customer();
            customer.setUsername(registerRequestDto.getUsername());
            customer.setPassword(registerRequestDto.getPassword());
            customer.setName(registerRequestDto.getName());
            customer.setAuthorities(roles);
            customer.setType(Type.CUSTOMER);
            customer.setStatus(false);
            customer.setName(registerRequestDto.getName());
            customer.setPhoneNumber(registerRequestDto.getPhonenumber());
            String randomCode = RandomStringUtils.randomAlphabetic(64);
            customer.setVerificationCode(randomCode);
            customer.setEmail(registerRequestDto.getEmail());
            User user =  userRepository.getUserByUsername(registerRequestDto.getUsername());
            if(user==null){
                emailService.sendVerificationEmail(customer,"http://localhost:8080/auth");
                return new RegisterResponseDTO("Dang ki thanh cong, vui long nhan link xac thuc trong email dang ki cua ban de kich hoat tai khoan.",userRepository.save(customer));
            }else {
                if(!user.isStatus()){
                    user.setEmail(registerRequestDto.getEmail());
                    user.setVerificationCode(randomCode);
                    emailService.sendVerificationEmail(user,"http://localhost:8080/auth");
                    return new RegisterResponseDTO("Dang ki that bai, ban da dang ki tai khoan nay, vui long xac thuc trong email.",null);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new RegisterResponseDTO("Dang ki that bai, ten tai khoan da ton tai",null);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDto) {
        try{
            Authentication auth = manager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword())
            );
            String token = tokenService.generateJwt(auth);
            User u = userRepository.getUserByUsername(loginRequestDto.getUsername());
            u.setAuthorities(roleRepository.findAllByUserId(u.getId()));
            if(u.isStatus())
                return new LoginResponseDTO(UserLoginDTO.toUserDTO(u),token,"Dang nhap thanh cong");
            else
                return new LoginResponseDTO(null,"","Dang nhap that bai, ban chua xac thuc tai khoan");
        }catch (AuthenticationException exception){
            return new LoginResponseDTO(null,"",userService.checkLogin(loginRequestDto));
        }

    }

}
