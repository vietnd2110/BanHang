package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.exception.BadRequestException;
import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.OrderDetailDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.OrderDetailEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ProductEntity;
import com.example.udpm14sellcomputerpartsbackend.payload.response.orderDetail.TotalPriceResponse;
import com.example.udpm14sellcomputerpartsbackend.repository.OrderDetailRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.ProductRepository;
import com.example.udpm14sellcomputerpartsbackend.security.CustomerDetailService;
import com.example.udpm14sellcomputerpartsbackend.service.OrderDetailService;
import com.example.udpm14sellcomputerpartsbackend.ultil.CurrentUserUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    private final ProductRepository productRepository;
    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository, ProductRepository productRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.productRepository = productRepository;
    }


    @Override
    public List<OrderDetailDto> getAll() {
        return orderDetailRepository.getAll();
    }

    @Override
    public Page<OrderDetailDto> getAllAndPage(Integer pageSize, Integer pageNumber) {
        Page<OrderDetailDto> list = orderDetailRepository.getAllAndPage(PageRequest.of(pageSize, pageNumber));
        return list;
    }

    @Override
    public OrderDetailEntity getById(Long id) {
        return orderDetailRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Order detail id not found: " + id));
    }

    @Override
    public Page<OrderDetailDto> getByOrder(Long id, Integer pageSize, Integer pageNumber) {
        Page<OrderDetailDto> list = orderDetailRepository.findAllByOrderId(id, PageRequest.of(pageSize, pageNumber));
        return list;
    }

    @Override
    public Page<OrderDetailDto> getByUserLogin(Integer pageSize, Integer pageNumber) {
        CustomerDetailService user = CurrentUserUtils.getCurrentUserUtils();
        Page<OrderDetailDto> list = orderDetailRepository.findAllByUserId(user.getId(), PageRequest.of(pageSize, pageNumber));
        return list;
    }

    @Override
    public Page<OrderDetailDto> getByUser(Long userId, Integer pageSize, Integer pageNumber) {
        Page<OrderDetailDto> list = orderDetailRepository.findAllByUserId(userId, PageRequest.of(pageSize, pageNumber));
        return list;
    }

    @Override
    public List<OrderDetailEntity> getAllOrderId(Long id){
        return orderDetailRepository.findAllByOrderId(id);
    }

    @Override
    public OrderDetailEntity update(Long id, OrderDetailDto orderDetailDto) {
        OrderDetailEntity find = orderDetailRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Hóa đơn chi tiết không tồn tại"));

        OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
        orderDetailEntity.setId(find.getId());
        orderDetailEntity.setPrice(orderDetailDto.getPrice());
        orderDetailEntity.setName(orderDetailDto.getName());
        orderDetailEntity.setQuantity(orderDetailDto.getQuantity());
        orderDetailEntity.setTotal(orderDetailDto.getTotal());
        orderDetailEntity.setImage(orderDetailDto.getImage());
        orderDetailEntity.setProductId(orderDetailDto.getProductId());
        orderDetailEntity.setOrderId(orderDetailDto.getOrderId());
        orderDetailEntity.setUserId(orderDetailDto.getUserId());

        return orderDetailRepository.save(orderDetailEntity);
    }

    @Override
    public OrderDetailEntity updateQuantity(Long productId, Long orderId, Integer quantity) {
        OrderDetailEntity orderDetailEntity = orderDetailRepository.findAllByOrderIdAndProductId(orderId, productId);
        if (orderDetailEntity == null) {
            throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "người dùng chưa có sản phẩm id: " + productId + " trong order");
        }

        ProductEntity findQuantity = productRepository.findById(productId).get();
        if (findQuantity.getQuantity() < quantity) {
            throw new BadRequestException("Bạn chỉ có thể mua tối đa :" + findQuantity.getQuantity() + " của sản phẩm này");
        }
        orderDetailEntity.setQuantity(quantity);
        orderDetailEntity.setTotal(orderDetailEntity.getTotal() * orderDetailEntity.getQuantity());

        return orderDetailRepository.save(orderDetailEntity);
    }

    @Override
    public void delete(Long id) {
        OrderDetailEntity find = orderDetailRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Hóa đơn chi tiết không tồn tại"));
        orderDetailRepository.deleteById(find.getId());
    }

    @Transactional
    @Override
    public void deleteAllByOrderId(Long orderId) {
        orderDetailRepository.deleteAllByOrderId(orderId);
    }

    @Override
    public TotalPriceResponse total(Long orderId) {
        TotalPriceResponse totalPriceResponse = new TotalPriceResponse();
        totalPriceResponse.setTotalPrice(orderDetailRepository.totalPrice(orderId));

        return totalPriceResponse;
    }

}
