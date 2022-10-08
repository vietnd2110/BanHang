package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.contants.RoleEnum;
import com.example.udpm14sellcomputerpartsbackend.contants.UserStatusEnum;
import com.example.udpm14sellcomputerpartsbackend.model.entity.UserEntity;
import com.example.udpm14sellcomputerpartsbackend.payload.request.UserRegister;
import com.example.udpm14sellcomputerpartsbackend.repository.UserRepository;
import com.example.udpm14sellcomputerpartsbackend.service.MailService;
import com.example.udpm14sellcomputerpartsbackend.service.UserService;
import net.bytebuddy.utility.RandomString;
import org.modelmapper.ModelMapper;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    //    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper
//                           PasswordEncoder passwordEncoder
            , MailService mailService
    ) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
//        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    @Override
    public UserRegister registerAccount(UserRegister userRegister, StringBuffer siteUrl) throws MessagingException {
        Optional<UserEntity> findByUsername = userRepository.findByUsername(userRegister.getUsername());
        if (findByUsername.isPresent()) return null;

        Optional<UserEntity> findByEmail = userRepository.findByEmail(userRegister.getEmail());
        if (findByEmail.isPresent()) return null;

        UserEntity userEntity = modelMapper.map(userRegister, UserEntity.class);
//        userEntity.setUsername(userRegister.getUsername());
//        userEntity.setFullname(userRegister.getFullname());
//        userEntity.setEmail(userRegister.getEmail());
//        userEntity.setPassword(passwordEncoder.encode(userRegister.getPassword()));

        userEntity.setPassword(userRegister.getPassword());
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

}
