package com.example.InstaPay_Travel_Tours.service.impl;

import com.example.InstaPay_Travel_Tours.dto.TourScheduleDTO;
import com.example.InstaPay_Travel_Tours.entity.TourSchedule;
import com.example.InstaPay_Travel_Tours.repo.TourScheduleRepository;
import com.example.InstaPay_Travel_Tours.service.TourScheduleService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourScheduleServiceImpl implements TourScheduleService {

    @Autowired
    private TourScheduleRepository tourScheduleRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addTourSchedule(TourScheduleDTO tourScheduleDTO) {
        if (tourScheduleRepo.existsById(tourScheduleDTO.getScheduleId())) {
            throw new RuntimeException("Tour schedule already exists");
        }
        tourScheduleRepo.save(modelMapper.map(tourScheduleDTO, TourSchedule.class));
    }

    @Override
    public List<TourScheduleDTO> getAllTourSchedules() {
        return modelMapper.map(tourScheduleRepo.findAll(),
                new TypeToken<List<TourScheduleDTO>>() {}.getType());
    }

    @Override
    public void updateTourSchedule(TourScheduleDTO tourScheduleDTO) {
        if (tourScheduleRepo.existsById(tourScheduleDTO.getScheduleId())) {
            tourScheduleRepo.save(modelMapper.map(tourScheduleDTO, TourSchedule.class));
        } else {
            throw new RuntimeException("Tour schedule does not exist");
        }
    }

    @Override
    public void deleteTourSchedule(int scheduleId) {
        tourScheduleRepo.deleteById(scheduleId);
    }
}
