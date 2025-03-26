package com.example.InstaPay_Travel_Tours.service;

import com.example.InstaPay_Travel_Tours.entity.Tour;
import com.example.InstaPay_Travel_Tours.repo.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private TourRepository tourRepository;

    public void bookTour(Integer tourId) {
        Optional<Tour> tourOptional = tourRepository.findById(Math.toIntExact(tourId));

        if (tourOptional.isPresent()) {
            Tour tour = tourOptional.get();
            if (tour.getAvailableSeats() > 0) {
                tour.setAvailableSeats(tour.getAvailableSeats() - 1); // Decrease available seats by 1
                tourRepository.save(tour); // Save the updated tour data to the database
            } else {
                throw new RuntimeException("No seats available for this tour.");
            }
        } else {
            throw new RuntimeException("Tour not found.");
        }
    }
}
