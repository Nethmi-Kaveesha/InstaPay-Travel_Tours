package com.example.InstaPay_Travel_Tours.repo;

import com.example.InstaPay_Travel_Tours.entity.TourGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourGuideRepository extends JpaRepository<TourGuide, Integer> {
    // You can add custom queries here if necessary
}
