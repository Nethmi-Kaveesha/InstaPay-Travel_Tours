package com.example.InstaPay_Travel_Tours.controller;


import com.example.InstaPay_Travel_Tours.service.PaymentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }



    @PostMapping("/create-payment-intent")
    public ResponseEntity<Map<String, String>> createPaymentIntent(@RequestBody Map<String, Object> request) {
        try {
            Double amount = Double.parseDouble(request.get("amount").toString());
            String email = request.get("customerEmail").toString();
            return ResponseEntity.ok(paymentService.createPaymentIntent(amount, email));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/update-payment-status")
    public ResponseEntity<String> updatePaymentStatus(@RequestBody Map<String, Object> request) {
        String paymentId = request.get("paymentId").toString();
        String status = request.get("status").toString();
        String email = request.get("email").toString();
        Double amount = Double.parseDouble(request.get("amount").toString());

        paymentService.savePayment(paymentId, email, amount, status);
        return ResponseEntity.ok("Payment saved successfully");
    }
}
