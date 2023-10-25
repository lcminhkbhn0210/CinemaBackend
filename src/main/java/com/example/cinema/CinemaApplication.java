package com.example.cinema;


import com.example.cinema.management.repositories.RoleRepository;
import com.example.cinema.management.repositories.UserRepository;
import com.example.cinema.management.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
@EnableAsync
public class CinemaApplication implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private FilmRoomService filmRoomService;
    @Autowired
    private ShowTimesService showTimesService;
    @Autowired
    private TicketService ticketService;



    public CinemaApplication(RoleRepository roleRepository, UserService userService) {
        super();
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    public static void main(String[] args) {

         SpringApplication.run(CinemaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

     }
}
