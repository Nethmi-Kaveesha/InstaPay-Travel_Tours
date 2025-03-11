package com.example.InstaPay_Travel_Tours.service.impl;

import com.example.InstaPay_Travel_Tours.dto.TourGuideDTO;
import com.example.InstaPay_Travel_Tours.entity.TourGuide;
import com.example.InstaPay_Travel_Tours.repo.TourGuideRepository;
import com.example.InstaPay_Travel_Tours.service.TourGuideService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourGuideServiceImpl implements TourGuideService {
    @Autowired
    private TourGuideRepository tourGuideRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addTourGuide(TourGuideDTO tourGuideDTO) {
        if (tourGuideRepo.existsById(tourGuideDTO.getGuideID())) {
            throw new RuntimeException("Tour guide already exists");
        }
        tourGuideRepo.save(modelMapper.map(tourGuideDTO, TourGuide.class));
    }

    @Override
    public List<TourGuideDTO> getAllTourGuides() {
        return modelMapper.map(tourGuideRepo.findAll(),
                new TypeToken<List<TourGuideDTO>>() {}.getType());
    }

    @Override
    public void updateTourGuide(TourGuideDTO tourGuideDTO) {
        if (tourGuideRepo.existsById(tourGuideDTO.getGuideID())) {
            tourGuideRepo.save(modelMapper.map(tourGuideDTO, TourGuide.class));
        } else {
            throw new RuntimeException("Tour guide does not exist");
        }
    }

    @Override
    public void deleteTourGuide(int guideID) {
        tourGuideRepo.deleteById(guideID);
    }
}
