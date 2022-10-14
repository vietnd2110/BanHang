package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.contants.RoleEnum;
import com.example.udpm14sellcomputerpartsbackend.contants.StatusEnum;
import com.example.udpm14sellcomputerpartsbackend.model.entity.UserEntity;
import com.example.udpm14sellcomputerpartsbackend.repository.UserRepository;
import com.example.udpm14sellcomputerpartsbackend.service.InfoMangementService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InfoMangementServiceImpl implements InfoMangementService {

    private final UserRepository userRepository;


    @Override
        public UserEntity updateInfo(Long id, UserEntity userEntity) throws MessagingException {
        Optional<UserEntity> findById = userRepository.findById(id);
        if (!findById.isPresent()){
            return null;
        }
        userEntity.setId(id);
        userEntity.setRole(RoleEnum.CUSTOMER);
        userEntity.setStatus(StatusEnum.ACTIVE);
        userEntity.setPassword("abccccc");
        return userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> getInfoUser() {
        return userRepository.findAll();
    }
}
