package com.example.InstaPay_Travel_Tours.service.impl;

import com.example.InstaPay_Travel_Tours.dto.TourDTO;
import com.example.InstaPay_Travel_Tours.entity.Tour;
import com.example.InstaPay_Travel_Tours.repo.TourRepository;
import com.example.InstaPay_Travel_Tours.service.TourService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;
    private final ModelMapper modelMapper;

    public TourServiceImpl(TourRepository tourRepository, ModelMapper modelMapper) {
        this.tourRepository = tourRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addTourWithImage(String tourName, String location, String duration, double price, String tourType,
                                 int availableSeats, String startDate, String endDate, String description, MultipartFile imageFile) throws IOException {

        validateTourFields(tourName, location, duration, price, tourType, availableSeats, startDate, endDate, description);

        Tour tour = new Tour();
        tour.setTourName(tourName);
        tour.setLocation(location);
        tour.setDuration(Integer.parseInt(duration));
        tour.setPrice(price);
        tour.setTourType(tourType);
        tour.setAvailableSeats(availableSeats);
        tour.setStartDate(parseDate(startDate));
        tour.setEndDate(parseDate(endDate));
        tour.setDescription(description);
        tour.setImages(encodeImage(imageFile));

        tourRepository.save(tour);
    }

    @Override
    public void addTour(TourDTO tourDTO) {
        Tour tour = modelMapper.map(tourDTO, Tour.class);
        tourRepository.save(tour);
    }

    @Override
    public List<TourDTO> getAllTours() {
        return tourRepository.findAll().stream()
                .map(tour -> modelMapper.map(tour, TourDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TourDTO> getTourById(int tourId) {
        return tourRepository.findById(tourId)
                .map(tour -> modelMapper.map(tour, TourDTO.class));
    }

    @Override
    public void updateTour(TourDTO tourDTO) {
        Tour existingTour = tourRepository.findById(tourDTO.getTourID())
                .orElseThrow(() -> new RuntimeException("Tour not found"));

        modelMapper.map(tourDTO, existingTour);
        tourRepository.save(existingTour);
    }

    @Override
    public void deleteTour(int tourId) {
        if (!tourRepository.existsById(tourId)) {
            throw new RuntimeException("Tour not found");
        }
        tourRepository.deleteById(tourId);
    }

    private void validateTourFields(String tourName, String location, String duration, double price, String tourType,
                                    int availableSeats, String startDate, String endDate, String description) {
        if (tourName == null || tourName.isEmpty()) throw new IllegalArgumentException("Tour name is required");
        if (location == null || location.isEmpty()) throw new IllegalArgumentException("Location is required");
        if (duration == null || duration.isEmpty()) throw new IllegalArgumentException("Duration is required");
        if (price <= 0) throw new IllegalArgumentException("Price must be greater than 0");
        if (tourType == null || tourType.isEmpty()) throw new IllegalArgumentException("Tour type is required");
        if (availableSeats <= 0) throw new IllegalArgumentException("Available seats must be greater than 0");
        if (startDate == null || startDate.isEmpty()) throw new IllegalArgumentException("Start date is required");
        if (endDate == null || endDate.isEmpty()) throw new IllegalArgumentException("End date is required");
        if (description == null || description.isEmpty()) throw new IllegalArgumentException("Description is required");
    }

    private Date parseDate(String dateStr) {
        LocalDate localDate = LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private String encodeImage(MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            if (!imageFile.getContentType().startsWith("image")) {
                throw new IllegalArgumentException("Uploaded file is not an image");
            }
            return Base64.getEncoder().encodeToString(imageFile.getBytes());
        }
        return null;
    }
}