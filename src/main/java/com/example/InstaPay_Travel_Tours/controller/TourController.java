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

import java.util.List;

@RestController
@RequestMapping("api/v1/tours")
@CrossOrigin(origins = "http://localhost:3000")
public class TourController {

    @Autowired
    private TourServiceImpl tourService;

    @PostMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveTour(@RequestBody TourDTO tourDTO) {
        tourService.addTour(tourDTO);
        return new ResponseUtil(201, "Tour Saved", null);
    }

    @GetMapping("getAll")
    public List<TourDTO> getAllTours() {
        return tourService.getAllTours();
    }

    @PutMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateTour(@RequestBody TourDTO tourDTO) {
        tourService.updateTour(tourDTO);
        return new ResponseUtil(200, "Tour Updated", null);
    }


    @DeleteMapping("delete/{tourID}")
    public ResponseUtil deleteTour(@PathVariable("tourID") String tourID) {
        tourService.deleteTour(Integer.parseInt(tourID));
        return new ResponseUtil(200, "Tour deleted", null);
    }
}
