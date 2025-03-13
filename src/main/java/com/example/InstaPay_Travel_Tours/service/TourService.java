package com.example.InstaPay_Travel_Tours.service;

import com.example.InstaPay_Travel_Tours.dto.TourDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface TourService {
    void addTourWithImage(String tourName, String location, String duration, double price, String tourType,
                          int availableSeats, String startDate, String endDate, String description, MultipartFile imageFile) throws IOException;

    void addTour(TourDTO tourDTO);

    List<TourDTO> getAllTours();

    Optional<TourDTO> getTourById(int tourId);

    void updateTour(TourDTO tourDTO);

    void deleteTour(int tourID);
}
