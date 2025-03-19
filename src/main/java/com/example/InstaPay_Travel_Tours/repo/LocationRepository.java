package com.example.InstaPay_Travel_Tours.repo;


import com.example.InstaPay_Travel_Tours.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
