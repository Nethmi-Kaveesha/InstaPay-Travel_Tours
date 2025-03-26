//package com.example.InstaPay_Travel_Tours.service.impl;
//
//import com.example.InstaPay_Travel_Tours.entity.User;
//import com.example.InstaPay_Travel_Tours.repo.BookingRepository;
//import com.example.InstaPay_Travel_Tours.repo.PaymentRepository;
//import com.example.InstaPay_Travel_Tours.repo.UserRepository;
//import jakarta.transaction.Transactional;
//import com.example.InstaPay_Travel_Tours.dto.PaymentDTO;
//import com.example.InstaPay_Travel_Tours.dto.BookingDTO;
//import com.example.InstaPay_Travel_Tours.entity.Booking;
//import com.example.InstaPay_Travel_Tours.entity.Payment;
//
//import com.example.InstaPay_Travel_Tours.service.PlaceBookingService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.UUID;
//
//@Service
//@Transactional
//public class PlaceBookingServiceImpl implements PlaceBookingService {
//
//    @Autowired
//    private BookingRepository bookingRepo;
//
//    @Autowired
//    private PaymentRepository paymentRepo;
//
//    @Autowired
//    private UserRepository systemUserRepo;
//
//    @Transactional
//    public boolean addBooking(BookingDTO bookingDTO, PaymentDTO paymentDTO) {
//        try {
//            UUID userId = UUID.fromString(bookingDTO.getUserId());
//            User systemUser = systemUserRepo.findById(userId)
//                    .orElseThrow(() -> new RuntimeException("User not found"));
//
//            Booking booking = new Booking();
//
//            LocalDateTime bookingDate = bookingDTO.getBookingDate();
//            if (bookingDate == null) {
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//                bookingDate = LocalDateTime.parse(bookingDTO.getBookingDate().toString(), formatter);
//            }
//            booking.setBookingDate(String.valueOf(bookingDate));
//
//            booking.setTotalAmount(new BigDecimal(bookingDTO.getTotalAmount()));
//
//            booking.setUser(systemUser);
//
//            Booking savedBooking = bookingRepo.save(booking);
//
//            Payment payment = new Payment();
//            payment.setBooking(savedBooking);
//            payment.setSystemUser(systemUser);
//            payment.setAmountPaid(paymentDTO.getAmountPaid());
//            payment.setPaymentMethod(paymentDTO.getPaymentMethod());
//            payment.setTransactionId(paymentDTO.getTransactionId());
//            payment.setPaymentStatus(paymentDTO.getPaymentStatus());
//            payment.setPaymentDate(paymentDTO.getPaymentDate());
//            payment.setReceiptUrl(paymentDTO.getReceiptUrl());
//            payment.setCreatedAt(paymentDTO.getCreatedAt());
//
//            paymentRepo.save(payment);
//
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//}
