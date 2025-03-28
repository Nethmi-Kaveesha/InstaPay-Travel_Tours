package com.example.InstaPay_Travel_Tours.service.impl;

import com.example.InstaPay_Travel_Tours.dto.BookingDTO;
import com.example.InstaPay_Travel_Tours.dto.BookingDetailDTO;
import com.example.InstaPay_Travel_Tours.entity.Booking;
import com.example.InstaPay_Travel_Tours.entity.BookingDetail;
import com.example.InstaPay_Travel_Tours.entity.Tour;
import com.example.InstaPay_Travel_Tours.entity.User;
import com.example.InstaPay_Travel_Tours.repo.BookingDetailRepository;
import com.example.InstaPay_Travel_Tours.repo.BookingRepository;
import com.example.InstaPay_Travel_Tours.repo.TourRepository;
import com.example.InstaPay_Travel_Tours.repo.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final UserRepository userRepo;
    private final BookingRepository bookingRepo;
    private final TourRepository tourRepo;
    private final BookingDetailRepository bookingDetailRepo;

    public BookingService(UserRepository userRepo, BookingRepository bookingRepo,
                          TourRepository tourRepo, BookingDetailRepository bookingDetailRepo) {
        this.userRepo = userRepo;
        this.bookingRepo = bookingRepo;
        this.tourRepo = tourRepo;
        this.bookingDetailRepo = bookingDetailRepo;
    }

    @Transactional
    public boolean addBooking(BookingDTO bookingDTO) {
        try {
            // Validate user
            UUID userId = bookingDTO.getUserId();
            User user = userRepo.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Create and save booking
            Booking booking = new Booking();
            booking.setBookingDate(bookingDTO.getBookingDate());
            booking.setTotalPrice(bookingDTO.getTotalPrice());
            booking.setUser(user);
            Booking savedBooking = bookingRepo.save(booking);

            // Process booking details
            List<BookingDetail> bookingDetails = bookingDTO.getBookingDetails().stream()
                    .map(detailDTO -> {
                        Tour tour = tourRepo.findById(detailDTO.getTourId())
                                .orElseThrow(() -> new RuntimeException("Tour not found"));

                        BookingDetail bookingDetail = new BookingDetail();
                        bookingDetail.setQuantity(detailDTO.getQuantity());
                        bookingDetail.setTotal(detailDTO.getTotal());
                        bookingDetail.setTour(tour);
                        bookingDetail.setBooking(savedBooking);
                        return bookingDetail;
                    }).collect(Collectors.toList());

            // Save all booking details in batch
            bookingDetailRepo.saveAll(bookingDetails);

            return true;
        } catch (RuntimeException e) {
            e.printStackTrace(); // Consider using a logger instead
            return false;
        }
    }
}
