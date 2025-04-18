package com.example.InstaPay_Travel_Tours.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PayHere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;
    private String status;
    private String transactionId;

    @Column(unique = true, nullable = false)
    private String orderId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Booking booking;
}
