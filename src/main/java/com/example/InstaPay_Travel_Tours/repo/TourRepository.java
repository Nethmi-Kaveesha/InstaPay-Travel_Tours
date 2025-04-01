package com.example.InstaPay_Travel_Tours.repo;

import com.example.InstaPay_Travel_Tours.entity.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer> {

    List<Tour> findByTourNameContainingIgnoreCase(String tourName);

    // Find tour by ID
    Optional<Tour> findByTourID(int tourID);

    // Find tours by location (Custom Query)
    @Query("SELECT t FROM Tour t WHERE t.location = :location")
    Page<Tour> findByLocation(@Param("location") String location, Pageable pageable);

    // Find tours with available seats greater than a specific number (Custom Query)
    @Query("SELECT t FROM Tour t WHERE t.availableSeats > :seats")
    Page<Tour> findToursWithSeatsGreaterThan(@Param("seats") int seats, Pageable pageable);
    @Query("SELECT t FROM Tour t WHERE t.startDate >= :startDate AND t.endDate <= :endDate")
    List<Tour> findAvailableTours(LocalDate startDate, LocalDate endDate);
}
