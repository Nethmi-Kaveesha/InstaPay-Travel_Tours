package com.example.InstaPay_Travel_Tours.service;

import com.example.InstaPay_Travel_Tours.dto.PaymentDTO;
import com.example.InstaPay_Travel_Tours.dto.BookingDTO;

public interface PlaceBookingService {

    // Method to add a booking and process payment
    boolean addBooking(BookingDTO bookingDTO, PaymentDTO paymentDTO);
}
