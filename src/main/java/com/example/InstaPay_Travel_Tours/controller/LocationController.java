package com.example.InstaPay_Travel_Tours.controller;

import com.example.InstaPay_Travel_Tours.entity.Location;
import com.example.InstaPay_Travel_Tours.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping
    public List<Location> getLocations() {
        return locationService.getAllLocations();
    }

    @PostMapping
    public Location addLocation(@RequestBody Location location) {
        return locationService.saveLocation(location);
    }
}
