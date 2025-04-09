package com.example.InstaPay_Travel_Tours.controller;

import com.example.InstaPay_Travel_Tours.entity.GalleryItem;
import com.example.InstaPay_Travel_Tours.service.GalleryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gallery")
public class GalleryController {

    @Autowired
    private GalleryItemService galleryItemService;

    @GetMapping
    public List<GalleryItem> getAllGalleryItems() {
        return galleryItemService.getAllGalleryItems();
    }

    @GetMapping("/featured")
    public List<GalleryItem> getFeaturedGalleryItems() {
        return galleryItemService.getFeaturedGalleryItems();
    }

    @GetMapping("/category/{category}")
    public List<GalleryItem> getGalleryItemsByCategory(@PathVariable String category) {
        return galleryItemService.getGalleryItemsByCategory(category);
    }

    @PostMapping
    public GalleryItem uploadGalleryItem(@RequestBody GalleryItem galleryItem) {
        return galleryItemService.saveGalleryItem(galleryItem);
    }
}
