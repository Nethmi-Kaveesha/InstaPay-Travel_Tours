package com.example.InstaPay_Travel_Tours.controller;

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

    /**
     * Endpoint to create a Stripe payment intent.
     */
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

    /**
     * Endpoint to update the payment status.
     */
    @PostMapping("/update-payment-status")
    public ResponseEntity<Map<String, String>> updatePaymentStatus(@RequestBody Map<String, Object> request) {
        try {
            String paymentId = request.get("paymentId").toString();
            String status = request.get("status").toString();
            String email = request.get("email").toString();
            Double amount = Double.parseDouble(request.get("amount").toString());
            Long tourId = Long.parseLong(request.get("tourId").toString());

            // Fetch the tour using the tour ID
            Tour tour = tourRepository.findById(Math.toIntExact(tourId))  // If tourId is Long and the repository uses Long as the ID type
                    .orElseThrow(() -> new RuntimeException("Tour not found with ID: " + tourId));



            // Save payment details
            paymentService.savePayment(paymentId, email, amount, status, tour);

            return ResponseEntity.ok(Map.of("message", "Payment status updated successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Error updating payment status: " + e.getMessage()));
        }
    }

    @GetMapping("/view")
    public ResponseEntity<List<Payment>> getAllPayments() {
        try {
            List<Payment> payments = paymentRepository.findAll();
            return ResponseEntity.ok(payments);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null); // Or return a custom error message
        }
    }

    /**
     * Endpoint to fetch all tours (for your frontend to populate the tour dropdown).
     */
    @GetMapping("/tours")
    public ResponseEntity<List<Tour>> getAllTours() {
        try {
            List<Tour> tours = tourRepository.findAll();
            return ResponseEntity.ok(tours);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null); // Or return a custom error message
        }
    }
}
