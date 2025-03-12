package com.example.InstaPay_Travel_Tours.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tours")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tourid")  // Matching the column name in the database
    private int tourID;

    @Column(name = "tour_name")  // Matching the column name in the database
    private String tourName;

    @Column(name = "description")  // Column name remains the same
    private String description;

    @Column(name = "location")  // Column name remains the same
    private String location;

    @Column(name = "duration")  // Column name remains the same
    private int duration;

    @Column(name = "price")  // Column name remains the same
    private double price;

    @Column(name = "tour_type")  // Matching the column name in the database
    private String tourType;

    @Column(name = "available_seats")  // Matching the column name in the database
    private int availableSeats;

    @Column(name = "start_date")  // Matching the column name in the database
    @Temporal(TemporalType.TIMESTAMP)  // Ensures correct date-time handling
    private Date startDate;

    @Column(name = "end_date")  // Matching the column name in the database
    @Temporal(TemporalType.TIMESTAMP)  // Ensures correct date-time handling
    private Date endDate;

    @Lob  // Use @Lob to store large binary data (like images)
    @Column(name = "images")  // Column name remains the same
    private String images;  // Store the image as a byte array

    // Constructor with fields
    public Tour(int tourID, String tourName, String description, String location, int duration, double price,
                String tourType, int availableSeats, Date startDate, Date endDate, String images) {
        this.tourID = tourID;
        this.tourName = tourName;
        this.description = description;
        this.location = location;
        this.duration = duration;
        this.price = price;
        this.tourType = tourType;
        this.availableSeats = availableSeats;
        this.startDate = startDate;
        this.endDate = endDate;
        this.images = images;
    }

    // Default constructor
    public Tour() {
    }

    // Getters and Setters
    public int getTourID() {
        return tourID;
    }

    public void setTourID(int tourID) {
        this.tourID = tourID;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTourType() {
        return tourType;
    }

    public void setTourType(String tourType) {
        this.tourType = tourType;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
