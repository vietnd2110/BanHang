package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.contants.OrderStatusEnum;
import com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe.StatisticalDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe.ThongKeThangVaNamDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe.ThongKeTrangThaiDonHang;
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
    public List<ThongKeThangVaNamDto> listHoaDonCacThangVaNam() {
        return orderRepository.listHoaDonThangVaNam();
    }

    @Override
    public ThongKeTrangThaiDonHang thongKeTrangThaiDonHang() {
        ThongKeTrangThaiDonHang thongKeTrangThaiDonHang = new ThongKeTrangThaiDonHang();
        thongKeTrangThaiDonHang.setSoDonChoXacNhan(orderRepository.thongKeTrangThaiDonHang(OrderStatusEnum.CHOXACNHAN));
        thongKeTrangThaiDonHang.setSoDonDangXuLy(orderRepository.thongKeTrangThaiDonHang(OrderStatusEnum.DANGXULY));
        thongKeTrangThaiDonHang.setSoDonDangVanChuyen(orderRepository.thongKeTrangThaiDonHang(OrderStatusEnum.DANGVANCHUYEN));
        thongKeTrangThaiDonHang.setSoDonDaGiao(orderRepository.thongKeTrangThaiDonHang(OrderStatusEnum.DAGIAO));
        thongKeTrangThaiDonHang.setSoDonDaHuy(orderRepository.thongKeTrangThaiDonHang(OrderStatusEnum.DAHUY));
        thongKeTrangThaiDonHang.setSoDonDaHoanThanh(orderRepository.thongKeTrangThaiDonHang(OrderStatusEnum.DAHOANTHANH));

        return thongKeTrangThaiDonHang;
    }
}
