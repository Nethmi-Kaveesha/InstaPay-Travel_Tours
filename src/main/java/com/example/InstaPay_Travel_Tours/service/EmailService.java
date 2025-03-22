//package com.example.InstaPay_Travel_Tours.service;
//
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmailService {
//
//    private final JavaMailSender javaMailSender;
//
//    public EmailService(JavaMailSender javaMailSender) {
//        this.javaMailSender = javaMailSender;
//    }
//
//    public void sendEmail(String to, String subject, String body) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("kaveefernando2003@gmail.com"); // Sender's email address
//        message.setTo(to); // Recipient's email address
//        message.setSubject(subject);
//        message.setText(body);
//        javaMailSender.send(message);
//    }
//}
