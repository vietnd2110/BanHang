package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.contants.FolderContants;
import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.entity.UserEntity;
import com.example.udpm14sellcomputerpartsbackend.repository.UserRepository;
import com.example.udpm14sellcomputerpartsbackend.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CloudinaryServiceImpl cloudinaryService;

    public UserServiceImpl(
            UserRepository userRepository,
            CloudinaryServiceImpl cloudinaryService
    ){
        this.userRepository = userRepository;
        this.cloudinaryService =cloudinaryService;
    }

    @Override
    public Optional<UserEntity> findById(Long id){
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(()->new NotFoundException(HttpStatus.NOT_FOUND.value(), "User id not found: " + id));
        return userRepository.findById(id);
    }

    @Override
    public String updateImage(Long id, MultipartFile file){
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(()->new NotFoundException(HttpStatus.NOT_FOUND.value(), "User id not found: " + id));

        String images = null;
        if(file.isEmpty()){
            images = "https://res.cloudinary.com/ducnd1306/image/upload/v1663338173/sample.jpg";
        }else{
            images = cloudinaryService.uploadImage(file, FolderContants.AVATARS_IMAGES_FOLDER);
        }
        userEntity.setImage(images);
        userRepository.save(userEntity);
        return images;
    }

    @Override
    public Page<UserEntity> findAllByStaff(Integer pageSize, Integer pageNumber) {
        return userRepository.findAllByStaff(PageRequest.of(pageSize, pageNumber));
    }

    @Override
    public Page<UserEntity> findAllByAdmin(Integer pageSize, Integer pageNumber) {
        return userRepository.findAllByAdmin(PageRequest.of(pageSize, pageNumber));
    }

    @Override
    public Page<UserEntity> findAllByCustomer(Integer pageSize, Integer pageNumber) {
        return userRepository.findAllByCustomer(PageRequest.of(pageSize, pageNumber));
    }


}
