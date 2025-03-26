package com.example.InstaPay_Travel_Tours.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentID;

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;  // References Booking

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @Column(name = "amount_paid", nullable = false)
    private double amountPaid;

    @Column(name = "payment_date", nullable = false)
    private Date paymentDate;

    // Getters and Setters
}
