//package com.example.InstaPay_Travel_Tours.repo;
//
//import com.example.InstaPay_Travel_Tours.entity.Review;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface ReviewRepository extends JpaRepository<Review, Integer> {
//
//    // Custom query to find reviews by a specific tour ID
//
//    List<Review> findByTourTourId(int tourId);  // Renamed method for clarity
//
//    // Custom query to check if a review exists for a specific user and tour
//    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Review r WHERE r.userid.uid = ?1 AND r.tourid.tourId = ?2")
//    boolean existsByUserAndTour(int userId, int tourId);  // Corrected parameter type for userId
//
//
//}
