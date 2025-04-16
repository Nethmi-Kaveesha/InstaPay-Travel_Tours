package com.example.InstaPay_Travel_Tours.service;

import com.example.InstaPay_Travel_Tours.model.Image;
import com.example.InstaPay_Travel_Tours.repo.ImageRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public void saveImageFromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            byte[] imageBytes = IOUtils.toByteArray(inputStream);

            Image image = new Image();
            image.setImageData(imageBytes);
            imageRepository.save(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
