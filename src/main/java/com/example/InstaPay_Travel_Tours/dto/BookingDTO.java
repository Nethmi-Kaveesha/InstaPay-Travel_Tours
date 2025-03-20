package com.example.InstaPay_Travel_Tours.dto;

import java.time.LocalDateTime;
import java.util.List;

public class BookingDTO {
    private int bookingId;
    private String userId;
    private double totalAmount;
    private LocalDateTime bookingDate;
    private List<PaymentDTO> payments;

    public BookingDTO() {
    }

    public BookingDTO(int bookingId, String userId, double totalAmount, LocalDateTime bookingDate, List<PaymentDTO> payments) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.bookingDate = bookingDate;
        this.payments = payments;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }


    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public List<PaymentDTO> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentDTO> payments) {
        this.payments = payments;
    }
}
