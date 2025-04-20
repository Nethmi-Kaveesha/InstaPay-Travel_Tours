package com.example.InstaPay_Travel_Tours.service;

import com.example.InstaPay_Travel_Tours.dto.BookingDTO;
import com.example.InstaPay_Travel_Tours.dto.BookingWithDetailsDTO;
import com.example.InstaPay_Travel_Tours.entity.Booking;

import java.util.List;

public interface PlaceBookingService {
    public boolean addBooking(BookingDTO bookingDTO);

    List<BookingDTO> getAllBookings();

    boolean updateStatusToPaid(Long bookingId);




}
