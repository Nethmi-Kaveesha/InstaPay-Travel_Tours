package com.example.InstaPay_Travel_Tours.projection;

import java.util.Date;

public interface BookingAdminViewProjection {
    int getBookingId();
    Date getBookingDate();
    double getTotalPrice();
    String getUserId();
    int getDetailId();
    int getTourId();
    double getPrice();
    double getQuantity();
    double getTotal();
}
