package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.contants.OrderStatusEnum;
import com.example.udpm14sellcomputerpartsbackend.exception.BadRequestException;
import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.entity.CartEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.OrderDetailEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.OrderEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ProductEntity;
import com.example.udpm14sellcomputerpartsbackend.repository.CartRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.OrderDetailRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.OrderRepository;
import com.example.udpm14sellcomputerpartsbackend.security.CustomerDetailService;
import com.example.udpm14sellcomputerpartsbackend.service.MailService;
import com.example.udpm14sellcomputerpartsbackend.service.OrderService;
import com.example.udpm14sellcomputerpartsbackend.service.ProductService;
import com.example.udpm14sellcomputerpartsbackend.ultil.CurrentUserUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.math.BigDecimal;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    private final CartServiceImpl cartService;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private OrderDetailRepository orderDetailRepository;
    private final MailService mailService;

    public OrderServiceImpl(
            CartServiceImpl cartService,
            CartRepository cartRepository,
            OrderRepository orderRepository,
            ProductService productService,
            OrderDetailRepository orderDetailRepository,
            MailService mailService
    ) {
        this.cartService = cartService;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.orderDetailRepository = orderDetailRepository;
        this.mailService = mailService;
    }

    @Override
    public OrderEntity orderConfirmed(Long orderId) {
        CustomerDetailService uDetailService = CurrentUserUtils.getCurrentUserUtils();
        Optional<OrderEntity> findByOrderId = orderRepository.findById(orderId);
        if (findByOrderId.isPresent()) {
            OrderEntity order = findByOrderId.get();
            order.setStatus(OrderStatusEnum.DAXACNHAN);
            order.setStaffId(uDetailService.getId());
            return orderRepository.save(order);
        }
        return findByOrderId
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Order id not found: " + orderId));

    }

    @Override
    public OrderEntity waitForPay(Long orderId) {
        Optional<OrderEntity> findByOrderId = orderRepository.findById(orderId);
        if (findByOrderId.isPresent()) {
            OrderEntity order = findByOrderId.get();
            order.setStatus(OrderStatusEnum.CHOTHANHTOAN);
            return orderRepository.save(order);
        }
        return findByOrderId
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Order id not found: " + orderId));
    }

    @Override
    public OrderEntity beingShipped(Long orderId) {
        Optional<OrderEntity> findByOrderId = orderRepository.findById(orderId);
        if (findByOrderId.isPresent()) {
            OrderEntity order = findByOrderId.get();
            order.setStatus(OrderStatusEnum.DANGVANCHUYEN);
            return orderRepository.save(order);
        }
        return findByOrderId
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Order id not found: " + orderId));
    }

    @Override
    public OrderEntity cancelled(Long orderId, String reason) {
        CustomerDetailService uDetailService = CurrentUserUtils.getCurrentUserUtils();
        Optional<OrderEntity> findByOrderId = orderRepository.findById(orderId);
        if (findByOrderId.isPresent()) {
            OrderEntity order = findByOrderId.get();

            if (order.getStatus() == OrderStatusEnum.DANGVANCHUYEN) {
                throw new BadRequestException("Đơn hàng của bạn đang vận chuyển không thể hủy đơn hàng");
            } else if (order.getStatus() == OrderStatusEnum.DAGIAO) {
                throw new BadRequestException("Đơn hàng đã được giao không thể hủy đơn hàng");
            } else {
                order.setStatus(OrderStatusEnum.DAHUY);
                order.setReason(reason);

                Collection<OrderDetailEntity> listOrderDetail = orderDetailRepository.findAllByUserId(uDetailService.getId());
                System.out.println(listOrderDetail + "abc");
                for (OrderDetailEntity listOrder : listOrderDetail) {

                    ProductEntity productEntity = productService.getOne(listOrder.getProductId());

                    int quantityOrder = listOrder.getQuantity();
                    System.out.println(quantityOrder + "quanti");
                    int quantityProduct = productEntity.getQuantity();

                    int updateQuantity = quantityProduct + quantityOrder;
                    productService.updateQuantity(productEntity.getId(), updateQuantity);
                    System.out.println(updateQuantity + "updat");
                    System.out.println("vao day khong");
                }

                return orderRepository.save(order);
            }
        }
        return findByOrderId
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Order id not found: " + orderId));
    }

    @Override
    public OrderEntity checkoutOrder(OrderEntity order) throws MessagingException {
        CustomerDetailService uDetailService = CurrentUserUtils.getCurrentUserUtils();

        BigDecimal total_amount = cartRepository.sumPrice(uDetailService.getId());
        Integer quantity_cart = cartRepository.sumQuantity(uDetailService.getId());
        System.out.println(total_amount + "total_order");
        if (uDetailService.getId() != null) {
            order.setAccountId(uDetailService.getId());
            order.setShipping(40000f);
            order.setStatus(OrderStatusEnum.CHOXACNHAN);
            order.setDiscount(0.0f);
            order.setQuantity(quantity_cart);
            order.setGrandTotal(total_amount);
            System.out.println(uDetailService.getId() + "account id");
            order.setAccountId(uDetailService.getId());
            orderRepository.save(order);
        }

        Collection<CartEntity> listCartItem = cartService.getAllByUser(uDetailService.getId());
        for (CartEntity cartItem : listCartItem) {
            OrderDetailEntity orderDetail = new OrderDetailEntity();

            orderDetail.setPrice(cartItem.getPrice());
            orderDetail.setQuantity(cartItem.getQuantity());

            Double price = Double.parseDouble(orderDetail.getPrice().toString());
            BigDecimal total = BigDecimal.valueOf(price * orderDetail.getQuantity());
            orderDetail.setTotal(total);

            orderDetail.setImage(cartItem.getImage());
            orderDetail.setProductId(cartItem.getProductId());
            orderDetail.setOrderId(order.getId());
            orderDetail.setUserId(uDetailService.getId());

            orderDetailRepository.save(orderDetail);
            ProductEntity productEntity = productService.getOne(cartItem.getProductId());

            int quantityOrderDetail = orderDetail.getQuantity();
            int quantityProduct = productEntity.getQuantity();

            int updateQuantity = quantityProduct - quantityOrderDetail;
            productService.updateQuantity(productEntity.getId(), updateQuantity);
            // xoa cart
            cartRepository.deleteById(cartItem.getId());
        }
        sendEmailOrder(uDetailService.getEmail(), order);

        return order;
    }

    public void sendEmailOrder(String email, OrderEntity order) throws MessagingException {
        Map<String, Object> props = new HashMap<>();
        props.put("fullname", order.getFullname());
        props.put("phone", order.getPhone());
        props.put("address", order.getAddress());
        props.put("description", order.getDescription());
        props.put("createDate", order.getCreateDate());
        props.put("discount", order.getDiscount());
        props.put("total", order.getGrandTotal());
        props.put("shipping", order.getShipping());
        mailService.sendMail(props, email, "sendEmailOrder", "ĐƠN HÀNG CỦA BẠN ĐÃ ĐẶT");
    }


    @Override
    public List<OrderEntity> listStatusWaitForConfirmation(){
        return orderRepository.findAllByStatusEquals(OrderStatusEnum.CHOXACNHAN);
    }

    @Override
    public List<OrderEntity> listStatusOrderConfirmed(){
        return orderRepository.findAllByStatusEquals(OrderStatusEnum.DAXACNHAN);
    }

    @Override
    public List<OrderEntity> listStatusWaitForPay(){
        return orderRepository.findAllByStatusEquals(OrderStatusEnum.CHOTHANHTOAN);
    }

    @Override
    public List<OrderEntity> listStatusBeingShipped() {
        return orderRepository.findAllByStatusEquals(OrderStatusEnum.DANGVANCHUYEN);
    }

    @Override
    public List<OrderEntity> listStatusDelivered() {
        return orderRepository.findAllByStatusEquals(OrderStatusEnum.DAGIAO);
    }

    @Override
    public List<OrderEntity> listStatusCancelled() {
        return orderRepository.findAllByStatusEquals(OrderStatusEnum.DAHUY);
    }










}
