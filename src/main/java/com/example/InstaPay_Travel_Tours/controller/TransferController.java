package com.example.InstaPay_Travel_Tours.controller;

import com.example.InstaPay_Travel_Tours.dto.TransferRequest;
import com.example.InstaPay_Travel_Tours.entity.Booking;
import com.example.InstaPay_Travel_Tours.entity.Transfer;
import com.example.InstaPay_Travel_Tours.entity.User;
import com.example.InstaPay_Travel_Tours.repo.BookingRepository;
import com.example.InstaPay_Travel_Tours.repo.TransferRepository;
import com.example.InstaPay_Travel_Tours.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/transfer")
public class TransferController {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<Transfer> createTransfer(@RequestBody TransferRequest request) {

        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));


        Transfer transfer = new Transfer();
        transfer.setOrderId(request.getOrderId());
        transfer.setAmount(request.getAmount());
        transfer.setStatus("Pending");
        transfer.setMethod("Transfer");

        transfer.setBooking(booking);
        transfer.setUser(user);

        Transfer saved = transferRepository.save(transfer);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

}
