package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.contants.FolderContants;
import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.InfoManagementDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.UserEntity;
import com.example.udpm14sellcomputerpartsbackend.repository.UserRepository;
import com.example.udpm14sellcomputerpartsbackend.security.CustomerDetailService;
import com.example.udpm14sellcomputerpartsbackend.service.InfoMangementService;
import com.example.udpm14sellcomputerpartsbackend.ultil.CurrentUserUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InfoMangementServiceImpl implements InfoMangementService {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CloudinaryServiceImpl cloudinaryService;


    @Override
    public InfoManagementDto updateInfo(Long id, InfoManagementDto userDto, MultipartFile file) {

        CustomerDetailService detailService = CurrentUserUtils.getCurrentUserUtils();
        System.out.println(detailService.getUsername() + "username" + "" + detailService.getAuthorities());

        UserEntity findById = userRepository.findById(id).
                orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "User id not found: " + id));

        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);

        userEntity.setId(findById.getId());
        userEntity.setUsername(findById.getUsername());
        userEntity.setRole(findById.getRole());
//        userEntity.setDistrict(userDto.getDistrict());
//        userEntity.setProvince(userDto.getProvince());
//        userEntity.setWard(userDto.getWard());
//        userEntity.setFullname(userDto.getFullname());
//        userEntity.setEmail(userDto.getEmail());
//        userEntity.setAddress(userDto.getAddress());

        String images = "";
        if (file != null) {
            images = cloudinaryService.uploadImage(file, FolderContants.AVATARS_IMAGES_FOLDER);
        } else {
            images = "https://res.cloudinary.com/ducnd1306/image/upload/v1667716002/sell-computer/images/avatars/eibisxdae7a3cysgkhpl.jpg";
        }

        userEntity.setImage(images);
        userEntity.setStatus(findById.getStatus());
        userEntity.setPassword(findById.getPassword());

        return modelMapper.map(userRepository.save(userEntity), InfoManagementDto.class);
    }

    @Override
    public List<UserEntity> getInfoUser() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findByUserId(Long id) {
        Optional<UserEntity> user = Optional.ofNullable(userRepository.findById(id).
                orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "User id not found: " + id)));
        return user;
    }
}
