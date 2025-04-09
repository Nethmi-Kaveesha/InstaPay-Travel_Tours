package com.example.InstaPay_Travel_Tours.service;

import com.example.InstaPay_Travel_Tours.entity.GalleryItem;
import com.example.InstaPay_Travel_Tours.repo.GalleryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GalleryItemService {

    @Autowired
    private GalleryItemRepository galleryItemRepository;

    public List<GalleryItem> getAllGalleryItems() {
        return galleryItemRepository.findAll();
    }

    public List<GalleryItem> getFeaturedGalleryItems() {
        return galleryItemRepository.findByFeaturedTrue();
    }

    public List<GalleryItem> getGalleryItemsByCategory(String category) {
        return galleryItemRepository.findByCategory(category);
    }

    public GalleryItem saveGalleryItem(GalleryItem galleryItem) {
        return galleryItemRepository.save(galleryItem);
    }
}
