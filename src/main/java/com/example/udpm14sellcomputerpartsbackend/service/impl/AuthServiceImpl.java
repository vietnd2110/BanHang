package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.contants.RoleEnum;
import com.example.udpm14sellcomputerpartsbackend.contants.StatusEnum;
import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.entity.UserEntity;
import com.example.udpm14sellcomputerpartsbackend.payload.request.ChangePassword;
import com.example.udpm14sellcomputerpartsbackend.payload.request.ForgotPassword;
import com.example.udpm14sellcomputerpartsbackend.payload.request.UserRegister;
import com.example.udpm14sellcomputerpartsbackend.payload.response.BaseResponse;
import com.example.udpm14sellcomputerpartsbackend.repository.UserRepository;
import com.example.udpm14sellcomputerpartsbackend.service.MailService;
import com.example.udpm14sellcomputerpartsbackend.service.AuthService;
import com.example.udpm14sellcomputerpartsbackend.ultil.HashUtil;
import net.bytebuddy.utility.RandomString;
import org.modelmapper.ModelMapper;


import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    public AuthServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder

            , MailService mailService
    ) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    @Override
    public UserRegister registerAccount(UserRegister userRegister, StringBuffer siteUrl) throws MessagingException {
        Optional<UserEntity> findByUsername = userRepository.findByUsername(userRegister.getUsername());
        if (findByUsername.isPresent()) return null;

        Optional<UserEntity> findByEmail = userRepository.findByEmail(userRegister.getEmail());
        if (findByEmail.isPresent()) return null;

        UserEntity userEntity = modelMapper.map(userRegister, UserEntity.class);
        userEntity.setUsername(userRegister.getUsername());
        userEntity.setFullname(userRegister.getFullname());
        userEntity.setEmail(userRegister.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userRegister.getPassword()));
        userEntity.setRole(RoleEnum.CUSTOMER);
        userEntity.setVerificationCode(RandomString.make(16));
        userEntity.setStatus(StatusEnum.ACTIVE);
        userEntity.setProvince(userRegister.getProvince());
        userEntity.setDistrict(userRegister.getDistrict());
        userEntity.setWard(userRegister.getWard());
        userEntity.setImage("https://res.cloudinary.com/ducnd1306/image/upload/v1673278507/sell-computer/images/avatars/qmyarums8lrrqamrqorv.jpg");

        userRegister = modelMapper.map(userRepository.save(userEntity),UserRegister.class);

        Map<String, Object> props = new HashMap<>();
        props.put("name", userEntity.getUsername());
        props.put("url", siteUrl.append(userEntity.getVerificationCode()).toString());

//        mailService.sendMail(props, userEntity.getEmail(), "sendEmail", "XÁC THỰC TÀI KHOẢN");

        return userRegister;
    }

    @Override
    public boolean verifiCode(String code) {
        Optional<UserEntity> findByVerificode = userRepository.findByVerificationCode(code);
        if (!findByVerificode.isPresent()) return false;

        UserEntity userEntity = findByVerificode.get();
        userEntity.setVerificationCode(null);
        userRepository.save(userEntity);

        return true;
    }

    @Override
    public Optional<UserEntity> findByUserId(Long id){
        Optional<UserEntity> user = Optional.ofNullable(userRepository.findById(id).
                orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "User id not found: " + id)));
        return user;
    }

    @Override
    public BaseResponse changePassword(ChangePassword changePassword) {
        Optional<UserEntity> user = userRepository.findByUsername(changePassword.getUsername());
        UserEntity userEntity = user.get();
        if (!passwordEncoder.matches(changePassword.getPassOld(), userEntity.getPassword())) {
            return BaseResponse.error("Mật khẩu cũ không chính xác");
        } else if (!changePassword.getPassConfirm().equals(changePassword.getPassNew())) {
            return BaseResponse.error("Mật khẩu xác nhận không khớp");
        } else if (passwordEncoder.matches(changePassword.getPassNew(), userEntity.getPassword())) {
            return BaseResponse.error("Mật khẩu mới trùng với mật khẩu cũ");
        } else {
            if (changePassword.getPassNew().length() < 6) {
                return BaseResponse.error("Mật khẩu phải lớn hơn 6 kí tự");
            } else {
                userEntity.setPassword(passwordEncoder.encode(changePassword.getPassNew()));
                UserEntity userResponse = userRepository.save(userEntity);
                return BaseResponse.success("Đổi mật khẩu thành công").withData(userResponse);
            }
        }
    }

    @Override
    public BaseResponse forgotPassword(ForgotPassword forgotPassword) {
        try {
            Optional<UserEntity> user = userRepository.findByEmail(forgotPassword.getEmail());
            UserEntity userEntity = user.get();
            String so = RequiredString(6);
            userEntity.setPassword(HashUtil.hash(so));
            userRepository.save(userEntity);
            mailService.forgotEmail(userEntity.getEmail(), "Hay nhớ mật khẩu nhé bạn", userEntity.getUsername(), so);
            return BaseResponse.success("Vui lòng kiểm tra email của bạn");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BaseResponse.error("Email không tồn tại");
    }

    public static String RequiredString(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder s = new StringBuilder(n);
        int y;
        for (y = 0; y < n; y++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            s.append(AlphaNumericString.charAt(index));
        }
        return s.toString();
    }

}
