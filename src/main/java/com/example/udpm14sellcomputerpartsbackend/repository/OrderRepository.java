package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.contants.OrderStatusEnum;
import com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe.StatisticalDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe.ThongKeDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe.ThongKeThangVaNamDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByStatusEquals(OrderStatusEnum status);

    List<OrderEntity> findAllByStatusEqualsAndAccountId(OrderStatusEnum status, Long accountId);

    List<OrderEntity> findAllByAccountId(Long accountId);

    @Query(nativeQuery = true,value = "SELECT count(*) AS 'Số lượng' FROM `orders` WHERE orders.status = ?1 and orders.account_id = ?2 ")
    long countOrderStatus(int status,Long accountId);

    @Query(" SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe.StatisticalDto(o.id, YEAR(o.createDate), count (o.id), sum(o.grandTotal) )" +
            "FROM OrderEntity o " +
            "GROUP BY year (o.createDate)")
    List<StatisticalDto> listHoaDonCacNam();

    @Query(" SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe.ThongKeThangVaNamDto(o.id, YEAR(o.createDate), MONTH(o.createDate), count (o.id), sum(o.grandTotal) )" +
            "FROM OrderEntity o " +
            "GROUP BY year (o.createDate), Month (o.createDate)")
    List<ThongKeThangVaNamDto> listHoaDonThangVaNam();


    @Query(" SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe.StatisticalDto(o.id, YEAR(o.createDate), count (o.id), sum(o.grandTotal) )" +
            "FROM OrderEntity o " +
            "WHERE year(o.createDate)=:year " +
            "GROUP BY year (o.createDate)")
    List<StatisticalDto> listHoaDonTheoNam(Integer year);

    @Query(" SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe.ThongKeDto(o.id, count (o.id), sum(o.grandTotal) )" +
            "FROM OrderEntity o " +
            "WHERE year(o.createDate)=?1 and month(o.createDate)=?2 " +
            "GROUP BY year (o.createDate)")
    List<ThongKeDto> listHoaDonTheoThang(Integer year, Integer month);

    @Query(" SELECT new com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe.ThongKeDto(o.id, count (o.id), sum(o.grandTotal) )" +
            "FROM OrderEntity o " +
            "WHERE year(o.createDate)=?1 and month(o.createDate)=?2 and day(o.createDate)=?3")
    List<ThongKeDto> listHoaDonTheoNgayHienTai(Integer year, Integer mont, Integer day);
}