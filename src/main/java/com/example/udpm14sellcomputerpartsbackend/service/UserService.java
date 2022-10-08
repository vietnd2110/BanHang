package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.entity.UserEntity;
import com.example.udpm14sellcomputerpartsbackend.payload.request.UserRegister;

import javax.mail.MessagingException;
import java.util.Optional;

public interface UserService {
    UserRegister registerAccount(UserRegister userRegister, StringBuffer siteUrl) throws MessagingException;

    boolean verifiCode(String code);
    Optional<UserEntity> findByUserName(String userName);
}
