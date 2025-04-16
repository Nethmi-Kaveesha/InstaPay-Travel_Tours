package com.example.InstaPay_Travel_Tours.repo;

import com.example.InstaPay_Travel_Tours.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
