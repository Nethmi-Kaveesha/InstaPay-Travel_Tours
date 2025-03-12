package com.example.InstaPay_Travel_Tours.dto;

import java.util.Date;
import java.util.UUID;

public class TourDTO {

    private Integer tourID;
    private String tourName;
    private String description;
    private String location;
    private Integer duration;
    private Double price;
    private String tourType;
    private Integer availableSeats;
    private Date startDate;  // Date type for datetime fields
    private Date endDate;    // Date type for datetime fields
    private String images;
    private UUID tourOperatorID; // UUID for referencing the tour operator (UUID from binary(16))

    // No-arg constructor
    public TourDTO() {}

    // Constructor with all fields
    public TourDTO(Integer tourID, String tourName, String description, String location,
                   Integer duration, Double price, String tourType, Integer availableSeats,
                   Date startDate, Date endDate, String images, UUID tourOperatorID) {
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

    public UUID getTourOperatorID() {
        return tourOperatorID;
    }

    public void setTourOperatorID(UUID tourOperatorID) {
        this.tourOperatorID = tourOperatorID;
    }

    // Override toString for better logging and debugging
    @Override
    public String toString() {
        return "TourDTO{" +
                "tourID=" + tourID +
                ", tourName='" + tourName + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", duration=" + duration +
                ", price=" + price +
                ", tourType='" + tourType + '\'' +
                ", availableSeats=" + availableSeats +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", images='" + images + '\'' +
                ", tourOperatorID=" + tourOperatorID +
                '}';
    }

    // Override equals and hashCode to ensure correct comparisons
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TourDTO tourDTO = (TourDTO) o;

        if (!tourID.equals(tourDTO.tourID)) return false;
        if (!tourName.equals(tourDTO.tourName)) return false;
        if (!description.equals(tourDTO.description)) return false;
        if (!location.equals(tourDTO.location)) return false;
        if (!duration.equals(tourDTO.duration)) return false;
        if (!price.equals(tourDTO.price)) return false;
        if (!tourType.equals(tourDTO.tourType)) return false;
        if (!availableSeats.equals(tourDTO.availableSeats)) return false;
        if (!startDate.equals(tourDTO.startDate)) return false;
        if (!endDate.equals(tourDTO.endDate)) return false;
        if (!images.equals(tourDTO.images)) return false;
        return tourOperatorID.equals(tourDTO.tourOperatorID);
    }

    @Override
    public int hashCode() {
        int result = tourID.hashCode();
        result = 31 * result + tourName.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + location.hashCode();
        result = 31 * result + duration.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + tourType.hashCode();
        result = 31 * result + availableSeats.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + images.hashCode();
        result = 31 * result + tourOperatorID.hashCode();
        return result;
    }
}
