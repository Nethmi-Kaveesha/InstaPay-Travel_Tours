package com.example.InstaPay_Travel_Tours.image;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;

public class FileUploadUtil {

    // Upload path from application.properties or default relative path
    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        // Replace this with a relative path or configuration property if needed
        Path uploadPath = Paths.get("src/main/resources/static/image", uploadDir);

        // Create directories if they don't exist
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Copy the file to the specified path
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            System.out.println("Image loaded: " + filePath.toFile().getAbsolutePath());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("Error uploading file: " + e.getMessage());
            throw new IOException("Could not save file: " + fileName, e);
        }
    }
}
