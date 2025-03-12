package com.example.InstaPay_Travel_Tours.repo;

import com.example.InstaPay_Travel_Tours.entity.TourGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TourGuideRepository extends JpaRepository<TourGuide, Integer> {
    Optional<TourGuide> findByFullName(String fullName); // Ensure method matches the entity property 'name'
}
