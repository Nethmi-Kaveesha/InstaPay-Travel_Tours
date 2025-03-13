package com.example.InstaPay_Travel_Tours.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookingID")
    private int bookingID;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;  // Assuming User is another entity representing the systemuser table

    @ManyToOne
    @JoinColumn(name = "TourID", nullable = false)
    private Tour tour;  // Assuming Tour is another entity representing the Tours table

    @Column(name = "BookingDate", nullable = false)
    private String bookingDate;

    @Column(name = "NumberOfPeople", nullable = false)
    private int numberOfPeople;

    @Column(name = "TotalAmount", nullable = false)
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "BookingStatus", nullable = false)
    private BookingStatus bookingStatus;

    @Column(name = "CreatedAt", nullable = false)
    private String createdAt;

    public Booking() {
    }

    public Booking(int bookingID, User user, Tour tour, String bookingDate, int numberOfPeople, BigDecimal totalAmount, BookingStatus bookingStatus, String createdAt) {
        this.bookingID = bookingID;
        this.user = user;
        this.tour = tour;
        this.bookingDate = bookingDate;
        this.numberOfPeople = numberOfPeople;
        this.totalAmount = totalAmount;
        this.bookingStatus = bookingStatus;
        this.createdAt = createdAt;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    // Enum for BookingStatus
    public enum BookingStatus {
        PENDING,
        CONFIRMED,
        CANCELED
    }
}
