
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
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

                int availableSeats = tour.getAvailableSeats();
                int requestedSeats = (int) bookingDetailDTO.getQuantity();

                if (availableSeats <= 0) {
                    throw new RuntimeException("No seats available for " + tour.getTourName());
                }

                if (requestedSeats > availableSeats) {
                    throw new RuntimeException("Only " + availableSeats + " seats available for " + tour.getTourName());
                }

                // Reduce the available seats
                tour.setAvailableSeats(availableSeats - requestedSeats);
                tourRepo.save(tour); // update seat count in DB

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
            // You can log this or pass a custom error message
            throw new RuntimeException("Booking failed: " + e.getMessage());
        }
    }

    public List<BookingDTO> getAllBookings() {
        return bookingRepo.findAll().stream().map(booking -> {
            BookingDTO dto = new BookingDTO();
            dto.setBookingId(booking.getBookingId());
            dto.setBookingDate(booking.getBookingDate());
            dto.setTotalPrice(booking.getTotalPrice());
            dto.setUserId(booking.getUser().getUid());

            // Map booking detail list
            List<BookingDetailDTO> detailDTOs = booking.getBookingDetails().stream().map(detail -> {
                BookingDetailDTO detailDTO = new BookingDetailDTO();
                detailDTO.setId(detail.getId());
                detailDTO.setBookingId(booking.getBookingId());
                detailDTO.setTourId(detail.getTour().getTourID());
                detailDTO.setQuantity(detail.getQuantity());
                detailDTO.setPrice(detail.getPrice());
                detailDTO.setTotal(detail.getTotal());
                return detailDTO;
            }).collect(Collectors.toList());

            dto.setBookingDetails(detailDTOs);

            return dto;
        }).collect(Collectors.toList());
    }

    public Booking findById(Long id) {
        Optional<Booking> booking = bookingRepo.findById(Math.toIntExact(id));
        return booking.orElseThrow(() -> new RuntimeException("Booking not found with ID: " + id));
    }

    public boolean updateStatusToPaid(Long bookingId) {
        Optional<Booking> bookingOpt = bookingRepo.findById(Math.toIntExact(bookingId));
        if (bookingOpt.isPresent()) {
            Booking booking = bookingOpt.get();
            booking.setStatus("PAID");
            bookingRepo.save(booking);
            return true;
        }
        return false;
    }


}