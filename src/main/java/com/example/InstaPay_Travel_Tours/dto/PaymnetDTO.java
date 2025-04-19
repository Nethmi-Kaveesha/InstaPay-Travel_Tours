package com.example.InstaPay_Travel_Tours.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymnetDTO {
    private Long bookingId;
    private String cardNumber;
    private String cardName;
    private String expiry;
    private String cvv;
    private Double amount;
}
