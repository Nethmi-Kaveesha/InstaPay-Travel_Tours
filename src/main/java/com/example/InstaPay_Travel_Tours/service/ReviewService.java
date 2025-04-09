package com.example.InstaPay_Travel_Tours.service;

import com.example.InstaPay_Travel_Tours.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    void addReview(ReviewDTO reviewDTO);

    List<ReviewDTO> getAllReviews();

    void updateReview(ReviewDTO reviewDTO);

    void deleteReview(Long reviewId);
}
