//package com.example.InstaPay_Travel_Tours.controller;
//
//import com.example.InstaPay_Travel_Tours.dto.ReviewDTO;
//import com.example.InstaPay_Travel_Tours.service.impl.ReviewServiceImpl;
//import com.example.InstaPay_Travel_Tours.util.ResponseUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("api/v1/reviews")
//@CrossOrigin(origins = "http://localhost:63342")
//public class ReviewController {
//
//    @Autowired
//    private ReviewServiceImpl reviewService;
//
//    @PostMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseUtil saveReview(@RequestBody ReviewDTO reviewDTO) {
//        reviewService.addReview(reviewDTO);
//        return new ResponseUtil(201, "Review Saved", null);
//    }
//
//    @GetMapping("getAll")
//    public List<ReviewDTO> getAllReviews() {
//        return reviewService.getAllReviews();
//    }
//
//    @PutMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseUtil updateReview(@RequestBody ReviewDTO reviewDTO) {
//        reviewService.updateReview(reviewDTO);
//        return new ResponseUtil(200, "Review Updated", null);
//    }
//
//    @DeleteMapping("delete/{reviewID}")
//    public ResponseUtil deleteReview(@PathVariable("reviewID") String reviewID) {
//        reviewService.deleteReview(Integer.parseInt(reviewID));
//        return new ResponseUtil(200, "Review deleted", null);
//    }
//}
