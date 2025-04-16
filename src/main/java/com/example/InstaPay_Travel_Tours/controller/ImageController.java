package com.example.InstaPay_Travel_Tours.controller;

import com.example.InstaPay_Travel_Tours.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/save")
    public ResponseEntity<String> saveImage(@RequestParam("imageUrl") String imageUrl) {
        imageService.saveImageFromUrl(imageUrl);
        return ResponseEntity.ok("Image Saved Successfully!");
    }
}
