package com.example.InstaPay_Travel_Tours.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingID;

    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;  // References Tour

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(name = "seats_booked", nullable = false)
    private int seatsBooked;

    @Column(name = "booking_date", nullable = false)
    private Date bookingDate;

    // Getters and Setters
}
