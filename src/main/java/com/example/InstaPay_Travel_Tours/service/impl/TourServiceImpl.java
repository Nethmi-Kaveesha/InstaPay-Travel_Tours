package com.example.InstaPay_Travel_Tours.service.impl;

import com.example.InstaPay_Travel_Tours.dto.TourDTO;
import com.example.InstaPay_Travel_Tours.entity.Tour;
import com.example.InstaPay_Travel_Tours.repo.TourRepository;

import com.example.InstaPay_Travel_Tours.service.TourService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourServiceImpl implements TourService {

    @Autowired
    private TourRepository tourRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addTour(TourDTO tourDTO) {
        if (tourRepo.existsById(tourDTO.getTourID())) {
            throw new RuntimeException("Tour already exists");
        }
        tourRepo.save(modelMapper.map(tourDTO, Tour.class));
    }

    @Override
    public List<TourDTO> getAllTours() {
        return modelMapper.map(tourRepo.findAll(),
                new TypeToken<List<TourDTO>>() {}.getType());
    }

    @Override
    public void updateTour(TourDTO tourDTO) {
        if (tourRepo.existsById(tourDTO.getTourID())) {
            tourRepo.save(modelMapper.map(tourDTO, Tour.class));
        } else {
            throw new RuntimeException("Tour does not exist");
        }
    }

    @Override
    public void deleteTour(int tourID) {
        if (tourRepo.existsById(tourID)) {
            tourRepo.deleteById(tourID);
        } else {
            throw new RuntimeException("Tour does not exist");
        }
    }
}
