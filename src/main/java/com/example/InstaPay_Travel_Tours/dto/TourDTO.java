package com.example.InstaPay_Travel_Tours.dto;

import java.util.Date;

public class TourDTO {

    private int tourID;
    private String tourName;
    private String description;
    private String location;
    private int duration;
    private double price;
    private String tourType;
    private int availableSeats;
    private Date startDate;
    private Date endDate;
    private String images;

    public TourDTO(int tourID, String tourName, String description, String location, int duration, double price,
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

    public TourDTO() {
    }

    // Getters and setters
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
