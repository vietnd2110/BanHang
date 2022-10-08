package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.entity.UserEntity;

import javax.mail.MessagingException;
import java.util.List;


public interface InfoMangementService {
    UserEntity updateInfo(Long id, UserEntity userEntity) throws MessagingException;

    List<UserEntity> getInfoUser() throws MessagingException;
}
