package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.contants.OrderStatusEnum;
import com.example.udpm14sellcomputerpartsbackend.exception.BadRequestException;
import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.CreateOrderReq;
import com.example.udpm14sellcomputerpartsbackend.model.entity.*;
import com.example.udpm14sellcomputerpartsbackend.payload.request.OrderConfirm;
import com.example.udpm14sellcomputerpartsbackend.repository.CartRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.OrderDetailRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.OrderRepository;
import com.example.udpm14sellcomputerpartsbackend.security.CustomerDetailService;
import com.example.udpm14sellcomputerpartsbackend.service.MailService;
import com.example.udpm14sellcomputerpartsbackend.service.OrderService;
import com.example.udpm14sellcomputerpartsbackend.service.ProductService;
import com.example.udpm14sellcomputerpartsbackend.service.PromotionService;
import com.example.udpm14sellcomputerpartsbackend.ultil.CurrentUserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    private final CartServiceImpl cartService;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private OrderDetailRepository orderDetailRepository;
    private final MailService mailService;
    private final PromotionService promotionService;

    public OrderServiceImpl(
            CartServiceImpl cartService,
            CartRepository cartRepository,
            OrderRepository orderRepository,
            ProductService productService,
            OrderDetailRepository orderDetailRepository,
            MailService mailService,
            PromotionService promotionService
    ) {
        this.cartService = cartService;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.orderDetailRepository = orderDetailRepository;
        this.mailService = mailService;
        this.promotionService = promotionService;
    }


    @Override
    public List<OrderEntity> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public OrderEntity orderConfirmed(Long orderId, OrderConfirm orderConfirm) {
        CustomerDetailService uDetailService = CurrentUserUtils.getCurrentUserUtils();
        Optional<OrderEntity> findByOrderId = orderRepository.findById(orderId);
        if (findByOrderId.isPresent()) {
            OrderEntity order = findByOrderId.get();
            if(order.getStatus() == OrderStatusEnum.CHOXACNHAN){
                order.setStatus(OrderStatusEnum.DANGXULY);
                order.setStaffId(uDetailService.getId());
                order.setNameStaff(uDetailService.getFullname());
                order.setShipping(orderConfirm.getShipping());
                return orderRepository.save(order);
            }
        }
        return findByOrderId
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Order id not found: " + orderId));
    }


    @Override
    public OrderEntity beingShipped(Long orderId) {
        Optional<OrderEntity> findByOrderId = orderRepository.findById(orderId);
        if (findByOrderId.isPresent()) {
            OrderEntity order = findByOrderId.get();
            if(order.getStatus() == OrderStatusEnum.DANGXULY){
                order.setStatus(OrderStatusEnum.DANGVANCHUYEN);
                return orderRepository.save(order);
            }
        }
        return findByOrderId
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Order id not found: " + orderId));
    }

    //đã giao
    @Override
    public OrderEntity delivered(Long orderId){
        Optional<OrderEntity> findByOrderId = orderRepository.findById(orderId);
        if (findByOrderId.isPresent()) {
            OrderEntity order = findByOrderId.get();
            if(order.getStatus() == OrderStatusEnum.DANGVANCHUYEN){
                order.setStatus(OrderStatusEnum.DAGIAO);
                return orderRepository.save(order);
            }
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

//            if (order.getAccountId() != uDetailService.getId()) {
//                throw new BadRequestException("Bạn không phải là chủ đơn hàng");
//            }

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
    public OrderEntity checkoutOrder(CreateOrderReq req) throws MessagingException {
        OrderEntity order = new OrderEntity();
        CustomerDetailService uDetailService = CurrentUserUtils.getCurrentUserUtils();

        long total_amount = cartRepository.sumPrice(uDetailService.getId());
        Integer quantity_cart = cartRepository.sumQuantity(uDetailService.getId());
        System.out.println(total_amount + "total_order");
        if (uDetailService.getId() != null) {

            order.setAccountId(uDetailService.getId());
            order.setShipping(40000f);
            order.setStatus(OrderStatusEnum.CHOXACNHAN);
            order.setEmail(uDetailService.getEmail());


            // Check promotion
            PromotionEntity promotion = promotionService.checkPromotion(req.getCouponCode());
            if (promotion != null) {
                long promotionPrice = promotionService.calculatePromotionPrice(total_amount, promotion);
                order.setDiscount((double) promotionPrice);
            } else {
                order.setDiscount(0.0);
            }

//            // Check promotion
//            if (req.getCouponCode() != "") {
//                PromotionEntity promotion = promotionService.checkPromotion(req.getCouponCode());
//                System.out.println(promotion + "ad");
//                if (promotion == null) {
//                    throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Mã khuyến mãi không tồn tại hoặc chưa được kích hoạt");
//                }
//                Timestamp now = new Timestamp(System.currentTimeMillis());
//                if (promotion.getExpiredAt().before(now)) {
//                    throw new BadRequestException("Mã khuyến mãi hết hạn");
//                }
//                long promotionPrice = promotionService.calculatePromotionPrice(total_amount, promotion);
//                order.setDiscount((double) promotionPrice);
//            }


            order.setFullname(req.getFullname());
            order.setAddress(req.getAddress());
            order.setPhone(req.getPhone());
            order.setDescription(req.getDescription());
            order.setPaymentId(req.getPaymentId());
            order.setQuantity(quantity_cart);

            System.out.println(total_amount + "total");
            System.out.println(order.getShipping() + "ship");
            System.out.println(order.getDiscount() + "discount");

            order.setGrandTotal((long) (total_amount + order.getShipping() - order.getDiscount()));

            System.out.println(uDetailService.getId() + "account id");
            order.setAccountId(uDetailService.getId());
            orderRepository.save(order);
        }

        Collection<CartEntity> listCartItem = cartService.getAllByUser(uDetailService.getId());
        for (CartEntity cartItem : listCartItem) {
            OrderDetailEntity orderDetail = new OrderDetailEntity();

            orderDetail.setPrice(cartItem.getPrice());
            orderDetail.setQuantity(cartItem.getQuantity());

            long total = orderDetail.getPrice() * orderDetail.getQuantity();
            orderDetail.setTotal(total);

            orderDetail.setName(cartItem.getName());
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

    private void sendEmailOrder(String email, OrderEntity order) throws MessagingException {
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

    // danh sách hóa đơn theo status
    @Override
    public List<OrderEntity> listStatus(OrderStatusEnum status) {
        return orderRepository.findAllByStatusEquals(status);
    }


    // danh sách hóa đơn theo status và người dùng
    @Override
    public List<OrderEntity> listOrderStatusAndUserId(OrderStatusEnum status){
        CustomerDetailService detailService = CurrentUserUtils.getCurrentUserUtils();
        return orderRepository.findAllByStatusEqualsAndAccountId(status,detailService.getId());
    }

    @Override
    public OrderStatusEnum[] status() {
        OrderStatusEnum[] status = OrderStatusEnum.values();
        System.out.println(status);
        return status;
    }


    // mua lại hàng
    @Override
    public void reOrder(Long orderId){
        CustomerDetailService userUtils = CurrentUserUtils.getCurrentUserUtils();

        List<OrderDetailEntity> findByIdOrder = orderDetailRepository.findAllByOrderIdAndUserId(orderId,userUtils.getId());

        for (OrderDetailEntity order : findByIdOrder) {
            CartEntity cart = new CartEntity();
            BeanUtils.copyProperties(order,cart);

            cartRepository.save(cart);
        }
    }


    @Override
    public long countOrderStatus(int status){
        CustomerDetailService detailService = CurrentUserUtils.getCurrentUserUtils();
        return orderRepository.countOrderStatus(status,detailService.getId());
    }


    @Override
    public OrderEntity findByIdOrder(Long id){
        Optional<OrderEntity> optional = orderRepository.findById(id);
        if(!optional.isPresent()){
            throw new BadRequestException("Order id not found");
        }
        return optional.get();
    }


}
