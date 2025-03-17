package com.example.InstaPay_Travel_Tours.service;

import com.example.InstaPay_Travel_Tours.dto.TourScheduleDTO;

import java.util.List;

public interface TourScheduleService {
    void addTourSchedule(TourScheduleDTO tourScheduleDTO);

    List<TourScheduleDTO> getAllTourSchedules();

    void updateTourSchedule(TourScheduleDTO tourScheduleDTO);

    void deleteTourSchedule(int scheduleId);
}
