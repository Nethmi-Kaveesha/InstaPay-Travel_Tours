package com.example.InstaPay_Travel_Tours.controller;

import com.example.InstaPay_Travel_Tours.dto.BookingDTO;
import com.example.InstaPay_Travel_Tours.dto.PaymentDTO;
import com.example.InstaPay_Travel_Tours.service.PlaceBookingService;
import com.example.InstaPay_Travel_Tours.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/booking")
@CrossOrigin
public class PlaceBookingController {

    @Autowired
    private PlaceBookingService placeBookingService;

    @PostMapping("place")
    public ResponseUtil saveBooking(@RequestBody BookingDTO bookingDTO, @RequestBody PaymentDTO paymentDTO) {
        boolean res = placeBookingService.addBooking(bookingDTO, paymentDTO);
        if (res) {
            return new ResponseUtil(201, "Booking and Payment Saved Successfully", null);
        } else {
            return new ResponseUtil(200, "Failed to Save Booking and Payment", null);
        }
    }
}
