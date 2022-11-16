package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.PaymentDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.PaymentEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PaymentService {
    List<PaymentDto> paymentEntityList();

    PaymentDto getOnePayment(Long id);

    PaymentDto create(PaymentDto paymentDto, MultipartFile file);

    PaymentDto update(Long id, PaymentDto paymentDto, MultipartFile file);

    void delete(Long id);
}
