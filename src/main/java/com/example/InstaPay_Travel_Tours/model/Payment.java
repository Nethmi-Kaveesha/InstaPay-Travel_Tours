package com.example.InstaPay_Travel_Tours.model;

import com.example.InstaPay_Travel_Tours.entity.Tour;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id", referencedColumnName = "tourid")
    private Tour tour;
}
