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

    // Find tours by name (case-insensitive)
    List<Tour> findByTourNameContainingIgnoreCase(String tourName);

    // Find tour by ID
    Optional<Tour> findByTourID(int tourID);

    // Paginated query to find tours by location
    @Query("SELECT t FROM Tour t WHERE t.location = :location")
    Page<Tour> findByLocation(@Param("location") String location, Pageable pageable);

    // Paginated query to find tours with available seats greater than the specified number
    @Query("SELECT t FROM Tour t WHERE t.availableSeats > :seats")
    Page<Tour> findToursWithSeatsGreaterThan(@Param("seats") int seats, Pageable pageable);

    // Find tours available in a specific date range
    @Query("SELECT t FROM Tour t WHERE t.startDate >= :startDate AND t.endDate <= :endDate")
    List<Tour> findAvailableTours(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // Combine filters for location and available seats
    @Query("SELECT t FROM Tour t WHERE t.location = :location AND t.availableSeats > :seats")
    Page<Tour> findToursByLocationAndSeats(@Param("location") String location, @Param("seats") int seats, Pageable pageable);
}
