package com.example.InstaPay_Travel_Tours.service.impl;

import com.example.InstaPay_Travel_Tours.entity.User;
import com.example.InstaPay_Travel_Tours.repo.BookingRepository;
import com.example.InstaPay_Travel_Tours.repo.PaymentRepository;
import com.example.InstaPay_Travel_Tours.repo.UserRepository;
import jakarta.transaction.Transactional;
import com.example.InstaPay_Travel_Tours.dto.PaymentDTO;
import com.example.InstaPay_Travel_Tours.dto.BookingDTO;
import com.example.InstaPay_Travel_Tours.entity.Booking;
import com.example.InstaPay_Travel_Tours.entity.Payment;

import com.example.InstaPay_Travel_Tours.service.PlaceBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@Transactional
public class PlaceBookingServiceImpl implements PlaceBookingService {

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private PaymentRepository paymentRepo;

    @Autowired
    private UserRepository systemUserRepo;

    @Transactional
    public boolean addBooking(BookingDTO bookingDTO, PaymentDTO paymentDTO) {
        try {
            // Find user by UUID
            UUID userId = UUID.fromString(bookingDTO.getUserId());  // Assuming userId is a UUID string in the DTO
            User systemUser = systemUserRepo.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Create Booking entity and set values
            Booking booking = new Booking();

            // Convert bookingDate from String to LocalDateTime if necessary
            LocalDateTime bookingDate = bookingDTO.getBookingDate();  // If it's already LocalDateTime, you can keep it.
            if (bookingDate == null) {
                // In case the date is null or needs to be formatted, handle it
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Example format
                bookingDate = LocalDateTime.parse(bookingDTO.getBookingDate().toString(), formatter); // Handle the conversion properly
            }
            booking.setBookingDate(String.valueOf(bookingDate));

            // Set totalAmount as BigDecimal (to ensure precision)
            booking.setTotalAmount(new BigDecimal(bookingDTO.getTotalAmount()));

            // Set system user (ensure this is correct)
            booking.setUser(systemUser);

            // Save Booking
            Booking savedBooking = bookingRepo.save(booking);

            // Create Payment entity and set values
            Payment payment = new Payment();
            payment.setBooking(savedBooking);  // Set associated booking
            payment.setSystemUser(systemUser); // Set associated user
            payment.setAmountPaid(paymentDTO.getAmountPaid());
            payment.setPaymentMethod(paymentDTO.getPaymentMethod());
            payment.setTransactionId(paymentDTO.getTransactionId());
            payment.setPaymentStatus(paymentDTO.getPaymentStatus());
            payment.setPaymentDate(paymentDTO.getPaymentDate());
            payment.setReceiptUrl(paymentDTO.getReceiptUrl());
            payment.setCreatedAt(paymentDTO.getCreatedAt());

            // Save Payment
            paymentRepo.save(payment);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
