package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.contants.ResponseStatusContants;
import com.example.udpm14sellcomputerpartsbackend.contants.StatusEnum;
import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.exception.ObjectNotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.VoucherDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.VoucherEntity;
import com.example.udpm14sellcomputerpartsbackend.repository.VoucherRepository;
import com.example.udpm14sellcomputerpartsbackend.service.VoucherService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoucherServiceImpl implements VoucherService {

    private final VoucherRepository voucherRepository;
    private final ModelMapper modelMapper;

    public VoucherServiceImpl(VoucherRepository voucherRepository, ModelMapper modelMapper) {
        this.voucherRepository = voucherRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<VoucherDto> getAll() {
        List<VoucherEntity> voucherEntityList = voucherRepository.findAll();
        return voucherEntityList.stream().map(voucherEntity ->
                modelMapper.map(voucherEntity, VoucherDto.class)).collect(Collectors.toList());
    }

    @Override
    public VoucherDto create(VoucherDto voucherDto) {
        LocalDateTime start = voucherDto.getStartDate();
        LocalDateTime end = voucherDto.getEndDate();
        if (start.isAfter(end)) {
            throw new ObjectNotFoundException(ResponseStatusContants.FORMAT_DATE);
        } else {
            String random = RequiredString(5);
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = formatter.format(date);

            VoucherEntity voucherEntity = modelMapper.map(voucherDto, VoucherEntity.class);
            voucherEntity.setCode(strDate + "_" + random);
            voucherEntity.setStatus(StatusEnum.ACTIVE);

            return modelMapper.map(voucherRepository.save(voucherEntity), VoucherDto.class);
        }
    }

    @Override
    public VoucherDto update(Long id, VoucherDto voucherDto) {
        VoucherEntity findById = voucherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Voucher id not found:" + id));
        LocalDateTime start = voucherDto.getStartDate();
        LocalDateTime end = voucherDto.getEndDate();
        if (start.isAfter(end)) {
            throw new ObjectNotFoundException(ResponseStatusContants.FORMAT_DATE);
        } else {
            VoucherEntity voucherEntity = modelMapper.map(voucherDto, VoucherEntity.class);
            voucherEntity.setId(findById.getId());
            voucherEntity.setCode(findById.getCode());
            voucherEntity.setStatus(StatusEnum.ACTIVE);

            return modelMapper.map(voucherRepository.save(voucherEntity), VoucherDto.class);
        }
    }

    @Override
    public void delete(Long id) {
        VoucherEntity findById = voucherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Voucher id not found:" + id));
        voucherRepository.deleteById(findById.getId());
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