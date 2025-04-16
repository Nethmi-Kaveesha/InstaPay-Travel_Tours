package com.example.InstaPay_Travel_Tours.dto;

import java.util.UUID;

public class TransferRequest {

    private UUID userId; // The userId to identify the user making the transfer
    private Integer bookingId; // The bookingId associated with the transfer
    private String orderId; // The orderId for the transfer
    private Double amount; // The amount to be transferred

    // Getters and Setters

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
