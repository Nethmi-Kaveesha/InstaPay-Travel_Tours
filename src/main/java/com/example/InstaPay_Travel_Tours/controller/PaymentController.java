package com.example.InstaPay_Travel_Tours.controller;

import com.example.InstaPay_Travel_Tours.dto.PaymentRequest;
import com.example.InstaPay_Travel_Tours.entity.Payment;
import com.example.InstaPay_Travel_Tours.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Endpoint to process payment
    @PostMapping("/process")
    public ResponseEntity<?> processPayment(@RequestBody PaymentRequest paymentRequest) {
        // Call the service to process payment
        Payment payment = paymentService.processPayment(
                paymentRequest.getBookingId(),
                paymentRequest.getPaymentMethod(),
                paymentRequest.getCardNumber(),
                paymentRequest.getExpiryDate(),
                paymentRequest.getCvv()
        );

        return ResponseEntity.ok(payment);
    }
}
