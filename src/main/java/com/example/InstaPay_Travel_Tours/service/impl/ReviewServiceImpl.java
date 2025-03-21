//package com.example.InstaPay_Travel_Tours.service.impl;
//
//import com.example.InstaPay_Travel_Tours.dto.ReviewDTO;
//import com.example.InstaPay_Travel_Tours.entity.Review;
//import com.example.InstaPay_Travel_Tours.repo.ReviewRepository;
//import com.example.InstaPay_Travel_Tours.service.ReviewService;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.TypeToken;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.List;
//
//@Service
//public class ReviewServiceImpl implements ReviewService {
//
//    private static final Logger logger = LoggerFactory.getLogger(ReviewServiceImpl.class);
//
//    @Autowired
//    private ReviewRepository reviewRepo;
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//    @Override
//    public void addReview(ReviewDTO reviewDTO) {
//        // Check if the review already exists by user and tour combination (if applicable)
//        if (reviewRepo.existsByUserAndTour(reviewDTO.getUserid(), reviewDTO.getTourid())) {
//            logger.error("Review for tour {} by user {} already exists", reviewDTO.getTourid(), reviewDTO.getUserid());
//            throw new RuntimeException("Review for this tour already exists from this user");
//        }
//
//        reviewRepo.save(modelMapper.map(reviewDTO, Review.class));
//        logger.info("Review added successfully for tour {} by user {}", reviewDTO.getTourid(), reviewDTO.getUserid());
//    }
//
//    @Override
//    public List<ReviewDTO> getAllReviews() {
//        List<ReviewDTO> reviews = modelMapper.map(reviewRepo.findAll(), new TypeToken<List<ReviewDTO>>() {}.getType());
//        logger.info("Fetched all reviews");
//        return reviews;
//    }
//
//    @Override
//    public void updateReview(ReviewDTO reviewDTO) {
//        // Ensure the review exists before updating
//        if (reviewRepo.existsById(reviewDTO.getReviewid())) {
//            reviewRepo.save(modelMapper.map(reviewDTO, Review.class));
//            logger.info("Review updated successfully for review ID {}", reviewDTO.getReviewid());
//        } else {
//            logger.error("Review with ID {} does not exist", reviewDTO.getReviewid());
//            throw new RuntimeException("Review does not exist");
//        }
//    }
//
//    @Override
//    public void deleteReview(int reviewID) {
//        if (reviewRepo.existsById(reviewID)) {
//            reviewRepo.deleteById(reviewID);
//            logger.info("Review with ID {} deleted successfully", reviewID);
//        } else {
//            logger.error("Review with ID {} does not exist", reviewID);
//            throw new RuntimeException("Review does not exist");
//        }
//    }
//
//    @Override
//    public List<ReviewDTO> getReviewsByTour(int tourID) {
//        // Fetch reviews for the given tour ID
//        List<Review> reviews = reviewRepo.findByTourTourId(tourID);
//
//        // Convert Review entities to ReviewDTO objects and return
//        List<ReviewDTO> reviewDTOs = modelMapper.map(reviews, new TypeToken<List<ReviewDTO>>() {}.getType());
//        logger.info("Fetched reviews for tour ID {}", tourID);
//        return reviewDTOs;
//    }
//}
