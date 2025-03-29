package com.example.InstaPay_Travel_Tours.service;


import com.example.InstaPay_Travel_Tours.model.Payment;
import com.example.InstaPay_Travel_Tours.repo.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${stripe.api.secret-key}")
    private String stripeSecretKey;

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Map<String, String> createPaymentIntent(Double amount, String email) throws Exception {
        Stripe.apiKey = stripeSecretKey;

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount((long) (amount * 100)) // Convert to cents
                .setCurrency("usd")
                .setReceiptEmail(email)
                .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);

        Map<String, String> response = new HashMap<>();
        response.put("clientSecret", paymentIntent.getClientSecret());

        return response;
    }

    public void savePayment(String paymentId, String email, Double amount, String status) {
        Payment payment = new Payment();
        payment.setPaymentId(paymentId);
        payment.setEmail(email);
        payment.setAmount(amount);
        payment.setStatus(status);
        paymentRepository.save(payment);
    }
}
