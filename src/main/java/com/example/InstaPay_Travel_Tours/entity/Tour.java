package com.example.InstaPay_Travel_Tours.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

@Entity
@Table(name = "tours")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tourid")
    private int tourID;

    @Column(name = "tour_name", nullable = false)
    @NotEmpty(message = "Tour name cannot be empty")
    private String tourName;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "location", nullable = false)
    @NotEmpty(message = "Location cannot be empty")
    private String location;

    @Column(name = "duration", nullable = false)
    @Min(value = 1, message = "Duration must be at least 1 day")
    private int duration;

    @Column(name = "price", nullable = false)
    @Min(value = 0, message = "Price cannot be negative")
    private double price;

    @Column(name = "tour_type", nullable = false)
    @NotEmpty(message = "Tour type cannot be empty")
    private String tourType;

    @Column(name = "available_seats")
    @Min(value = 0, message = "Available seats cannot be negative")
    private int availableSeats;

    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "images", length = 255, nullable = true)
    private String images;

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

    public Tour() {
    }

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
