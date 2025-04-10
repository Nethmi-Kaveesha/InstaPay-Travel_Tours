package com.example.InstaPay_Travel_Tours.dto;

public class BookingDetailDTO {

    private int id;
    private int bookingId;
    private int tourId;
    private double quantity;
    private double price;
    private double total;

    public BookingDetailDTO() {
    }

    public BookingDetailDTO(int id, int bookingId, int tourId, double quantity, double price, double total) {
        this.id = id;
        this.bookingId = bookingId;
        this.tourId = tourId;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
