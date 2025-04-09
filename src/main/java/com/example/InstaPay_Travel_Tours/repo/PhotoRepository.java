package com.example.InstaPay_Travel_Tours.repo;


import com.example.InstaPay_Travel_Tours.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    // Find photos by featured status
    List<Photo> findByIsFeatured(boolean isFeatured);

    // Find photos by category
    List<Photo> findByCategory(String category);
}
