package com.example.InstaPay_Travel_Tours.service.impl;

import com.example.InstaPay_Travel_Tours.dto.TourDTO;
import com.example.InstaPay_Travel_Tours.entity.Tour;
import com.example.InstaPay_Travel_Tours.repo.TourRepository;
import com.example.InstaPay_Travel_Tours.service.TourService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TourServiceImpl implements TourService {

    @Autowired
    private TourRepository tourRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addTour(@Valid TourDTO tourDTO) {
        Optional<Tour> existingTour = tourRepo.findById(tourDTO.getTourID());
        if (existingTour.isPresent()) {
            throw new RuntimeException("Tour with ID " + tourDTO.getTourID() + " already exists");
        }

        // Mapping DTO to entity and saving the new tour
        Tour newTour = modelMapper.map(tourDTO, Tour.class);
        tourRepo.save(newTour);
    }

    @Override
    public List<Tour> findAvailableTours(LocalDate startDate, LocalDate endDate) {
        return tourRepo.findAvailableTours(startDate, endDate);
    }

    @Override
    public List<TourDTO> getAllTours() {
        List<Tour> tours = tourRepo.findAll();
        return modelMapper.map(tours, new TypeToken<List<TourDTO>>() {}.getType());
    }

    @Override
    public TourDTO getTourById(int tourID) {
        Optional<Tour> tour = tourRepo.findById(tourID);
        if (tour.isPresent()) {
            return modelMapper.map(tour.get(), TourDTO.class);
        } else {
            throw new RuntimeException("Tour with ID " + tourID + " not found");
        }
    }

    public List<TourDTO> searchToursByName(String keyword) {
        List<Tour> tours = tourRepo.findByTourNameContainingIgnoreCase(keyword);
        return modelMapper.map(tours, new TypeToken<List<TourDTO>>() {}.getType());
    }

    @Override
    public void updateTour(@Valid TourDTO tourDTO) {
        Optional<Tour> existingTour = tourRepo.findById(tourDTO.getTourID());
        if (existingTour.isPresent()) {
            Tour tourToUpdate = existingTour.get();
            modelMapper.map(tourDTO, tourToUpdate);
            tourRepo.save(tourToUpdate);
        } else {
            throw new RuntimeException("Tour with ID " + tourDTO.getTourID() + " does not exist");
        }
    }

    @Override
    public void deleteTour(int tourID) {
        Optional<Tour> existingTour = tourRepo.findById(tourID);
        if (existingTour.isPresent()) {
            tourRepo.deleteById(tourID);
        } else {
            throw new RuntimeException("Tour with ID " + tourID + " does not exist");
        }
    }
}
