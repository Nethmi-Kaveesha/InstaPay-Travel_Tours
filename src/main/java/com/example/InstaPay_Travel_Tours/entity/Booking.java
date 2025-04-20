package com.example.InstaPay_Travel_Tours.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Entity
@Table(name = "booking")
public class Booking {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;

    private LocalDate bookingDate;
    private double totalPrice;

    @Column(nullable = false)
    private String status = "PENDING";

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookingDetail> bookingDetails;

    public Booking() {
    }

    public Booking(int bookingId, LocalDate bookingDate, double totalPrice, String status, User user, List<BookingDetail> bookingDetails) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.totalPrice = totalPrice;
        this.status = status;
        this.user = user;
        this.bookingDetails = bookingDetails;
    }

    // Getters and setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<BookingDetail> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(List<BookingDetail> bookingDetails) {
        this.bookingDetails = bookingDetails;
    }
}
