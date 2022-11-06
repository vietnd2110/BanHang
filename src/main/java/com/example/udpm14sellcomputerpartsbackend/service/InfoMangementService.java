package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.InfoManagementDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;


public interface InfoMangementService {
    InfoManagementDto updateInfo(Long id, InfoManagementDto userEntity, MultipartFile file) ;

    List<UserEntity> getInfoUser() throws MessagingException;

    Optional<UserEntity> findByUserId(Long id);
}
