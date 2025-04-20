// PayHereService.java
package com.example.InstaPay_Travel_Tours.service;

import com.example.InstaPay_Travel_Tours.dto.PaymentRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Service
public class PayHereService {

    public String makePayment(PaymentRequest paymentRequest) {
        String payHereUrl = "https://sandbox.payhere.lk/pay/checkoutJ";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PaymentRequest> entity = new HttpEntity<>(paymentRequest, headers);

        ResponseEntity<String> response = restTemplate.exchange(payHereUrl, HttpMethod.POST, entity, String.class);

        return response.getBody();
    }
}
