package com.example.InstaPay_Travel_Tours.service;

import com.example.InstaPay_Travel_Tours.dto.TourDTO;
import com.example.InstaPay_Travel_Tours.entity.Tour;

import java.time.LocalDate;
import java.util.List;

public interface TourService {

    // Method to add a new tour
    void addTour(TourDTO tourDTO);

    List<Tour> findAvailableTours(LocalDate startDate, LocalDate endDate);

    // Method to get all tours
    List<TourDTO> getAllTours();

    // Method to get a single tour by its ID
    TourDTO getTourById(int tourID);

    // Method to update an existing tour
    void updateTour(TourDTO tourDTO);

    // Method to delete a tour by its ID
    void deleteTour(int tourID);
    public List<TourDTO> searchToursByName(String keyword);

}
