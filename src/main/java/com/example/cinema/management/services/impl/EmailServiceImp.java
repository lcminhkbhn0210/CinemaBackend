package com.example.cinema.management.services.impl;

import com.example.cinema.management.model.User;
import com.example.cinema.management.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class EmailServiceImp implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    @Async
    public void sendVerificationEmail(User user, String siteURL) throws MessagingException, UnsupportedEncodingException {
            String toEmail = user.getEmail();
            String fromEmail = "lcminhthpt2000@gmail.com";
            String senderName = "Minh FPT";
            String subject = "Please verify your registration";
            String content = "Dear [[name]], <br>"
                    + "Please click the link below to verify your registration:<br>"
                    + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                    + "Thank you,<br>"
                    + "Minh FPT.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setFrom(fromEmail,senderName);

        content = content.replace("[[name]]",user.getName());
        String verifyURL = siteURL + "/verify?code="  + user.getVerificationCode();

        content  = content.replace("[[URL]]",verifyURL);

        helper.setText(content,true);
        mailSender.send(message);
    }
}
