//package com.example.InstaPay_Travel_Tours.controller;
//
//import com.example.InstaPay_Travel_Tours.dto.PaymentRequest;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@CrossOrigin
//@RestController
//@RequestMapping("/payhere")
//public class PayHereController {
//
//    @PostMapping("/initiate-payment")
//    public ResponseEntity<Map<String, String>> initiatePayment(@RequestBody PaymentRequest paymentRequest) {
//        String payHereUrl = "https://sandbox.payhere.lk/pay/checkout?" +
//                "merchant_id=" + paymentRequest.getMerchantId() +
//                "&return_url=http://localhost:63342/success.html" +
//                "&cancel_url=http://localhost:63342/cancel.html" +
//                "&notify_url=http://localhost:8080/payhere/notify" +
//                "&order_id=" + paymentRequest.getOrderId() +
//                "&items=Travel+Booking" +
//                "&amount=" + paymentRequest.getAmount() +
//                "&currency=" + paymentRequest.getCurrency() +
//                "&first_name=" + paymentRequest.getFirstName() +
//                "&last_name=" + paymentRequest.getLastName() +
//                "&email=" + paymentRequest.getEmail() +
//                "&phone=" + paymentRequest.getPhone() +
//                "&address=" + paymentRequest.getAddress() +
//                "&city=" + paymentRequest.getCity() +
//                "&country=" + paymentRequest.getCountry();
//
//        Map<String, String> response = new HashMap<>();
//        response.put("status", "success");
//        response.put("paymentUrl", payHereUrl);
//
//        return ResponseEntity.ok(response);
//    }
//
//    @PostMapping("/notify")
//    public ResponseEntity<String> handlePayHereNotification(@RequestParam Map<String, String> data) {
//        // You will receive payment details here (orderId, status, etc.)
//        System.out.println("Payment Notification Received: " + data);
//
//        // Example: Process payment, verify it, and update order status
//        String orderStatus = data.get("status"); // Should be 'Completed', 'Pending', etc.
//        if ("Completed".equals(orderStatus)) {
//            // Handle completed payment (e.g., update order, send confirmation)
//        } else {
//            // Handle failed or pending payment
//        }
//
//        return ResponseEntity.ok("Notification received");
//    }
//
//}
