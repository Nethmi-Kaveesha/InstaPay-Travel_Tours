package com.example.InstaPay_Travel_Tours.image;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RequestMapping("api/v1/img")
@RestController
public class ImageUploadController {

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> saveImage(@RequestParam("file") MultipartFile[] files) {
        String uploadDir = "image";  // Directory to save images
        List<String> uploadedFiles = new ArrayList<>();
        List<String> failedFiles = new ArrayList<>();

        Arrays.stream(files).forEach(file -> {
            // Clean the original filename and generate a new unique filename
            String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            String fileName = UUID.randomUUID() + "_" + originalFileName; // Avoid filename conflicts

            try {
                // Call the FileUploadUtil to save the file
                FileUploadUtil.saveFile(uploadDir, fileName, file);
                uploadedFiles.add(fileName);  // Add to the successful upload list
            } catch (IOException e) {
                failedFiles.add(originalFileName);  // Add to the failed upload list
                e.printStackTrace();  // Log the error (could use a logging framework like SLF4J)
            }
        });

        // Prepare the response map
        Map<String, Object> response = new HashMap<>();
        response.put("success", uploadedFiles);  // List of successfully uploaded files
        response.put("failed", failedFiles);    // List of failed uploads

        return new ResponseEntity<>(response, HttpStatus.OK);  // Return the response with status 200 OK
    }
}
