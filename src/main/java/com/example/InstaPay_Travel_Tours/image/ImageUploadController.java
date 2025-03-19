package com.example.InstaPay_Travel_Tours.image;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@RequestMapping("api/v1/img")
@RestController
public class ImageUploadController {


    @PostMapping("/upload")
    public void saveImage(@RequestParam("file")MultipartFile[] files){
        String uploadDir="image";
        Arrays.asList(files).stream().forEach(file->{
            String fileName= StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            System.out.println(fileName);
            try {
                FileUploadUtil.saveFile(uploadDir,fileName,file);
            }catch (IOException ioException){

            }
        });

    }


}