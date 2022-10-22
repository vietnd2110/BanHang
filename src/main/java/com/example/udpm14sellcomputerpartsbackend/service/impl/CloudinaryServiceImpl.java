package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.exception.WrongFomatException;
import com.example.udpm14sellcomputerpartsbackend.repository.ImagesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Service
public class CloudinaryServiceImpl {

    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(
            Cloudinary cloudinary
    ) {
        this.cloudinary = cloudinary;
    }

    public String uploadImage(MultipartFile file, String folder) {
        try {
            if (Objects.requireNonNull(file.getContentType()).contains("image")) {
                Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                        "folder", folder
                ));
                return this.getUrlImage(uploadResult.get("public_id").toString());
            }else{
                throw new WrongFomatException("file is not an image");
            }
        }catch (IOException e){
            throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "File not found");
        }


    }

    public String getUrlImage(String publicId) {
        System.out.println(cloudinary.url().generate(publicId) + "get url image");
        return cloudinary.url().generate(publicId);
    }


}
