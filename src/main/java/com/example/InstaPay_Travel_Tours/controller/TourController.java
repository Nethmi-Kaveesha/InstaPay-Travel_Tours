package com.example.InstaPay_Travel_Tours.controller;

import com.example.InstaPay_Travel_Tours.dto.TourDTO;
import com.example.InstaPay_Travel_Tours.service.TourService;
import com.example.InstaPay_Travel_Tours.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tours")
@CrossOrigin(origins = "http://localhost:3000")
public class TourController {

    @Autowired
    private TourService tourService;

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
