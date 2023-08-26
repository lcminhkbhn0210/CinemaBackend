package com.example.cinema.management.services;

import com.example.cinema.management.model.User;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface EmailService {
    void sendVerificationEmail(User user, String siteURL) throws MessagingException, UnsupportedEncodingException;
}
