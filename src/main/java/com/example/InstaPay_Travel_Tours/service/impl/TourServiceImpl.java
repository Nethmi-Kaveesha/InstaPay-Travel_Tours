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
import java.util.Optional;

@Service
public class TourServiceImpl implements TourService {

    @Autowired
    private TourRepository tourRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addTour(TourDTO tourDTO) {
        // Checking if the tour already exists by tour name or ID
        Optional<Tour> existingTour = tourRepo.findById(tourDTO.getTourID());
        if (existingTour.isPresent()) {
            throw new RuntimeException("Tour with ID " + tourDTO.getTourID() + " already exists");
        }

        // Mapping DTO to entity and saving the new tour
        Tour newTour = modelMapper.map(tourDTO, Tour.class);
        tourRepo.save(newTour);
    }

    @Override
    public List<TourDTO> getAllTours() {
        // Fetching all tours from the repository and mapping to DTOs
        List<Tour> tours = tourRepo.findAll();
        return modelMapper.map(tours, new TypeToken<List<TourDTO>>() {}.getType());
    }

    @Override
    public TourDTO getTourById(int tourID) {
        // Fetching tour by ID
        Optional<Tour> tour = tourRepo.findById(tourID);
        if (tour.isPresent()) {
            return modelMapper.map(tour.get(), TourDTO.class);
        } else {
            throw new RuntimeException("Tour with ID " + tourID + " not found");
        }
    }

    @Override
    public void updateTour(TourDTO tourDTO) {
        // Fetching the existing tour and updating it
        Optional<Tour> existingTour = tourRepo.findById(tourDTO.getTourID());
        if (existingTour.isPresent()) {
            // Mapping DTO to existing tour entity
            Tour tourToUpdate = existingTour.get();
            modelMapper.map(tourDTO, tourToUpdate);
            tourRepo.save(tourToUpdate);
        } else {
            throw new RuntimeException("Tour with ID " + tourDTO.getTourID() + " does not exist");
        }
    }

    @Override
    public void deleteTour(int tourID) {
        // Checking if the tour exists before deleting
        Optional<Tour> existingTour = tourRepo.findById(tourID);
        if (existingTour.isPresent()) {
            tourRepo.deleteById(tourID);
        } else {
            throw new RuntimeException("Tour with ID " + tourID + " does not exist");
        }
    }
}
