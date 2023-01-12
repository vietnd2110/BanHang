package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.contants.OrderStatus;
import com.example.udpm14sellcomputerpartsbackend.contants.OrderStatusEnum;
import com.example.udpm14sellcomputerpartsbackend.contants.StatusEnum;
import com.example.udpm14sellcomputerpartsbackend.model.dto.CreateOrderReq;
import com.example.udpm14sellcomputerpartsbackend.model.entity.OrderEntity;
import com.example.udpm14sellcomputerpartsbackend.payload.request.CreateDeliveryOrder;
import com.example.udpm14sellcomputerpartsbackend.payload.request.CreateOrderAtTheCounter;
import com.example.udpm14sellcomputerpartsbackend.payload.request.OrderConfirm;
import com.example.udpm14sellcomputerpartsbackend.payload.response.OrderDetailResponse;
import org.springframework.data.domain.Page;

import javax.mail.MessagingException;
import java.util.List;

public interface OrderService {
    List<OrderEntity> getAll();

    OrderEntity orderConfirmed(Long orderId);

    OrderEntity beingShipped(Long orderId);

    //đã giao
    OrderEntity delivered(Long orderId);

    OrderEntity cancelled(Long orderId, String reason);

    // đặt hàng tại quầy
    OrderEntity checkoutAtTheCounter(Long orderId);

    OrderEntity checkoutOrder(CreateOrderReq req) throws MessagingException;

    List<OrderEntity> listStatus(OrderStatusEnum status);

    // danh sách hóa đơn theo status và người dùng
    List<OrderEntity> listOrderStatusAndUserId(OrderStatusEnum status);

    OrderStatusEnum[] status();

    // mua lại hàng
    void reOrder(Long orderId);

    long countOrderStatus(int status);

    OrderEntity findByIdOrder(Long id);

    List<OrderEntity> listStatusPayment();

    List<OrderEntity> listStatusPaymentPaid();

    List<OrderEntity> searchOrder(String name);

    OrderEntity createOrder();

    // tao đơn hàng bán lẻ
    OrderEntity retailOrders(Long idOrder);

    // tạo đơn hàng tại quầy
    OrderEntity createAnOrderAtTheCounter(CreateOrderAtTheCounter req);

    // tạo đơn hàng Giao
    OrderEntity createDeliveryOrder(CreateDeliveryOrder req);

    // cập nhật lại hóa đơn giao
    OrderEntity updateDeliveryOrder(Long orderId, CreateDeliveryOrder req);

    OrderEntity updateAtTheCounterOrder(Long orderId, CreateOrderAtTheCounter req);

    OrderDetailResponse sumTotalOrderDetail(Long idOrder);

    // lọc theo loại đơn
    List<OrderEntity> filterStatusOrder(OrderStatus status);

    OrderEntity findByMahd(String mahd);
}
