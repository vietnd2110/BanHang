package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.entity.UserEntity;
import com.example.udpm14sellcomputerpartsbackend.payload.request.ChangePassword;
import com.example.udpm14sellcomputerpartsbackend.payload.request.ForgotPassword;
import com.example.udpm14sellcomputerpartsbackend.payload.request.UserRegister;
import com.example.udpm14sellcomputerpartsbackend.payload.response.BaseResponse;

import javax.mail.MessagingException;
import java.util.Optional;

public interface AuthService {
    UserRegister registerAccount(UserRegister userRegister, StringBuffer siteUrl) throws MessagingException;

    boolean verifiCode(String code);

    Optional<UserEntity> findByUserId(Long id);

    BaseResponse changePassword(ChangePassword userEntity);
    BaseResponse forgotPassword(ForgotPassword forgotPassword);

}
