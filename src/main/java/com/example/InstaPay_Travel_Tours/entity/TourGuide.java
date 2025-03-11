package com.example.InstaPay_Travel_Tours.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "TourGuides")
public class TourGuide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GuideID")
    private int guideID;

    @Column(name = "FullName", nullable = false)
    private String fullName;

    @Column(name = "Email", unique = true, nullable = false)
    private String email;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "Experience")
    private String experience;

    @Column(name = "LanguagesSpoken")
    private String languagesSpoken;

    @Column(name = "CreatedAt", nullable = false, updatable = false)
    @CreationTimestamp
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
        return "TourGuide{" +
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
