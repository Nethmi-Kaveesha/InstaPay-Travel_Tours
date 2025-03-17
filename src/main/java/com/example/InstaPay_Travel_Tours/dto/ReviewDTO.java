package com.example.InstaPay_Travel_Tours.dto;

import java.time.LocalDateTime;

public class ReviewDTO {
    private int reviewid;
    private int userid;
    private int tourId;
    private int rating;
    private String reviewText;
    private LocalDateTime createdAt;

    // Constructors
    public ReviewDTO() {}

    public ReviewDTO(int reviewid, int userid, int tourId, int rating, String reviewText, LocalDateTime createdAt) {
        this.reviewid = reviewid;
        this.userid = userid;
        this.tourId = tourId;
        this.rating = rating;
        this.reviewText = reviewText;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getReviewid() {
        return reviewid;
    }

    public void setReviewid(int reviewId) {
        this.reviewid = reviewId;
    }

    public int getUserId() {
        return userid;
    }

    public void setUserId(int userId) {
        this.userid = userId;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
