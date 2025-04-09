package com.example.InstaPay_Travel_Tours.repo;


import com.example.InstaPay_Travel_Tours.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
