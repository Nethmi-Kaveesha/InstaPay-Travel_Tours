package com.example.InstaPay_Travel_Tours.repo;

import com.example.InstaPay_Travel_Tours.dto.BookingWithDetailsDTO;
import com.example.InstaPay_Travel_Tours.entity.BookingDetail;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingDetailRepository extends JpaRepository<BookingDetail,Integer> {

}
