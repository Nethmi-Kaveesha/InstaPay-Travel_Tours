package com.example.InstaPay_Travel_Tours.service.impl;

import com.example.InstaPay_Travel_Tours.dto.ReviewDTO;
import com.example.InstaPay_Travel_Tours.entity.Review;
import com.example.InstaPay_Travel_Tours.repo.ReviewRepository;
import com.example.InstaPay_Travel_Tours.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addReview(ReviewDTO reviewDTO) {
        if (reviewRepo.existsById(reviewDTO.getReviewid())) {
            throw new RuntimeException("Review already exists");
        }
        reviewRepo.save(modelMapper.map(reviewDTO, Review.class));
    }

    @Override
    public List<ReviewDTO> getAllReviews() {
        return modelMapper.map(reviewRepo.findAll(),
                new TypeToken<List<ReviewDTO>>() {}.getType());
    }


    @Override
    public void updateReview(ReviewDTO reviewDTO) {
        if (reviewRepo.existsById(reviewDTO.getReviewid())) {
            reviewRepo.save(modelMapper.map(reviewDTO, Review.class));
        } else {
            throw new RuntimeException("Review does not exist");
        }
    }

    @Override
    public void deleteReview(int reviewId) {
        reviewRepo.deleteById(reviewId);
    }
}
