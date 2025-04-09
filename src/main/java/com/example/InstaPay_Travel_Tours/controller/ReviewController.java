package com.example.InstaPay_Travel_Tours.controller;

import com.example.InstaPay_Travel_Tours.dto.ReviewDTO;
import com.example.InstaPay_Travel_Tours.model.Review;
import com.example.InstaPay_Travel_Tours.repo.ReviewRepository;
import com.example.InstaPay_Travel_Tours.service.impl.ReviewServiceImpl;
import com.example.InstaPay_Travel_Tours.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reviews")
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewServiceImpl reviewService;

    // POST method to save review
    @PostMapping
    public void saveReview(@RequestBody Review review) {
        // Print review to debug
        System.out.println("Review data received: " + review);
        reviewRepository.save(review);  // Save to the database
    }

    // GET method to retrieve reviews
    @GetMapping
    public List<Review> getAllReview() {
        return reviewRepository.findAll();
    }

    @PostMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveReview(@RequestBody ReviewDTO reviewDTO) {
        reviewService.addReview(reviewDTO);
        return new ResponseUtil(201, "Review Saved", null);
    }

    @GetMapping("getAll")
    public List<ReviewDTO> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @PutMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateReview(@RequestBody ReviewDTO reviewDTO) {
        reviewService.updateReview(reviewDTO);
        return new ResponseUtil(200, "Review Updated", null);
    }

    @DeleteMapping("delete/{reviewID}")
    public ResponseUtil deleteReview(@PathVariable("reviewID") Long reviewID) {
        reviewService.deleteReview(reviewID);
        return new ResponseUtil(200, "Review Deleted", null);
    }
}
