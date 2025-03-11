package com.example.InstaPay_Travel_Tours.service;

import com.example.InstaPay_Travel_Tours.dto.TourGuideDTO;

import java.util.List;

public interface TourGuideService {
    void addTourGuide(TourGuideDTO tourGuideDTO);

    List<TourGuideDTO> getAllTourGuides();

    void updateTourGuide(TourGuideDTO tourGuideDTO);

    void deleteTourGuide(int guideID);
}
