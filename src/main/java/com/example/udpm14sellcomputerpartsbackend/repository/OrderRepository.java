package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.contants.OrderStatus;
import com.example.udpm14sellcomputerpartsbackend.contants.OrderStatusEnum;
import com.example.udpm14sellcomputerpartsbackend.contants.PaymentStatus;
import com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe.*;
import com.example.udpm14sellcomputerpartsbackend.model.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {


    @Query(value = "SELECT * FROM `orders`ORDER BY orders.id DESC",nativeQuery = true)
    List<OrderEntity> findAllOrderById();


    @Query("SELECT ord FROM OrderEntity ord WHERE ord.phone like %?1% and ord.paymentStatus = ?2")
    List<OrderEntity> searchAllByOrder(String name,PaymentStatus status);

    List<OrderEntity> findByPhone(String phone);

    List<OrderEntity> findAllByStatusEqualsAndOrderStatusOrderByIdDesc(OrderStatusEnum status, OrderStatus oStatus);

    List<OrderEntity> findAllByPaymentStatusEquals(PaymentStatus status);

    List<OrderEntity> findAllByOrderStatusEquals(OrderStatus status);

    List<OrderEntity> findAllByStatusEqualsAndUserId(OrderStatusEnum status, Long userId);

    List<OrderEntity> findAllByUserId(Long userId);

    @Query(nativeQuery = true,value = "SELECT count(*) AS 'Số lượng' FROM `orders` WHERE orders.status = ?1 and orders.user_id = ?2 ")
    long countOrderStatus(int status,Long userId);

    @Query(" SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe.StatisticalDto(o.id, YEAR(o.createDate), count (o.id), sum(o.grandTotal) )" +
            "FROM OrderEntity o where o.status=3 " +
            "GROUP BY year (o.createDate)")
    List<StatisticalDto> listHoaDonCacNam();

    @Query(" SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe.ThongKeThangVaNamDto(o.id, YEAR(o.createDate), MONTH(o.createDate), count (o.id), sum(o.grandTotal) )" +
            "FROM OrderEntity o where o.status=3 " +
            "GROUP BY year (o.createDate), Month (o.createDate)")
    List<ThongKeThangVaNamDto> listHoaDonThangVaNam();

    @Query(" SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe.ThongKeDto(count (o.id), sum(o.grandTotal) )" +
            "FROM OrderEntity o where o.status=3")
    ThongKeDto thongKeTuTruocToiNay();

    @Query("select count(o.id) from OrderEntity o where o.status=?1")
    Integer thongKeTrangThaiDonHang(OrderStatusEnum status);

    @Query(" SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe.ThongKeDto(count (o.id), sum(o.grandTotal) )" +
            "FROM OrderEntity o " +
            "WHERE year(o.createDate)=?1 and month(o.createDate)=?2 and day(o.createDate)=?3 and o.status=3")
    List<ThongKeDto> listHoaDonTheoNgayHienTai(Integer year, Integer mont, Integer day);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe.ThongKeCacNgayTheoThangVaNam(day(o.createDate),month(o.createDate),year(o.createDate), count(o.id), sum(o.grandTotal) )" +
            "FROM OrderEntity o " +
            "WHERE year(o.createDate)=?1 and month(o.createDate)=?2 and o.status=3 " +
            "GROUP BY day(o.createDate),month(o.createDate),year(o.createDate)")
    List<ThongKeCacNgayTheoThangVaNam> listHoaDonTungNgayTheoThangVaNam(Integer year, Integer month);

    @Query(" SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe.ThongKeThangVaNamDto(o.id, YEAR(o.createDate), MONTH(o.createDate), count (o.id), sum(o.grandTotal))" +
            "FROM OrderEntity o " +
            "WHERE year(o.createDate)=?1 and o.status=3 " +
            "GROUP BY YEAR(o.createDate), MONTH(o.createDate)")
    List<ThongKeThangVaNamDto> listHoaDonTungThangTheoNam(Integer year);

    @Query("SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe.ProductBanChayDto(od.productId,od.name,p.code, sum(od.quantity), sum(od.total)) " +
            "FROM OrderDetailEntity od " +
            "INNER JOIN ProductEntity p ON p.id = od.productId " +
            "INNER join OrderEntity o ON o.id = od.orderId " +
            "WHERE o.status = 3 " +
            "GROUP BY od.name " +
            "ORDER BY SUM(od.quantity) desc ")
    List<ProductBanChayDto> topSanPhamBanChay(Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM `orders` WHERE orders.payment_status = 0",nativeQuery = true)
    long countOrderStatus();

    Optional<OrderEntity> findByMahd(String mahd);

}
