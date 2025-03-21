package com.example.InstaPay_Travel_Tours.image;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestMapping("api/v1/img")
@RestController
public class ImageUploadController {

    private static final Logger logger = Logger.getLogger(ImageUploadController.class.getName());

    @PostMapping("/upload")
    public String saveImage(@RequestParam("files") MultipartFile[] files) {
        String uploadDir = "image";  // This matches your path configuration

        // Process each file in the multipart array
        Arrays.asList(files).forEach(file -> {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            logger.info("Received file: " + fileName);

            try {
                FileUploadUtil.saveFile(uploadDir, fileName, file);
                logger.info("File saved successfully: " + fileName);
            } catch (IOException ioException) {
                logger.log(Level.SEVERE, "Error saving file: " + fileName, ioException);
            }
        });

        return "Files uploaded successfully!";
    }
}
