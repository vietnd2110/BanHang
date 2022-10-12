package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.contants.RoleEnum;
import com.example.udpm14sellcomputerpartsbackend.contants.UserStatusEnum;
import com.example.udpm14sellcomputerpartsbackend.model.entity.UserEntity;
import com.example.udpm14sellcomputerpartsbackend.payload.request.ChangePassword;
import com.example.udpm14sellcomputerpartsbackend.payload.request.ForgotPassword;
import com.example.udpm14sellcomputerpartsbackend.payload.request.UserRegister;
import com.example.udpm14sellcomputerpartsbackend.payload.response.BaseResponse;
import com.example.udpm14sellcomputerpartsbackend.repository.UserRepository;
import com.example.udpm14sellcomputerpartsbackend.service.MailService;
import com.example.udpm14sellcomputerpartsbackend.service.UserService;
import com.example.udpm14sellcomputerpartsbackend.ultil.HashUtil;
import net.bytebuddy.utility.RandomString;
import org.modelmapper.ModelMapper;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
        private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper
                           ,PasswordEncoder passwordEncoder
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
        userEntity.setStatus(UserStatusEnum.ACTIVE);

        userRepository.save(userEntity);

        Map<String, Object> props = new HashMap<>();
        props.put("name", userEntity.getUsername());
        props.put("url", siteUrl.append(userEntity.getVerificationCode()).toString());

        mailService.sendMail(props, userEntity.getEmail(), "sendEmail", "XÁC THỰC TÀI KHOẢN");

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
    public BaseResponse changePassword(ChangePassword changePassword) {
        Optional<UserEntity> user = userRepository.findByUsername(changePassword.getUserName());
        UserEntity userEntity = user.get();
        if (HashUtil.verify(changePassword.getPassOld(), userEntity.getPassword()) == false) {
            return BaseResponse.error("Mật khẩu cũ không chính xác");
        } else if (!changePassword.getPassConfirm().equals(changePassword.getPassNew())) {
            return BaseResponse.error("Mật khẩu xác nhận không khớp");
        } else if (HashUtil.verify(changePassword.getPassNew(), userEntity.getPassword())) {
            return BaseResponse.error("Mật khẩu mới trùng với mật khẩu cũ");
        } else {
            if (changePassword.getPassNew().length() <= 6) {
                return BaseResponse.error("Mật khẩu phải lớn hơn 6 kí tự");
            } else {
                userEntity.setPassword(HashUtil.hash(changePassword.getPassNew()));
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
