
package com.example.InstaPay_Travel_Tours.service;

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
    private TourRepository tourRepo;

    @Autowired
    private UserRepository userRepo;

    @Transactional
    public boolean addBooking(BookingDTO bookingDTO) {
        try {
            UUID userId = UUID.fromString(bookingDTO.getUserId().toString());

            User user = userRepo.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Booking booking = new Booking();
            booking.setBookingDate(bookingDTO.getBookingDate());
            booking.setTotalPrice(bookingDTO.getTotalPrice());
            booking.setUser(user);

            Booking savedBooking = bookingRepo.save(booking);

            for (BookingDetailDTO bookingDetailDTO : bookingDTO.getBookingDetails()) {
                Tour tour = tourRepo.findById(bookingDetailDTO.getTourId())
                        .orElseThrow(() -> new RuntimeException("Tour not found"));

                System.out.println("Tour ID: " + bookingDetailDTO.getTourId() +
                        ", Quantity: " + bookingDetailDTO.getQuantity() +
                        ", Price: " + bookingDetailDTO.getPrice() +
                        ", Total: " + bookingDetailDTO.getTotal());

                BookingDetail bookingDetail = new BookingDetail();
                bookingDetail.setQuantity(bookingDetailDTO.getQuantity());

                bookingDetail.setPrice(bookingDetailDTO.getPrice());

                if (bookingDetailDTO.getTotal() == 0) {
                    bookingDetail.setTotal(bookingDetailDTO.getQuantity() * bookingDetailDTO.getPrice());
                } else {
                    bookingDetail.setTotal(bookingDetailDTO.getTotal());
                }

                bookingDetail.setTour(tour);
                bookingDetail.setBooking(savedBooking);

                bookingDetailRepo.save(bookingDetail);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}