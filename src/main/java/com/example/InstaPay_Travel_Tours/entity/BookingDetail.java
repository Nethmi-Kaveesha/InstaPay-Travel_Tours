package com.example.InstaPay_Travel_Tours.entity;

import jakarta.persistence.*;

@Entity
public class BookingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;  // Assuming you have a Booking entity

    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;  // Assuming you have a Tour entity

    private double quantity;
    private double price;
    private double total;

    public BookingDetail() {
    }

    public BookingDetail(int id, Booking booking, Tour tour, double quantity, double price, double total) {
        this.id = id;
        this.booking = booking;
        this.tour = tour;
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

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
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
