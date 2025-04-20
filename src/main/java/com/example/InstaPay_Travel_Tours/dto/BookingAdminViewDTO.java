package com.example.InstaPay_Travel_Tours.dto;

import java.util.Date;

public class BookingAdminViewDTO {

    private int bookingId;
    private Date bookingDate;
    private double totalPrice;
    private String userId;
    private int detailId;
    private int tourId;
    private double price;
    private double quantity;
    private double total;

    public BookingAdminViewDTO() {}

    public BookingAdminViewDTO(int bookingId, Date bookingDate, double totalPrice, String userId,
                               int detailId, int tourId, double price, double quantity, double total) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.detailId = detailId;
        this.tourId = tourId;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

}
