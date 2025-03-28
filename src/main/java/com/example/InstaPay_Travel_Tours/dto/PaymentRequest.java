package com.example.InstaPay_Travel_Tours.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private int bookingId;
    private String paymentMethod;
    private String cardNumber;
    private String expiryDate;
    private String cvv;

    // Getters and Setters
}
