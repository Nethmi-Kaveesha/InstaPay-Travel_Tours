package com.example.InstaPay_Travel_Tours.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReviewDTO {

    private int reviewid;           // Review ID
    private LocalDateTime createdAt; // Created At timestamp
    private int rating;             // Rating
    private String reviewText;      // Review Text
    private int tourid;             // Tour ID
    private int userid;            // User ID (UUID format to represent binary(16) in DTO)

    public ReviewDTO() {}

    public ReviewDTO(int reviewid, LocalDateTime createdAt, int rating, String reviewText, int tourid, int userid) {
        this.reviewid = reviewid;
        this.createdAt = createdAt;
        this.rating = rating;
        this.reviewText = reviewText;
        this.tourid = tourid;
        this.userid = userid;
    }

    // Getters and Setters
    public int getReviewid() {
        return reviewid;
    }

    public void setReviewid(int reviewid) {
        this.reviewid = reviewid;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getTourid() {
        return tourid;
    }

    public void setTourid(int tourid) {
        this.tourid = tourid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
