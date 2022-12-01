package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe.StatisticalDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe.ThongKeDto;
import com.example.udpm14sellcomputerpartsbackend.repository.OrderRepository;
import com.example.udpm14sellcomputerpartsbackend.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticalServiceImpl implements StatisticalService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<StatisticalDto> listHoaDonCacNam() {
        return orderRepository.listHoaDonCacNam();
    }

    @Override
    public List<StatisticalDto> listThongKeTheoNam(Integer year) {
        return orderRepository.listHoaDonTheoNam(year);
    }

    @Override
    public List<ThongKeDto> listThongKeTheoThangVaNam(Integer year, Integer month) {
        return orderRepository.listHoaDonTheoThang(year, month);
    }
}
