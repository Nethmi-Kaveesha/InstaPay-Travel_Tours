//package com.example.InstaPay_Travel_Tours.controller;
//
//import com.example.InstaPay_Travel_Tours.entity.Booking;
//import com.example.InstaPay_Travel_Tours.entity.Tour;
//import com.example.InstaPay_Travel_Tours.repo.BookingRepository;
//import com.example.InstaPay_Travel_Tours.repo.TourRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Date;
//
//@RestController
//@RequestMapping("/api/v1/bookings")
//public class BookingController {
//
//    @Autowired
//    private BookingRepository bookingRepository;
//
//    @Autowired
//    private TourRepository tourRepository;
//
//    @PostMapping
//    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
//        // Fetch the tour by ID
//        Tour tour = tourRepository.findById(booking.getTour().getTourId())
//                .orElseThrow(() -> new RuntimeException("Tour not found"));
//
//        // Update available seats
//        if (tour.getAvailableSeats() >= booking.getSeatsBooked()) {
//            tour.setAvailableSeats(tour.getAvailableSeats() - booking.getSeatsBooked());
//            tourRepository.save(tour);
//
//            // Set the booking date
//            booking.setBookingDate(new Date());
//
//            // Save the booking
//            Booking savedBooking = bookingRepository.save(booking);
//
//            return ResponseEntity.status(201).body(savedBooking);
//        } else {
//            return ResponseEntity.status(400).body(null); // Not enough available seats
//        }
//    }
//}
