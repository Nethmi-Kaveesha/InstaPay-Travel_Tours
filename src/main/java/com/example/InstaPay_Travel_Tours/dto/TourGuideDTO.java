package com.example.InstaPay_Travel_Tours.dto;

import java.sql.Timestamp;

public class TourGuideDTO {

    private int guideID;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String experience;
    private String languagesSpoken;
    private Timestamp createdAt;

    // Getters and Setters
    public int getGuideID() {
        return guideID;
    }

    public void setGuideID(int guideID) {
        this.guideID = guideID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getLanguagesSpoken() {
        return languagesSpoken;
    }

    public void setLanguagesSpoken(String languagesSpoken) {
        this.languagesSpoken = languagesSpoken;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "TourGuideDTO{" +
                "guideID=" + guideID +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", experience='" + experience + '\'' +
                ", languagesSpoken='" + languagesSpoken + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
