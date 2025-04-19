package com.example.InstaPay_Travel_Tours.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class PaymentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookingId;
    private String cardNumber;
    private String cardName;
    private String expiry;
    private String cvv;
    private Double amount;

    // Default constructor
    public PaymentRequest() {}

    // Constructor with parameters
    public PaymentRequest(Long bookingId, String cardNumber, String cardName, String expiry, String cvv, Double amount) {
        this.bookingId = bookingId;
        this.cardNumber = cardNumber;
        this.cardName = cardName;
        this.expiry = expiry;
        this.cvv = cvv;
        this.amount = amount;
    }
}
