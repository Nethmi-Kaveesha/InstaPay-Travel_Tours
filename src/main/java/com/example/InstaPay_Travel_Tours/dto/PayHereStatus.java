package com.example.InstaPay_Travel_Tours.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayHereStatus {
    private String status;
    private String order_id;
    private String payment_id;
    private String payhere_amount;
    private String payhere_currency;
}
