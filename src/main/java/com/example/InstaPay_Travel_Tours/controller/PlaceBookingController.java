package com.example.InstaPay_Travel_Tours.controller;

import com.example.InstaPay_Travel_Tours.dto.BookingDTO;
import com.example.InstaPay_Travel_Tours.entity.Booking;
import com.example.InstaPay_Travel_Tours.repo.BookingRepository;
import com.example.InstaPay_Travel_Tours.service.PlaceBookingService;
import com.example.InstaPay_Travel_Tours.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/booking")
@CrossOrigin(origins = "http://localhost:3000")
public class PlaceBookingController {

    @Autowired
    private PlaceBookingService placeBookingService;

    @PostMapping("place")
    public ResponseEntity<?> saveBooking(@RequestBody BookingDTO bookingDTO) {
        try {
            boolean res = placeBookingService.addBooking(bookingDTO);
            if (res) {
                return ResponseEntity.status(201).body(new ResponseUtil(201, "Booking Saved", null));
            } else {
                return ResponseEntity.status(500).body(new ResponseUtil(500, "Failed to save booking", null));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(new ResponseUtil(400, e.getMessage(), null));
        }
    }

    @GetMapping("view")
    public List<BookingDTO> getAllBookings() {
        return placeBookingService.getAllBookings();
    }


}



