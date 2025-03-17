package com.example.InstaPay_Travel_Tours.controller;

import com.example.InstaPay_Travel_Tours.dto.TourScheduleDTO;
import com.example.InstaPay_Travel_Tours.service.impl.TourScheduleServiceImpl;
import com.example.InstaPay_Travel_Tours.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tourschedule")
@CrossOrigin(origins = "http://localhost:3000")
public class TourScheduleController {

    @Autowired
    private TourScheduleServiceImpl tourScheduleService;

    @PostMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveTourSchedule(@RequestBody TourScheduleDTO tourScheduleDTO) {
        tourScheduleService.addTourSchedule(tourScheduleDTO);
        return new ResponseUtil(201, "Tour Schedule Saved", null);
    }

    @GetMapping("getAll")
    public List<TourScheduleDTO> getAllTourSchedules() {
        return tourScheduleService.getAllTourSchedules();
    }

    @PutMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateTourSchedule(@RequestBody TourScheduleDTO tourScheduleDTO) {
        tourScheduleService.updateTourSchedule(tourScheduleDTO);
        return new ResponseUtil(200, "Tour Schedule Updated", null);
    }

    @DeleteMapping("delete/{scheduleId}")
    public ResponseUtil deleteTourSchedule(@PathVariable("scheduleId") String scheduleId) {
        tourScheduleService.deleteTourSchedule(Integer.parseInt(scheduleId));
        return new ResponseUtil(200, "Tour Schedule Deleted", null);
    }
}
