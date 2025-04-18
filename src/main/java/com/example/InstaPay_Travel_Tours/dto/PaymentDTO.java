package com.example.InstaPay_Travel_Tours.dto;

import lombok.Data;

@Data
public class PaymentDTO {
    private Long id;
    private String email;
    private String paymentId;
    private String status;
    private Double amount;
    private String tourName;

    public PaymentDTO(Long id, String email, String paymentId, String status, Double amount, String tourName) {
        this.id = id;
        this.email = email;
        this.paymentId = paymentId;
        this.status = status;
        this.amount = amount;
        this.tourName = tourName;
    }
}
