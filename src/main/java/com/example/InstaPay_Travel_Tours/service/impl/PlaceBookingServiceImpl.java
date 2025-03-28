package com.example.InstaPay_Travel_Tours.service.impl;

import com.example.InstaPay_Travel_Tours.dto.BookingDTO;
import com.example.InstaPay_Travel_Tours.dto.BookingDetailDTO;
import com.example.InstaPay_Travel_Tours.entity.Booking;
import com.example.InstaPay_Travel_Tours.entity.BookingDetail;
import com.example.InstaPay_Travel_Tours.entity.Tour;
import com.example.InstaPay_Travel_Tours.entity.User;
import com.example.InstaPay_Travel_Tours.repo.BookingDetailRepository;
import com.example.InstaPay_Travel_Tours.repo.BookingRepository;
import com.example.InstaPay_Travel_Tours.repo.TourRepository;  // Use the correct TourRepository
import com.example.InstaPay_Travel_Tours.repo.UserRepository;
import com.example.InstaPay_Travel_Tours.service.PlaceBookingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class PlaceBookingServiceImpl implements PlaceBookingService {

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private BookingDetailRepository bookingDetailRepo;

    @Autowired
    private TourRepository tourRepo;  // Correct repository for Tour entities

    @Autowired
    private UserRepository userRepo;  // Using UserRepo instead of CustomerRepo

    @Transactional
    public boolean addBooking(BookingDTO bookingDTO) {
        try {
            // Convert userId from String to UUID
            UUID userId = UUID.fromString(bookingDTO.getUserId().toString());  // Ensure the userId is of UUID type

            // Find the user by UUID
            User user = userRepo.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Create a new booking
            Booking booking = new Booking();
            booking.setBookingDate(bookingDTO.getBookingDate());
            booking.setTotalPrice(bookingDTO.getTotalPrice());
            booking.setUser(user);  // Set the user instead of customer

            // Save the booking to the repository
            Booking savedBooking = bookingRepo.save(booking);

            // Process each booking detail
            for (BookingDetailDTO bookingDetailDTO : bookingDTO.getBookingDetails()) {
                // Use the correct repository to find the tour by its ID
                Tour tour = tourRepo.findById(bookingDetailDTO.getTourId())
                        .orElseThrow(() -> new RuntimeException("Tour not found"));

                BookingDetail bookingDetail = new BookingDetail();
                bookingDetail.setQuantity(bookingDetailDTO.getQuantity());
                bookingDetail.setTotal(bookingDetailDTO.getTotal());
                bookingDetail.setTour(tour);  // Set the tour from the tourId
                bookingDetail.setBooking(savedBooking);  // Link the booking to the booking detail

                // Save each booking detail to the repository
                bookingDetailRepo.save(bookingDetail);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}