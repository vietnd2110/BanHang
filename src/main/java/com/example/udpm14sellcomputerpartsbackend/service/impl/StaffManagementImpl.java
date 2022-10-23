package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.contants.RoleEnum;
import com.example.udpm14sellcomputerpartsbackend.contants.StatusEnum;
import com.example.udpm14sellcomputerpartsbackend.exception.BadRequestException;
import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.StaffDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.StaffEntity;
import com.example.udpm14sellcomputerpartsbackend.repository.StaffManagementRepository;
import com.example.udpm14sellcomputerpartsbackend.service.StaffManagementService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StaffManagementImpl implements StaffManagementService {

    private final ModelMapper modelMapper;

    private final StaffManagementRepository staffManagementRepository;

    @Override
    public List<StaffEntity> getAll() {
        return staffManagementRepository.findAll();
    }

    @Override
    public StaffDto create(StaffDto staffDto) {
        StaffEntity staffEntity = modelMapper.map(staffDto,StaffEntity.class);
        StaffEntity findByName = staffManagementRepository.findByUsername(staffEntity.getUsername());
        if(findByName != null){
            throw new BadRequestException("Username đã tồn tại");
        }
        StaffEntity findByEmail = staffManagementRepository.findByEmail(staffEntity.getEmail());
        if(findByEmail != null){
            throw new BadRequestException("Email đã tồn tại");
        }
        staffEntity.setFullname(staffDto.getFullname());
        staffEntity.setUsername(staffDto.getUsername());
        staffEntity.setPassword(staffDto.getPassword());
        staffEntity.setEmail(staffDto.getEmail());
        staffEntity.setPhone(staffDto.getPhone());
        staffEntity.setStatus(StatusEnum.ACTIVE);
        staffEntity.setAddress(staffDto.getAddress());
        staffEntity.setRole(RoleEnum.STAFF);
        staffManagementRepository.save(staffEntity);
        return staffDto;
    }

    @Override
    public StaffDto update(Long id, StaffDto staffDto) {
        Optional<StaffEntity> findById = staffManagementRepository.findById(id);
        StaffEntity staffEntity =  modelMapper.map(staffDto,StaffEntity.class);
          staffEntity.setId(findById.get().getId());
        System.out.println(findById.get().getId() + "!@312312312312");
          staffEntity.setUsername(findById.get().getUsername());
          staffEntity.setPassword(findById.get().getPassword());
          staffEntity.setStatus(findById.get().getStatus());
          staffEntity.setRole(findById.get().getRole());
//        //findById.setFullname(staffDto.getFullname());
//        staffEntity.setUsername(findById.getUsername());
//        staffEntity.setPassword(findById.getPassword());
//        findById.setEmail(staffDto.getEmail());
//        findById.setPhone(staffDto.getPhone());
//        staffEntity.setStatus(findById.getStatus());
//        findById.setAddress(staffDto.getAddress());
//        staffEntity.setRole(findById.getRole());
        return modelMapper.map(staffManagementRepository.save(staffEntity), StaffDto.class);
    }

    @Override
    public void delete(Long id) {
        StaffEntity staffEntity = staffManagementRepository.findById(id).
                orElseThrow(()->new NotFoundException(HttpStatus.NOT_FOUND.value(), "Staff id not found: " + id));
        staffManagementRepository.deleteById(staffEntity.getId());
    }

}
