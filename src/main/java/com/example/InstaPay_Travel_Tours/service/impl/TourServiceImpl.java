package com.example.InstaPay_Travel_Tours.service.impl;

import com.example.InstaPay_Travel_Tours.dto.TourDTO;
import com.example.InstaPay_Travel_Tours.entity.Tour;
import com.example.InstaPay_Travel_Tours.repo.TourRepository;
import com.example.InstaPay_Travel_Tours.service.TourService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
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
        // Validate the DTO
        if (tourDTO == null || tourDTO.getTourName() == null || tourDTO.getTourName().isEmpty()) {
            throw new IllegalArgumentException("Tour name is required");
        }

        // Check if tour already exists
        if (tourRepo.existsById(tourDTO.getTourID())) {
            throw new RuntimeException("Tour already exists");
        }

        // Map TourDTO to Tour entity
        Tour tour = modelMapper.map(tourDTO, Tour.class);
        tourRepo.save(tour);
    }

    @Override
    public List<TourDTO> getAllTours() {
        // Retrieve all tours from the repository
        List<Tour> tours = tourRepo.findAll();
        // Map the list of Tour entities to TourDTOs
        return modelMapper.map(tours, new TypeToken<List<TourDTO>>() {}.getType());
    }

    @Override
    public void updateTour(TourDTO tourDTO) {
        // Validate the DTO
        if (tourDTO == null || tourDTO.getTourID() == null || tourDTO.getTourID() <= 0) {
            throw new IllegalArgumentException("Invalid tour details");
        }

        // Check if the tour exists
        Optional<Tour> existingTour = tourRepo.findById(tourDTO.getTourID());
        if (existingTour.isPresent()) {
            // Map the updated TourDTO to a Tour entity
            Tour tour = modelMapper.map(tourDTO, Tour.class);
            tourRepo.save(tour);
        } else {
            throw new RuntimeException("Tour does not exist");
        }
    }

    @Override
    public void deleteTour(int tourID) {
        // Check if the tour exists before deletion
        if (tourRepo.existsById(tourID)) {
            tourRepo.deleteById(tourID);
        } else {
            throw new RuntimeException("Tour does not exist");
        }
    }

    // Modified method to handle image uploading and tour creation
    public void addTourWithImage(String tourName, String location, String duration, double price, String tourType,
                                 int availableSeats, String startDate, String endDate, String description, MultipartFile imageFile) throws IOException, ParseException {

        // Validate fields
        if (tourName == null || tourName.isEmpty()) {
            throw new IllegalArgumentException("Tour name is required");
        }
        if (location == null || location.isEmpty()) {
            throw new IllegalArgumentException("Location is required");
        }
        if (duration == null || duration.isEmpty()) {
            throw new IllegalArgumentException("Duration is required");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        if (tourType == null || tourType.isEmpty()) {
            throw new IllegalArgumentException("Tour type is required");
        }
        if (availableSeats <= 0) {
            throw new IllegalArgumentException("Available seats must be greater than 0");
        }
        if (startDate == null || startDate.isEmpty()) {
            throw new IllegalArgumentException("Start date is required");
        }
        if (endDate == null || endDate.isEmpty()) {
            throw new IllegalArgumentException("End date is required");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description is required");
        }

        // Create a new Tour entity
        Tour tour = new Tour();
        tour.setTourName(tourName);
        tour.setLocation(location);
        tour.setDuration(Integer.parseInt(duration));
        tour.setPrice(price);
        tour.setTourType(tourType);
        tour.setAvailableSeats(availableSeats);
        tour.setStartDate(new java.text.SimpleDateFormat("yyyy-MM-dd").parse(startDate));
        tour.setEndDate(new java.text.SimpleDateFormat("yyyy-MM-dd").parse(endDate));
        tour.setDescription(description);

        // Handle image upload (convert to byte array if present)
        if (imageFile != null && !imageFile.isEmpty()) {
            byte[] imageBytes = imageFile.getBytes();
            tour.setImages(imageBytes);  // Store the image as a byte array
        }

        // Save the tour to the repository
        tourRepo.save(tour);
    }
}
