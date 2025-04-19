package com.example.InstaPay_Travel_Tours.repo;

import com.example.InstaPay_Travel_Tours.entity.PaymentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRequestRepository extends JpaRepository<PaymentRequest, Long> {

}
