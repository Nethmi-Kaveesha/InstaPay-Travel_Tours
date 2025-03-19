package com.example.InstaPay_Travel_Tours.controller;

import com.example.InstaPay_Travel_Tours.dto.TourDTO;
import com.example.InstaPay_Travel_Tours.service.impl.TourServiceImpl;
import com.example.InstaPay_Travel_Tours.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
@CrossOrigin(origins = "*")
public class TourController {

    @Autowired
    private TourServiceImpl tourService;

    // Endpoint for saving the tour and images
    @PostMapping(value = "save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveTour(@RequestPart("tour") TourDTO tourDTO, @RequestPart(value = "images", required = false) MultipartFile[] images) {
        // Add tour to the service
        tourService.addTour(tourDTO);

        List<String> imagePaths = new ArrayList<>();
        if (images != null) {
            for (MultipartFile image : images) {
                String fileName = StringUtils.cleanPath(image.getOriginalFilename());
                try {
                    String imagePath = saveImage(fileName, image);
                    imagePaths.add(imagePath);
                } catch (IOException e) {
                    return new ResponseUtil(500, "Image upload failed", null);
                }
            }
        }

        return new ResponseUtil(201, "Tour Saved", imagePaths);
    }

    // Endpoint to get all tours
    @GetMapping("getAll")
    public List<TourDTO> getAllTours() {
        return tourService.getAllTours();
    }

    // Endpoint for updating a tour
    @PutMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateTour(@RequestBody TourDTO tourDTO) {
        tourService.updateTour(tourDTO);
        return new ResponseUtil(200, "Tour Updated", null);
    }

    // Endpoint for deleting a tour by ID
    @DeleteMapping("delete/{tourID}")
    public ResponseUtil deleteTour(@PathVariable("tourID") String tourID) {
        tourService.deleteTour(Integer.parseInt(tourID));
        return new ResponseUtil(200, "Tour deleted", null);
    }

    // Method for saving images to a specific directory
    private String saveImage(String fileName, MultipartFile image) throws IOException {
        // Update the directory to be outside of the resources folder for better portability
        String directoryPath = "D:/Users/kavee/IdeaProjects/InstaPay-Travel_Tours/InstaPay-Travel_Tours2/src/main/resources/static/images"; // Set to an appropriate directory

        // Create the directory if it doesn't exist
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs(); // Creates the directory if it doesn't exist
        }

        // Save the file in the directory
        File file = new File(directory, fileName);
        image.transferTo(file);

        // Return the URL path that can be accessed
        return "/images/" + fileName;
    }
}
