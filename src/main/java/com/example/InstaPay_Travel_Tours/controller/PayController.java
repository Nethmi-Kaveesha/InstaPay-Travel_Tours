package com.example.InstaPay_Travel_Tours.controller;



import com.example.InstaPay_Travel_Tours.dto.PaymnetDTO;
import com.example.InstaPay_Travel_Tours.entity.PaymentRequest;

import com.example.InstaPay_Travel_Tours.repo.PaymentRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "http://localhost:3000") // Adjust this to your frontend port
public class PayController {

    @Autowired
    private PaymentRequestRepository paymentRequestRepository;

    @PostMapping("/confirm")
    public ResponseEntity<String> confirmPayment(@RequestBody PaymnetDTO payload) {
        try {
            PaymentRequest request = new PaymentRequest(
                    payload.getBookingId(),
                    payload.getCardNumber(),
                    payload.getCardName(),
                    payload.getExpiry(),
                    payload.getCvv(),
                    payload.getAmount()
            );

            paymentRequestRepository.save(request);
            return ResponseEntity.ok("Payment saved to DB");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to save payment");
        }
    }
}
