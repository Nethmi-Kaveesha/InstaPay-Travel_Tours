package com.example.InstaPay_Travel_Tours.controller;

import com.example.InstaPay_Travel_Tours.dto.TourDTO;
import com.example.InstaPay_Travel_Tours.entity.Tour;
import com.example.InstaPay_Travel_Tours.repo.TourRepository;
import com.example.InstaPay_Travel_Tours.service.BookingService;
import com.example.InstaPay_Travel_Tours.service.TourService;
import com.example.InstaPay_Travel_Tours.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/tours")
@CrossOrigin(origins = "http://localhost:3000")
public class TourController {

    @Autowired
    private TourService tourService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private TourRepository tourRepository;

    // In your TourController.java
    @PutMapping("/api/v1/tours/{id}")
    public ResponseEntity<Tour> updateTourSeats(@PathVariable Integer id, @RequestBody Tour updatedTour) {
        Optional<Tour> optionalTour = tourRepository.findById(id);
        if (optionalTour.isPresent()) {
            Tour tour = optionalTour.get();
            tour.setAvailableSeats(updatedTour.getAvailableSeats());
            tourRepository.save(tour);
            return ResponseEntity.ok(tour);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping("/search")
    public ResponseEntity<List<TourDTO>> searchTours(@RequestParam(required = false) String keyword) {
        List<TourDTO> tours;
        if (keyword != null && !keyword.trim().isEmpty()) {
            // If a keyword is provided, search for tours by name
            tours = tourService.searchToursByName(keyword);
        } else {
            // If no keyword is provided, return all tours
            tours = tourService.getAllTours();
        }
        return new ResponseEntity<>(tours, HttpStatus.OK);
    }
    // Save a new tour
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveTour(@RequestBody TourDTO tourDTO) {
        try {
            tourService.addTour(tourDTO);
            return new ResponseUtil(201, "Tour Package Saved", null);
        } catch (Exception e) {
            return new ResponseUtil(400, "Error: " + e.getMessage(), null);
        }
    }

    // Get all tours
    @GetMapping("/getAll")
    public List<TourDTO> getAllTours() {
        return tourService.getAllTours();
    }

    // Get a specific tour by ID
    @GetMapping("/get/{tourID}")
    public TourDTO getTourById(@PathVariable("tourID") int tourID) {
        return tourService.getTourById(tourID);
    }

    // Update an existing tour
    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateTour(@RequestBody TourDTO tourDTO) {
        try {
            tourService.updateTour(tourDTO);
            return new ResponseUtil(200, "Tour Package Updated", null);
        } catch (Exception e) {
            return new ResponseUtil(400, "Error: " + e.getMessage(), null);
        }
    }

    // Delete a tour by ID
    @DeleteMapping("/delete/{tourID}")
    public ResponseUtil deleteTour(@PathVariable("tourID") int tourID) {
        try {
            tourService.deleteTour(tourID);
            return new ResponseUtil(200, "Tour Package Deleted", null);
        } catch (Exception e) {
            return new ResponseUtil(400, "Error: " + e.getMessage(), null);
        }
    }
}
