//package com.example.InstaPay_Travel_Tours.entity;
//
//import jakarta.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "reviews")
//public class Review {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "reviewid")
//    private int reviewid;
//
//    @ManyToOne
//    @JoinColumn(name = "userid", nullable = false)
//    private User userid;
//
//    @ManyToOne
//    @JoinColumn(name = "tourid", nullable = false)
//    private Tour tourid;
//
//    @Column(name = "rating", nullable = false)
//    private int rating;
//
//    @Column(name = "ReviewText", columnDefinition = "TEXT")
//    private String reviewText;
//
//    @Column(name = "CreatedAt", updatable = false)
//    private LocalDateTime createdAt;
//
//    @PrePersist
//    protected void onCreate() {
//        createdAt = LocalDateTime.now();
//    }
//
//    // Getters and Setters
//    public int getReviewid() {
//        return reviewid;
//    }
//
//    public void setReviewId(int reviewId) {
//        this.reviewid = reviewId;
//    }
//
//    public User getUser() {
//        return userid;
//    }
//
//    public void setUser(User user) {
//        this.userid = user;
//    }
//
//    public Tour getTour() {
//        return tourid;
//    }
//
//    public void setTour(Tour tour) {
//        this.tourid = tour;
//    }
//
//    public int getRating() {
//        return rating;
//    }
//
//    public void setRating(int rating) {
//        if (rating < 1 || rating > 5) {
//            throw new IllegalArgumentException("Rating must be between 1 and 5");
//        }
//        this.rating = rating;
//    }
//
//    public String getReviewText() {
//        return reviewText;
//    }
//
//    public void setReviewText(String reviewText) {
//        this.reviewText = reviewText;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//}
