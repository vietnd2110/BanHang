package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.entity.UserEntity;
import com.example.udpm14sellcomputerpartsbackend.payload.request.ChangePassword;
import com.example.udpm14sellcomputerpartsbackend.payload.request.ForgotPassword;
import com.example.udpm14sellcomputerpartsbackend.payload.request.UserRegister;
import com.example.udpm14sellcomputerpartsbackend.payload.response.BaseResponse;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    UserRegister registerAccount(UserRegister userRegister, StringBuffer siteUrl) throws MessagingException;

    boolean verifiCode(String code);
    BaseResponse changePassword(ChangePassword userEntity);
    BaseResponse forgotPassword(ForgotPassword forgotPassword);

}
