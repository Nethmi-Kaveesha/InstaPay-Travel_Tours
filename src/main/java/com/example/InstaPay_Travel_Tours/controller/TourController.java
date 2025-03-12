package com.example.InstaPay_Travel_Tours.controller;

import com.example.InstaPay_Travel_Tours.dto.TourDTO;
import com.example.InstaPay_Travel_Tours.entity.Tour;
import com.example.InstaPay_Travel_Tours.service.impl.TourServiceImpl;
import com.example.InstaPay_Travel_Tours.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("api/v1/tours")
@CrossOrigin(origins = "http://localhost:3000") // To allow front-end requests
public class TourController {

    @Autowired
    private TourServiceImpl tourService;

    // Endpoint to save a new tour
    @PostMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseUtil> saveTour(@RequestBody TourDTO tourDTO) {
        try {
            tourService.addTour(tourDTO);
            return new ResponseEntity<>(new ResponseUtil(201, "Tour Saved", tourDTO), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseUtil(400, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ResponseUtil(500, "Internal Server Error", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to save a new tour with an image
    @PostMapping(value = "saveWithImage", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseUtil> saveTourWithImage(@RequestParam("tourName") String tourName,
                                                          @RequestParam("location") String location,
                                                          @RequestParam("duration") String duration,
                                                          @RequestParam("price") double price,
                                                          @RequestParam("tourType") String tourType,
                                                          @RequestParam("availableSeats") int availableSeats,
                                                          @RequestParam("startDate") String startDate,
                                                          @RequestParam("endDate") String endDate,
                                                          @RequestParam("description") String description,
                                                          @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        try {
            tourService.addTourWithImage(tourName, location, duration, price, tourType, availableSeats, startDate, endDate, description, imageFile);
            return new ResponseEntity<>(new ResponseUtil(201, "Tour with Image Saved", null), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseUtil(400, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            return new ResponseEntity<>(new ResponseUtil(500, "Error uploading image", null), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (RuntimeException | ParseException e) {
            return new ResponseEntity<>(new ResponseUtil(500, "Internal Server Error", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Endpoint to update a tour with the returned TourDTO
    @PutMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseUtil> updateTour(@RequestBody TourDTO tourDTO) {
        try {
            tourService.updateTour(tourDTO);
            return new ResponseEntity<>(new ResponseUtil(200, "Tour Updated", tourDTO), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseUtil(400, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ResponseUtil(500, "Internal Server Error", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to get all tours
    @GetMapping("getAll")
    public ResponseEntity<List<TourDTO>> getAllTours() {
        List<TourDTO> tours = tourService.getAllTours();
        return new ResponseEntity<>(tours, HttpStatus.OK);
    }

    // Endpoint to delete a tour by ID
    @DeleteMapping("delete/{tourID}")
    public ResponseEntity<ResponseUtil> deleteTour(@PathVariable("tourID") String tourID) {
        try {
            // Parse the tourID to integer and delete the tour
            tourService.deleteTour(Integer.parseInt(tourID));
            return new ResponseEntity<>(new ResponseUtil(200, "Tour Deleted", null), HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(new ResponseUtil(400, "Invalid Tour ID", null), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ResponseUtil(404, e.getMessage(), null), HttpStatus.NOT_FOUND);
        }
    }
}
