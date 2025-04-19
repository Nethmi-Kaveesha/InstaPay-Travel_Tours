package com.example.InstaPay_Travel_Tours.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Pay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment paymentId
    private Long paymentId;

    private String cardNumber;
    private String cardName;
    private String expiry;
    private String cvv;
    private Double amount;
    private LocalDate paymentDate = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}
