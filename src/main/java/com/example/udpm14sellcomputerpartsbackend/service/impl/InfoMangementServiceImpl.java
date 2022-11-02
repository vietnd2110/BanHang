package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.InfoManagementDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.UserEntity;
import com.example.udpm14sellcomputerpartsbackend.repository.UserRepository;
import com.example.udpm14sellcomputerpartsbackend.security.CustomerDetailService;
import com.example.udpm14sellcomputerpartsbackend.service.InfoMangementService;
import com.example.udpm14sellcomputerpartsbackend.ultil.CurrentUserUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InfoMangementServiceImpl implements InfoMangementService {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    @Override
    public InfoManagementDto updateInfo(Long id, InfoManagementDto userdto) {

        CustomerDetailService detailService = CurrentUserUtils.getCurrentUserUtils();
        System.out.println(detailService.getUsername() + "username" +"" + detailService.getAuthorities());

        UserEntity findById = userRepository.findById(id).
                orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "User id not found: " + id));

        UserEntity userEntity =  modelMapper.map(userdto,UserEntity.class);

        userEntity.setId(findById.getId());
        userEntity.setUsername(findById.getUsername());
        userEntity.setRole(findById.getRole());
        userEntity.setStatus(findById.getStatus());
        userEntity.setPassword(findById.getPassword());

        return modelMapper.map(userRepository.save(userEntity),InfoManagementDto.class);
    }

    @Override
    public List<UserEntity> getInfoUser() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findByUserId(Long id) {
        Optional<UserEntity> user = Optional.ofNullable(userRepository.findById(id).
                orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "User id not found: " + id)));
        return user;
    }
}
