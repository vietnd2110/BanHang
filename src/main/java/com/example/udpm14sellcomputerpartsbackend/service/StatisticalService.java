package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe.StatisticalDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe.ThongKeDto;

import java.util.List;

public interface StatisticalService {
    List<StatisticalDto> listHoaDonCacNam();

    List<StatisticalDto> listThongKeTheoNam(Integer year);

    List<ThongKeDto> listThongKeTheoThangVaNam(Integer year, Integer month);
}
