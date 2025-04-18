package com.example.InstaPay_Travel_Tours.repo;

import com.example.InstaPay_Travel_Tours.entity.PayHere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayHereRepository extends JpaRepository<PayHere, Long> {
    PayHere findByOrderId(String orderId);
}
