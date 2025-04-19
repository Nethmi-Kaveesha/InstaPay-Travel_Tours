package com.example.InstaPay_Travel_Tours.repo;

import com.example.InstaPay_Travel_Tours.entity.Pay;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PayRepository extends JpaRepository<Pay, Long> {
}
