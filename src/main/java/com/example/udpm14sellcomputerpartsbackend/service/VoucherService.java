package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.VoucherDto;

import java.util.List;

public interface VoucherService {

    List<VoucherDto> getAll();

    VoucherDto create(VoucherDto voucherDto);

    VoucherDto update(Long id, VoucherDto voucherDto);

    void delete(Long id);

}