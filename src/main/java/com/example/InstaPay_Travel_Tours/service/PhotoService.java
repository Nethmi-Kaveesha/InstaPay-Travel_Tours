package com.example.InstaPay_Travel_Tours.service;

import com.example.InstaPay_Travel_Tours.entity.Photo;
import com.example.InstaPay_Travel_Tours.repo.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    public boolean uploadPhoto(Photo photo) {
        try {
            photoRepository.save(photo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Photo> getFeaturedPhotos() {
        return photoRepository.findByIsFeatured(true);
    }

    public List<Photo> getPhotosByCategory(String category) {
        return photoRepository.findByCategory(category);
    }


    public boolean deletePhoto(Long id) {
        try {
            Optional<Photo> photo = photoRepository.findById(id);
            if (photo.isPresent()) {
                photoRepository.delete(photo.get());
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
