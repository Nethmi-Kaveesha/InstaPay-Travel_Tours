package com.example.InstaPay_Travel_Tours.controller;

import com.example.InstaPay_Travel_Tours.dto.PaymentDTO;
import com.example.InstaPay_Travel_Tours.entity.Tour;
import com.example.InstaPay_Travel_Tours.model.Payment;
import com.example.InstaPay_Travel_Tours.repo.PaymentRepository;
import com.example.InstaPay_Travel_Tours.repo.TourRepository;
import com.example.InstaPay_Travel_Tours.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private TourRepository tourRepository;

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
            return ResponseEntity.badRequest().body(Map.of("error", "Error creating payment intent: " + e.getMessage()));
        }
    }


    @PostMapping("/update-payment-status")
    public ResponseEntity<Map<String, String>> updatePaymentStatus(@RequestBody Map<String, Object> request) {
        try {
            String paymentId = request.get("paymentId").toString();
            String status = request.get("status").toString();
            String email = request.get("email").toString();
            Double amount = Double.parseDouble(request.get("amount").toString());
            Long tourId = Long.parseLong(request.get("tourId").toString());


            Tour tour = tourRepository.findById(Math.toIntExact(tourId))
                    .orElseThrow(() -> new RuntimeException("Tour not found with ID: " + tourId));

            paymentService.savePayment(paymentId, email, amount, status, tour);

            return ResponseEntity.ok(Map.of("message", "Payment status updated successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Error updating payment status: " + e.getMessage()));
        }
    }


    @GetMapping("/tours")
    public ResponseEntity<List<Tour>> getAllTours() {
        try {
            List<Tour> tours = tourRepository.findAll();
            return ResponseEntity.ok(tours);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/payment-details/{paymentId}")
    public ResponseEntity<Payment> getPaymentDetails(@PathVariable String paymentId) {
        try {
            Payment payment = paymentRepository.findById(Long.valueOf(paymentId))
                    .orElseThrow(() -> new RuntimeException("Payment not found with ID: " + paymentId));
            return ResponseEntity.ok(payment);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/view")
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();

        List<PaymentDTO> dtos = payments.stream()
                .map(p -> new PaymentDTO(
                        p.getId(),
                        p.getEmail(),
                        p.getPaymentId(),
                        p.getStatus(),
                        p.getAmount(),
                        p.getTour() != null ? p.getTour().getTourName() : "N/A"
                ))
                .toList();

        return ResponseEntity.ok(dtos);
    }


}
