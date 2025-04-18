package com.example.InstaPay_Travel_Tours.repo;

import com.example.InstaPay_Travel_Tours.dto.BookingDTO;
import com.example.InstaPay_Travel_Tours.entity.Booking;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking,Integer> {
    List<Booking> findAll();
}
