package com.example.InstaPay_Travel_Tours.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class BookingDTO {

    private int bookingId;
    private LocalDate bookingDate;
    private double totalPrice;
    private UUID userId;
    private List<BookingDetailDTO> bookingDetails;

    public BookingDTO() {
    }

    public BookingDTO(int bookingId, LocalDate bookingDate, double totalPrice, UUID userId, List<BookingDetailDTO> bookingDetails) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.bookingDetails = bookingDetails;
    }

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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public List<BookingDetailDTO> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(List<BookingDetailDTO> bookingDetails) {
        this.bookingDetails = bookingDetails;
    }
}
