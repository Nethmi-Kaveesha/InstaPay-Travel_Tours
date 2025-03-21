package com.example.InstaPay_Travel_Tours.image;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUploadUtil {

    private static final Logger logger = Logger.getLogger(FileUploadUtil.class.getName());

    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {

        // Correct the path based on your directory
        Path uploadPath = Paths.get("D:\\Users\\kavee\\IdeaProjects\\InstaPay-Travel_Tours\\InstaPay-Travel_Tours2\\src\\main\\resources\\static\\" + uploadDir);

        // Check if the directory exists, create it if it doesn't
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
                logger.info("Directory created: " + uploadPath.toString());
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Could not create directory: " + uploadPath.toString(), e);
                throw new IOException("Failed to create upload directory", e);
            }
        }

        // Save the file to the specified directory
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            logger.info("File saved successfully: " + filePath.toString());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving file: " + fileName, e);
            throw new IOException("Error saving file", e);
        }
    }
}
