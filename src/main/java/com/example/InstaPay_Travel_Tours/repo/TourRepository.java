package com.example.InstaPay_Travel_Tours.repo;

import com.example.InstaPay_Travel_Tours.entity.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer> {

    // Get paginated list of tours by location
    Page<Tour> findByLocation(String location, Pageable pageable);

    // Example: Get tours by name (partial match)
    Page<Tour> findByTourNameContainingIgnoreCase(String tourName, Pageable pageable);

    // Example: Get tours by price range
    @Query("SELECT t FROM Tour t WHERE t.price BETWEEN :minPrice AND :maxPrice")
    Page<Tour> findByPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice, Pageable pageable);
}
