package com.example.InstaPay_Travel_Tours.dto;

import java.util.UUID;
import java.util.Date;

public class TourDTO {

    private Integer tourID;
    private String tourName;
    private String description;
    private String location;
    private Integer duration;
    private Double price;
    private String tourType;
    private Integer availableSeats;
    private Date startDate;  // Use Date type for better handling of dates
    private Date endDate;    // Use Date type for better handling of dates
    private String images;
    private Date createdAt;  // Use Date type for better handling of dates
    private UUID tourOperatorID; // Use UUID for referencing the tour operator

    // No-arg constructor
    public TourDTO() {}

    // Constructor with all fields
    public TourDTO(Integer tourID, String tourName, String description, String location,
                   Integer duration, Double price, String tourType, Integer availableSeats,
                   Date startDate, Date endDate, String images, Date createdAt, UUID tourOperatorID) {
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
        this.createdAt = createdAt;
        this.tourOperatorID = tourOperatorID;
    }

    // Getters and Setters
    public Integer getTourID() {
        return tourID;
    }

    public void setTourID(Integer tourID) {
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTourType() {
        return tourType;
    }

    public void setTourType(String tourType) {
        this.tourType = tourType;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public UUID getTourOperatorID() {
        return tourOperatorID;
    }

    public void setTourOperatorID(UUID tourOperatorID) {
        this.tourOperatorID = tourOperatorID;
    }
}
