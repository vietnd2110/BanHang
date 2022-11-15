package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.OrderDetailDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.OrderEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.OrderDetailEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ProductEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.UserEntity;
import com.example.udpm14sellcomputerpartsbackend.repository.OrderDetailRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.OrderRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.ProductRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.UserRepository;
import com.example.udpm14sellcomputerpartsbackend.service.OrderDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository, ModelMapper modelMapper, UserRepository userRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderDetailDto> getAll() {
        List<OrderDetailEntity> list = orderDetailRepository.findAll();

        return list.stream().map(orderDetailEntity -> modelMapper
                .map(orderDetailEntity, OrderDetailDto.class)).collect(Collectors.toList());
    }

    @Override
    public Page<OrderDetailDto> getAllAndPage(Integer pageSize, Integer pageNumber) {
        Page<OrderDetailEntity> list = orderDetailRepository.getAllAndPage(PageRequest.of(pageSize, pageNumber));

        return list.map(orderDetailEntity -> modelMapper.map(orderDetailEntity, OrderDetailDto.class));
    }

    @Override
    public OrderDetailDto getById(Long id) {
        OrderDetailEntity getById = orderDetailRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Order detail id not found: "+id));
        return modelMapper.map(getById, OrderDetailDto.class);
    }

    @Override
    public Page<OrderDetailDto> getByOrder(Long id, Integer pageSize, Integer pageNumber) {
        Page<OrderDetailEntity> list = orderDetailRepository.findAllByOrderId(id, PageRequest.of(pageSize, pageNumber));

        return list.map(orderDetailEntity -> modelMapper.map(orderDetailEntity, OrderDetailDto.class));
    }

    @Override
    public OrderDetailDto create(OrderDetailDto orderDetailDto) {
        check(orderDetailDto.getUserId(), orderDetailDto.getOrderId());
        ProductEntity findProduct = productRepository.findById(orderDetailDto.getProductId())
                .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Product id not found: "+orderDetailDto.getOrderId()));

        OrderDetailEntity orderDetailEntity = modelMapper.map(orderDetailDto, OrderDetailEntity.class);
        orderDetailEntity.setPrice(findProduct.getPrice());

        return modelMapper.map(orderDetailRepository.save(orderDetailEntity),OrderDetailDto.class);
    }

    @Override
    public OrderDetailDto update(Long id, OrderDetailDto orderDetailDto) {
        return null;
    }

    @Override
    public void delete(Long id) {
        OrderDetailEntity find = orderDetailRepository.findById(id).orElse(null);
        orderDetailRepository.deleteById(find.getId());
    }
    public void check(Long user, Long order){
        UserEntity findUser = userRepository.findById(user)
                .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "User id not found: "+user));
        OrderEntity findOrder = orderRepository.findById(order)
                .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Order id not found: "+order));
    }
}
