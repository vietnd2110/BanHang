package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.contants.OrderStatus;
import com.example.udpm14sellcomputerpartsbackend.contants.OrderStatusEnum;
import com.example.udpm14sellcomputerpartsbackend.contants.PaymentStatus;
import com.example.udpm14sellcomputerpartsbackend.exception.BadRequestException;
import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.CreateOrderReq;
import com.example.udpm14sellcomputerpartsbackend.model.entity.*;
import com.example.udpm14sellcomputerpartsbackend.payload.request.CreateDeliveryOrder;
import com.example.udpm14sellcomputerpartsbackend.payload.request.CreateOrderAtTheCounter;
import com.example.udpm14sellcomputerpartsbackend.payload.response.OrderDetailResponse;
import com.example.udpm14sellcomputerpartsbackend.repository.CartRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.OrderDetailRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.OrderRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.ProductRepository;
import com.example.udpm14sellcomputerpartsbackend.security.CustomerDetailService;
import com.example.udpm14sellcomputerpartsbackend.service.*;
import com.example.udpm14sellcomputerpartsbackend.ultil.CurrentUserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.time.LocalTime;
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
    private final OrderDetailService orderDetailService;
    private final ProductRepository productRepository;

    public OrderServiceImpl(CartServiceImpl cartService, CartRepository cartRepository, OrderRepository orderRepository, ProductService productService, OrderDetailRepository orderDetailRepository, MailService mailService, PromotionService promotionService, OrderDetailService orderDetailService, ProductRepository productRepository) {
        this.cartService = cartService;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.orderDetailRepository = orderDetailRepository;
        this.mailService = mailService;
        this.promotionService = promotionService;
        this.orderDetailService = orderDetailService;
        this.productRepository = productRepository;
    }


    @Override
    public List<OrderEntity> getAll() {
        return orderRepository.findAllOrderById();
    }

    @Override
    public OrderEntity orderConfirmed(Long orderId) {
        CustomerDetailService uDetailService = CurrentUserUtils.getCurrentUserUtils();
        Optional<OrderEntity> findByOrderId = orderRepository.findById(orderId);
        if (findByOrderId.isPresent()) {
            OrderEntity order = findByOrderId.get();
            if (order.getStatus() == OrderStatusEnum.CHOXACNHAN) {
                order.setStatus(OrderStatusEnum.DANGXULY);
                order.setStaffId(uDetailService.getId());
                order.setGrandTotal((long) (order.getGrandTotal() + order.getShipping() - order.getDiscount()));
                return orderRepository.save(order);
            } else {
                throw new BadRequestException("Trạng thái phải là đang chờ xác nhận mới xác nhận được");
            }
        }
        return findByOrderId.orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Order id not found: " + orderId));
    }


    @Override
    public OrderEntity beingShipped(Long orderId) {
        Optional<OrderEntity> findByOrderId = orderRepository.findById(orderId);
        if (findByOrderId.isPresent()) {
            OrderEntity order = findByOrderId.get();
            if (order.getStatus() == OrderStatusEnum.DANGXULY) {
                order.setStatus(OrderStatusEnum.DANGVANCHUYEN);
                return orderRepository.save(order);
            }
        }
        return findByOrderId.orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Order id not found: " + orderId));
    }

    //đã giao
    @Override
    public OrderEntity delivered(Long orderId) {
        Optional<OrderEntity> findByOrderId = orderRepository.findById(orderId);
        if (findByOrderId.isPresent()) {
            OrderEntity order = findByOrderId.get();
            if (order.getStatus() == OrderStatusEnum.DANGVANCHUYEN) {
                order.setStatus(OrderStatusEnum.DAGIAO);
                return orderRepository.save(order);
            }
        }
        return findByOrderId.orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Order id not found: " + orderId));
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
                order.setPaymentStatus(PaymentStatus.HUY);

                Collection<OrderDetailEntity> listOrderDetail = orderDetailRepository.findAllByUserIdAndOrderId(uDetailService.getId(),orderId);
                System.out.println(listOrderDetail + "abc");
                for (OrderDetailEntity listOrder : listOrderDetail) {

                    ProductEntity productEntity = productService.getOne(listOrder.getProductId());

                    int quantityOrder = listOrder.getQuantity();
                    int quantityProduct = productEntity.getQuantity();

                    int updateQuantity = quantityProduct + quantityOrder;
                    productService.updateQuantity(productEntity.getId(), updateQuantity);
                }
                return orderRepository.save(order);
            }
        }
        return findByOrderId.orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Order id not found: " + orderId));
    }


    @Override
    public OrderEntity checkoutOrder(CreateOrderReq req) throws MessagingException {
        OrderEntity order = new OrderEntity();
        CustomerDetailService uDetailService = CurrentUserUtils.getCurrentUserUtils();

        Long total_amount = cartRepository.sumPrice(uDetailService.getId());

        System.out.println(total_amount + "total_order");
        if (uDetailService.getId() != null) {

            order.setUserId(uDetailService.getId());
            order.setShipping(req.getShipping());
            order.setStatus(OrderStatusEnum.CHOXACNHAN);

            // Check promotion
            PromotionEntity promotion = promotionService.checkPromotion(req.getCouponCode());
            if (promotion != null) {
                long promotionPrice = promotionService.calculatePromotionPrice(total_amount, promotion);
                order.setDiscount((double) promotionPrice);
            } else {
                order.setDiscount(0.0);
            }
//            Integer quantity_cart = cartRepository.sumQuantity(uDetailService.getId());

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

            LocalDate date = LocalDate.now();
            String year = String.valueOf(date.getYear());
            String month = String.valueOf(date.getMonth().getValue());
            String dayOfYear = String.valueOf(date.getDayOfMonth());
            String dateT = year + month + dayOfYear;


            LocalTime time = LocalTime.now();
            String second = String.valueOf(time.getSecond());
            String hours = String.valueOf(time.getHour());
            String minute = String.valueOf(time.getMinute() + 1);
            String timeD = hours + minute + second;


            order.setMahd("HD" + dateT + timeD);


            order.setFullname(req.getFullname());
            order.setPhone(req.getPhone());
            order.setDescription(req.getDescription());
            order.setPaymentId(req.getPaymentId());
            order.setAddress(req.getAddress());
            order.setPaymentStatus(PaymentStatus.CHUATHANHTOAN);
            order.setPaymentId(req.getPaymentId());
            order.setOrderStatus(OrderStatus.DONGIAO);
            order.setProvince(req.getProvince());
            order.setDistrict(req.getDistrict());
            order.setWard(req.getWard());
//            order.setQuantity(quantity_cart);

            System.out.println(total_amount + "total");
            System.out.println(order.getShipping() + "ship");
            System.out.println(order.getDiscount() + "discount");

//            order.setGrandTotal((long) (total_amount + order.getShipping() - order.getDiscount()));
            order.setGrandTotal((long) (total_amount - order.getDiscount()));

            System.out.println(uDetailService.getId() + "account id");
            order.setUserId(uDetailService.getId());
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

            if (cartItem.getQuantity() > productEntity.getQuantity())
                throw new BadRequestException("Số lượng đặt hàng vươt quá số lượng trong kho");
            else if (productEntity.getQuantity() == 0)
                throw new BadRequestException("Sản phẩm này đã hết hàng");


            int quantityOrderDetail = orderDetail.getQuantity();
            int quantityProduct = productEntity.getQuantity();

            int updateQuantity = quantityProduct - quantityOrderDetail;
            productService.updateQuantity(productEntity.getId(), updateQuantity);
            // xoa cart
            cartRepository.deleteById(cartItem.getId());
        }
//        sendEmailOrder(uDetailService.getEmail(), order);

        return order;
    }


    private void sendEmailOrder(String email, OrderEntity order) throws MessagingException {
        Map<String, Object> props = new HashMap<>();
        props.put("fullname", order.getFullname());
        props.put("phone", order.getPhone());
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
        return orderRepository.findAllByStatusEqualsAndOrderStatusOrderByIdDesc(status, OrderStatus.DONGIAO);
    }

    // danh sách hóa đơn theo status và người dùng
    @Override
    public List<OrderEntity> listOrderStatusAndUserId(OrderStatusEnum status) {
        CustomerDetailService detailService = CurrentUserUtils.getCurrentUserUtils();
        return orderRepository.findAllByStatusEqualsAndUserId(status, detailService.getId());
    }

    @Override
    public OrderStatusEnum[] status() {
        OrderStatusEnum[] status = OrderStatusEnum.values();
        System.out.println(status);
        return status;
    }

    // mua lại hàng
    @Override
    public void reOrder(Long orderId) {
        CustomerDetailService userUtils = CurrentUserUtils.getCurrentUserUtils();

        List<OrderDetailEntity> findByIdOrder = orderDetailRepository.findAllByOrderIdAndUserId(orderId, userUtils.getId());

        for (OrderDetailEntity order : findByIdOrder) {
//          cartRepository.deleteById(order.getProductId());
            CartEntity cart = new CartEntity();
            BeanUtils.copyProperties(order, cart);

            ProductEntity productEntity = productRepository.findById(cart.getProductId())
                    .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "product id not found: " + cart.getProductId()));
            if (productEntity.getQuantity() <= 0) {
                throw new BadRequestException("Sản phẩm này đã hết hàng, vui lòng chờ cửa hàng nhập thêm");
            }
            cartRepository.save(cart);
        }
    }

    @Override
    public long countOrderStatus(int status) {
        CustomerDetailService detailService = CurrentUserUtils.getCurrentUserUtils();
        return orderRepository.countOrderStatus(status, detailService.getId());
    }

    @Override
    public OrderEntity findByIdOrder(Long id) {
        Optional<OrderEntity> optional = orderRepository.findById(id);
        if (!optional.isPresent()) {
            throw new BadRequestException("Order id not found");
        }
        return optional.get();
    }

    @Override
    public List<OrderEntity> listStatusPayment() {
        return orderRepository.findAllByPaymentStatusEquals(PaymentStatus.DANGSULY);
    }


    @Override
    public List<OrderEntity> listStatusPaymentPaid() {
        return orderRepository.findAllByPaymentStatusEquals(PaymentStatus.DATHANHTOAN);
    }

    @Override
    public List<OrderEntity> searchOrder(String name) {
        List<OrderEntity> findByPhone = orderRepository.findByPhone(name);
        List<OrderEntity> list;
        for (OrderEntity order : findByPhone) {
            if (order.getPhone().equals(name)) {
                list = orderRepository.searchAllByOrder(name, PaymentStatus.DATHANHTOAN);
            } else {
                throw new BadRequestException("Không tìm thầy");
            }
            return list;
        }
        throw new BadRequestException("khong tim thay");
    }


    // đếm số lượng đơn hàng chờ
    private long countOrderStatus() {
        return orderRepository.countOrderStatus();
    }

    // tạo đơn hàng chờ
    @Override
    public OrderEntity createOrder() {
        CustomerDetailService detailService = CurrentUserUtils.getCurrentUserUtils();

        if (this.countOrderStatus() > 9) {
            throw new BadRequestException("Chỉ được tạo tối đa 10 đơn hàng chờ");
        }

        OrderEntity order = new OrderEntity();

        LocalDate date = LocalDate.now();
        String year = String.valueOf(date.getYear());
        String month = String.valueOf(date.getMonth().getValue());
        String dayOfYear = String.valueOf(date.getDayOfMonth());
        String dateT = year + month + dayOfYear;


        LocalTime time = LocalTime.now();
        String second = String.valueOf(time.getSecond());
        String hours = String.valueOf(time.getHour());
        String minute = String.valueOf(time.getMinute() + 1);
        String timeD = hours + minute + second;


        order.setMahd("HD" + dateT + timeD);


        order.setNameStaff(detailService.getFullname());
        order.setStaffId(detailService.getId());
        order.setShipping(0);
        order.setPaymentStatus(PaymentStatus.DANGSULY);
        order.setStatus(OrderStatusEnum.DANGXULY);
        order.setOrderStatus(OrderStatus.DONCHO);

        return orderRepository.save(order);
    }

    // tao đơn hàng bán lẻ
    @Override
    public OrderEntity retailOrders(Long idOrder) {
        Optional<OrderEntity> findById = orderRepository.findById(idOrder);

        if (findById.isPresent()) {
            OrderEntity order = findById.get();
            order.setFullname("Khách hàng lẻ");
            order.setDescription("Khách hàng mua lẻ không muốn để lại thông tin");

            order.setOrderStatus(OrderStatus.DONLE);
            return orderRepository.save(order);
        }
        throw new BadRequestException("Id order not found");
    }

    // tạo đơn hàng tại quầy
    @Override
    public OrderEntity createAnOrderAtTheCounter(CreateOrderAtTheCounter req) {
        CustomerDetailService detailService = CurrentUserUtils.getCurrentUserUtils();

        OrderEntity order = new OrderEntity();

        order.setFullname(req.getFullname());
        order.setProvince(req.getProvince());
        order.setDistrict(req.getDistrict());
        order.setWard(req.getWard());
        order.setAddress(req.getAddress());
        order.setPhone(req.getPhone());
        order.setDescription(req.getDescription());

        order.setNameStaff(detailService.getFullname());
        order.setStaffId(detailService.getId());
        order.setShipping(0);
        order.setPaymentStatus(PaymentStatus.CHUATHANHTOAN);
        order.setStatus(OrderStatusEnum.DANGXULY);
        order.setOrderStatus(OrderStatus.TAIQUAY);

        return orderRepository.save(order);
    }

    // tạo đơn hàng Giao
    @Override
    public OrderEntity createDeliveryOrder(CreateDeliveryOrder req) {
        CustomerDetailService detailService = CurrentUserUtils.getCurrentUserUtils();

        OrderEntity order = new OrderEntity();

        order.setFullname(req.getFullname());
        order.setProvince(req.getProvince());
        order.setDistrict(req.getDistrict());
        order.setWard(req.getWard());
        order.setAddress(req.getAddress());
        order.setPhone(req.getPhone());
        order.setDescription(req.getDescription());
        order.setShipping(req.getShipping());

        order.setNameStaff(detailService.getFullname());
        order.setStaffId(detailService.getId());
        order.setPaymentStatus(PaymentStatus.CHUATHANHTOAN);
        order.setStatus(OrderStatusEnum.DANGXULY);
        order.setOrderStatus(OrderStatus.DONGIAO);

        return orderRepository.save(order);
    }

    // cập nhật lại hóa đơn giao
    @Override
    public OrderEntity updateDeliveryOrder(Long orderId, CreateDeliveryOrder req) {
        Optional<OrderEntity> findById = orderRepository.findById(orderId);

        if (findById.isPresent()) {
            OrderEntity order = findById.get();

            order.setFullname(req.getFullname());
            order.setProvince(req.getProvince());
            order.setDistrict(req.getDistrict());
            order.setWard(req.getWard());
            order.setAddress(req.getAddress());
            order.setPhone(req.getPhone());
            order.setDescription(req.getDescription());
            order.setShipping(req.getShipping());
            order.setOrderStatus(OrderStatus.DONGIAO);

            return orderRepository.save(order);
        } else {
            throw new BadRequestException("Order Id not found");
        }

    }

    // cập nhật lại hóa đơn tại quầy
    @Override
    public OrderEntity updateAtTheCounterOrder(Long orderId, CreateOrderAtTheCounter req) {
        Optional<OrderEntity> findById = orderRepository.findById(orderId);
        if (findById.isPresent()) {
            OrderEntity order = findById.get();

            order.setFullname(req.getFullname());
            order.setProvince(req.getProvince());
            order.setDistrict(req.getDistrict());
            order.setWard(req.getWard());
            order.setPhone(req.getPhone());
            order.setDescription(req.getDescription());
            order.setOrderStatus(OrderStatus.TAIQUAY);
            order.setShipping(0);

            if (req.getAddress() == null) {
                order.setAddress(order.getAddress());
                System.out.println("vao day 1");
            } else {
                order.setAddress(req.getAddress());
                System.out.println("vao day 2");
            }
            System.out.println("vao day 3");

            return orderRepository.save(order);
        } else {
            throw new BadRequestException("Order Id not found");
        }

    }


    // đặt hàng tại quầy
    public OrderEntity checkoutAtTheCounters(Long orderId) {
        CustomerDetailService uDetailService = CurrentUserUtils.getCurrentUserUtils();
        // lấy tông tiền đơn hàng
        long total_amount = cartRepository.sumPrice(uDetailService.getId());

        System.out.println(total_amount + " total");
        System.out.println(uDetailService.getId() + "id sfafff");

        Optional<OrderEntity> findByIdOrder = orderRepository.findById(orderId);
        OrderEntity order = findByIdOrder.get();
        if (findByIdOrder.isPresent()) {
            order.setGrandTotal(total_amount + order.getShipping());
            order.setPaymentStatus(PaymentStatus.DATHANHTOAN);
            orderRepository.save(order);

            Collection<CartEntity> listCartItems = cartService.getAllByUser(uDetailService.getId());
            for (CartEntity cartItem : listCartItems) {
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
        } else {
            throw new BadRequestException("Order id not found");
        }
        return order;
    }


    // đặt hàng tại quầy
    @Override
    public OrderEntity checkoutAtTheCounter(Long orderId) {

        long total_amount = orderDetailRepository.sumPrice(orderId);

        Optional<OrderEntity> findByIdOrder = orderRepository.findById(orderId);
        OrderEntity order = findByIdOrder.get();
        if (findByIdOrder.isPresent()) {

            order.setGrandTotal(total_amount + order.getShipping());
            order.setPaymentStatus(PaymentStatus.DATHANHTOAN);

            if (order.getOrderStatus() == OrderStatus.DONGIAO) {
                order.setStatus(OrderStatusEnum.DANGXULY);
                System.out.println("vao order 1");
            } else if (order.getOrderStatus() == OrderStatus.TAIQUAY) {
                order.setStatus(OrderStatusEnum.DAHOANTHANH);
                System.out.println("vao order 2");
            } else {
                System.out.println("vao order 3");
                order.setStatus(OrderStatusEnum.DAHOANTHANH);
            }
            System.out.println("vao order 4");

            Collection<OrderDetailEntity> listOrderDetail = orderDetailService.getAllOrderId(orderId);
            for (OrderDetailEntity orderDetail : listOrderDetail) {

                ProductEntity productEntity = productService.getOne(orderDetail.getProductId());

                if (orderDetail.getQuantity() > productEntity.getQuantity())
                    throw new BadRequestException("Số lượng đặt hàng vươt quá số lượng trong kho");
                else if (productEntity.getQuantity() == 0)
                    throw new BadRequestException("Sản phẩm này đã hết hàng");

                int quantityOrderDetail = orderDetail.getQuantity();
                int quantityProduct = productEntity.getQuantity();

                int updateQuantity = quantityProduct - quantityOrderDetail;
                productService.updateQuantity(productEntity.getId(), updateQuantity);
            }

            return orderRepository.save(order);
        }
        throw new BadRequestException("Lỗi khi thành toán");
    }

    @Override
    public OrderDetailResponse sumTotalOrderDetail(Long idOrder) {

        OrderEntity findByOrder = orderRepository.findById(idOrder).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Order id not found: " + idOrder));

        OrderDetailResponse response = new OrderDetailResponse();
        response.setTotalAmount(orderDetailRepository.sumPrice(idOrder));
        response.setShipping(findByOrder.getShipping());
        return response;
    }


    // lọc theo loại đơn
    @Override
    public List<OrderEntity> filterStatusOrder(OrderStatus status) {
        return orderRepository.findAllByOrderStatusEquals(status);
    }

    @Override
    public OrderEntity findByMahd(String mahd) {

        Optional<OrderEntity> findByMahd = orderRepository.findByMahd(mahd);

        if (!findByMahd.isPresent())
            throw new BadRequestException("Mã Hóa Đơn Không tìm thấy");

        return findByMahd.get();
    }


//    public OrderEntity returnsTheGoods(Long idOrder, Reimburse reimburse){
//        CustomerDetailService detailService = CurrentUserUtils.getCurrentUserUtils();
//        Optional<OrderEntity> findByIdOrder = orderRepository.findById(idOrder);
//
//        if(findByIdOrder.isPresent()){
//           OrderEntity orderOld = findByIdOrder.get();
//
//           List<OrderDetailEntity> orderDetailEntityList = orderDetailRepository.findAllByUserId(detailService.getId());
//
//           if(orderOld.getStatus() != OrderStatusEnum.DAGIAO)
//               throw new BadRequestException("Bạn không thể đổi trả đơn hàng");
//           else{
//
//               orderOld.setDescription(reimburse.getDescription());
//               orderRepository.save(orderOld);
//
//               for (OrderDetailEntity order : orderDetailEntityList ) {
//
//               }
//           }
//        }
//    }

}
