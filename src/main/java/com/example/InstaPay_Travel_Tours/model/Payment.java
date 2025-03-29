package com.example.InstaPay_Travel_Tours.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String paymentId;
    private String status;
    private Double amount;
}
