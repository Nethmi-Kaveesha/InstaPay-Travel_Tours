package com.example.InstaPay_Travel_Tours.service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    // EmailService.java
    public void sendEmail(String to, String subject, String text) throws MessagingException {
        String from = "kaveefernando2003@gmail.com";  // Gmail email address
        String password = "poyv hjlr fzbl zhjf"; // Gmail app password or normal password

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.connectiontimeout", "10000"); // 10 seconds timeout
        properties.put("mail.smtp.timeout", "10000");           // 10 seconds timeout
        properties.put("mail.smtp.writetimeout", "10000");      // 10 seconds timeout

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(text);

        Transport.send(message);
    }


}
