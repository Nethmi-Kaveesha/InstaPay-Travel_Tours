package com.example.InstaPay_Travel_Tours.service.impl;


import com.example.InstaPay_Travel_Tours.model.Review;
import com.example.InstaPay_Travel_Tours.repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }
}
