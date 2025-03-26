package com.example.InstaPay_Travel_Tours.controller;

import com.example.InstaPay_Travel_Tours.model.Review;
import com.example.InstaPay_Travel_Tours.repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    // POST method to save review
    @PostMapping
    public void saveReview(@RequestBody Review review) {
        // Print review to debug
        System.out.println("Review data received: " + review);
        reviewRepository.save(review);  // Save to the database
    }

    // GET method to retrieve reviews
    @GetMapping
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
}
