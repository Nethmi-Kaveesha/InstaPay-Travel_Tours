package com.example.InstaPay_Travel_Tours.service;

import com.example.InstaPay_Travel_Tours.entity.Booking;
import com.example.InstaPay_Travel_Tours.entity.Payment;
import com.example.InstaPay_Travel_Tours.repo.BookingRepository;
import com.example.InstaPay_Travel_Tours.repo.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository; // Make sure you have this repository

    public Payment processPayment(int bookingId, String paymentMethod, String cardNumber, String expiryDate, String cvv) {
        // Fetch the booking by bookingId
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // Create the payment instance
        Payment payment = new Payment();

        // Set the payment details
        payment.setBooking(booking); // Set the fetched booking
        payment.setPaymentMethod(paymentMethod);
        payment.setCardNumber(cardNumber);
        payment.setCardExpiry(expiryDate);
        payment.setCardCvv(cvv);

        // Save the payment to the database
        return paymentRepository.save(payment);
    }
}
