package com.example.InstaPay_Travel_Tours.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private String merchantId;
    private String orderId;
    private String amount;
    private String currency;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String country;
}
