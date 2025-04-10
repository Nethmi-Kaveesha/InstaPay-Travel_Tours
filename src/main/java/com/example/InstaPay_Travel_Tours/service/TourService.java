package com.example.InstaPay_Travel_Tours.service;

import com.example.InstaPay_Travel_Tours.dto.TourDTO;
import com.example.InstaPay_Travel_Tours.entity.Tour;

import java.time.LocalDate;
import java.util.List;

public interface TourService {

    void addTour(TourDTO tourDTO);

    List<Tour> findAvailableTours(LocalDate startDate, LocalDate endDate);

    List<TourDTO> getAllTours();

    TourDTO getTourById(int tourID);

    void updateTour(TourDTO tourDTO);

    void deleteTour(int tourID);
    public List<TourDTO> searchToursByName(String keyword);

}
