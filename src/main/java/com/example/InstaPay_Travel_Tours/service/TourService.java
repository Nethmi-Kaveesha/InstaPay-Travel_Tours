package com.example.InstaPay_Travel_Tours.service;

import com.example.InstaPay_Travel_Tours.dto.TourDTO;

import java.util.List;

public interface TourService {
    void addTour(TourDTO tourDTO);

    List<TourDTO> getAllTours();

    void updateTour(TourDTO tourDTO);

    void deleteTour(int tourID);
}
