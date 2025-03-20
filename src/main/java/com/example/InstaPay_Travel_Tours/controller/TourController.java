package com.example.InstaPay_Travel_Tours.controller;

import com.example.InstaPay_Travel_Tours.dto.TourDTO;
import com.example.InstaPay_Travel_Tours.service.impl.TourServiceImpl;
import com.example.InstaPay_Travel_Tours.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/tours")
@CrossOrigin(origins = "http://localhost:63342")
public class TourController {

    @Autowired
    private TourServiceImpl tourService;

    @Value("${image.upload.path}")
    private String imageUploadPath;

    @PostMapping(value = "save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveTour(@RequestPart("tour") TourDTO tourDTO, @RequestPart(value = "images", required = false) MultipartFile[] images) {
        // Validate TourDTO fields if needed
        if (tourDTO == null || tourDTO.getTourName() == null || tourDTO.getTourName().isEmpty()) {
            return new ResponseUtil(400, "Tour name is required", null);
        }

        // Add the tour
        tourService.addTour(tourDTO);

        // Process and save images if provided
        List<String> imagePaths = new ArrayList<>();
        if (images != null && images.length > 0) {
            for (MultipartFile image : images) {
                String fileName = StringUtils.cleanPath(image.getOriginalFilename());
                try {
                    // Save the image and get the image path
                    String imagePath = saveImage(fileName, image);
                    imagePaths.add(imagePath);
                } catch (IOException e) {
                    return new ResponseUtil(500, "Image upload failed", null);
                }
            }
        }

        return new ResponseUtil(201, "Tour Saved", imagePaths);
    }

    @GetMapping("getAll")
    public List<TourDTO> getAllTours() {
        return tourService.getAllTours();
    }

    @PutMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateTour(@RequestBody TourDTO tourDTO) {
        tourService.updateTour(tourDTO);
        return new ResponseUtil(200, "Tour Updated", null);
    }

    @DeleteMapping("delete/{tourID}")
    public ResponseUtil deleteTour(@PathVariable("tourID") String tourID) {
        tourService.deleteTour(Integer.parseInt(tourID));
        return new ResponseUtil(200, "Tour deleted", null);
    }

    // Method to save images and return the file path
    private String saveImage(String fileName, MultipartFile image) throws IOException {
        // Use the injected path instead of hardcoding
        File directory = new File(imageUploadPath);
        if (!directory.exists()) {
            directory.mkdirs();  // Create directories if they don't exist
        }

        File file = new File(directory, fileName);
        image.transferTo(file);

        return "/images/" + fileName;  // Return relative path to access images in the front end
    }
}
