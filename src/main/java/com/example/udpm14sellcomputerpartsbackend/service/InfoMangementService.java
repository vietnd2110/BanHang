package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.InfoManagementDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.UserEntity;

import javax.mail.MessagingException;
import java.util.List;


public interface InfoMangementService {
    InfoManagementDto updateInfo(Long id, InfoManagementDto userEntity) ;

    List<UserEntity> getInfoUser() throws MessagingException;
}
