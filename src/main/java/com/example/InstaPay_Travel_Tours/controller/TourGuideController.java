package com.example.InstaPay_Travel_Tours.controller;

import com.example.InstaPay_Travel_Tours.dto.TourGuideDTO;
import com.example.InstaPay_Travel_Tours.service.impl.TourGuideServiceImpl;
import com.example.InstaPay_Travel_Tours.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tourguide")
@CrossOrigin(origins = "http://localhost:3000")
public class TourGuideController {

    @Autowired
    private TourGuideServiceImpl tourGuideService;

    @PostMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveTourGuide(@RequestBody TourGuideDTO tourGuideDTO) {
        tourGuideService.addTourGuide(tourGuideDTO);
        return new ResponseUtil(201, "Tour Guide Saved", null);
    }

    @GetMapping("getAll")
    public List<TourGuideDTO> getAllTourGuides() {
        return tourGuideService.getAllTourGuides();
    }

    @PutMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateTourGuide(@RequestBody TourGuideDTO tourGuideDTO) {
        tourGuideService.updateTourGuide(tourGuideDTO);
        return new ResponseUtil(200, "Tour Guide Updated", null);
    }

    @DeleteMapping("delete/{guideID}")
    public ResponseUtil deleteTourGuide(@PathVariable("guideID") String guideID) {
        tourGuideService.deleteTourGuide(Integer.parseInt(guideID));
        return new ResponseUtil(200, "Tour Guide deleted", null);
    }
}
