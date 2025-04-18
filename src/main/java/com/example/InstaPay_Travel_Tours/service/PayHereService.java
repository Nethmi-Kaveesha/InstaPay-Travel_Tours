// PayHereService.java
package com.example.InstaPay_Travel_Tours.service;

import com.example.InstaPay_Travel_Tours.dto.PaymentRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Service
public class PayHereService {

    public String makePayment(PaymentRequest paymentRequest) {
        // PayHere API endpoint
        String payHereUrl = "https://sandbox.payhere.lk/pay/checkoutJ"; // Use sandbox URL for testing

        // Create RestTemplate instance to send the POST request
        RestTemplate restTemplate = new RestTemplate();

        // Set headers for the request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the request body using the PaymentRequest object
        HttpEntity<PaymentRequest> entity = new HttpEntity<>(paymentRequest, headers);

        // Send the request to PayHere
        ResponseEntity<String> response = restTemplate.exchange(payHereUrl, HttpMethod.POST, entity, String.class);

        // Return the response from PayHere
        return response.getBody();
    }
}
