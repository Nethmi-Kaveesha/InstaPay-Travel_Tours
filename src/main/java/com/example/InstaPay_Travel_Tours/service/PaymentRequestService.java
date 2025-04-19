package com.example.InstaPay_Travel_Tours.service;

import com.example.InstaPay_Travel_Tours.entity.PaymentRequest;
import com.example.InstaPay_Travel_Tours.repo.PaymentRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentRequestService {

    @Autowired
    private PaymentRequestRepository paymentRequestRepository;

    public PaymentRequest savePayment(PaymentRequest paymentRequest) {
        return paymentRequestRepository.save(paymentRequest);
    }
}
