package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.export.ExportPdfOrderDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.OrderDetailEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.OrderEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ProductEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.UserEntity;
import com.example.udpm14sellcomputerpartsbackend.repository.OrderDetailRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.OrderRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.ProductRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.UserRepository;
import com.example.udpm14sellcomputerpartsbackend.service.ExportService;
import com.example.udpm14sellcomputerpartsbackend.ultil.ExportOrderPdfUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExportServiceImpl implements ExportService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ByteArrayInputStream exportPdfOrder(Long idOrder) {
        OrderEntity orderEntity = orderRepository.findById(idOrder).orElseThrow(()
                -> new NotFoundException(HttpStatus.NOT_FOUND.value(),"Hoá đơn không tồn tại:"+idOrder));
        Long total = orderEntity.getGrandTotal();
        List<OrderDetailEntity> orderDetailEntityLists = orderDetailRepository.findAllByOrderId(orderEntity.getId());
        //UserEntity userEntity = userRepository.findById(orderEntity.getAccountId()).orElse(null);

        List<ExportPdfOrderDto> exportPdfOrderDtoList = new ArrayList<>();

        orderDetailEntityLists.forEach(orderDetailEntity -> {
            ProductEntity productEntity = productRepository.findById(orderDetailEntity.getProductId()).get();
            ExportPdfOrderDto exportPdfOrderDto = new ExportPdfOrderDto();
            exportPdfOrderDto.setCode(productEntity.getCode());
            exportPdfOrderDto.setName(productEntity.getName());
            exportPdfOrderDto.setQuantity(orderDetailEntity.getQuantity());
            exportPdfOrderDto.setPrice(orderDetailEntity.getPrice());
            exportPdfOrderDto.setTotal(orderDetailEntity.getTotal());
            exportPdfOrderDto.setDescription(orderEntity.getDescription());
            exportPdfOrderDtoList.add(exportPdfOrderDto);
        });
        ExportOrderPdfUtils exportOrderPdfUtils = new ExportOrderPdfUtils();
        return exportOrderPdfUtils.exportPdf(exportPdfOrderDtoList, total, orderEntity.getFullname(), orderEntity.getAddress(), orderEntity.getDescription(), orderEntity.getMahd());
    }
}
