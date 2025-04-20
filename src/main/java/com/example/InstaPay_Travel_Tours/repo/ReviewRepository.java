package com.example.InstaPay_Travel_Tours.repo;


import com.example.InstaPay_Travel_Tours.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
