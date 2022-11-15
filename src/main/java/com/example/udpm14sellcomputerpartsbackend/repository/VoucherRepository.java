package com.example.udpm14sellcomputerpartsbackend.repository;

import com.example.udpm14sellcomputerpartsbackend.model.entity.VoucherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherRepository extends JpaRepository<VoucherEntity, Long> {

    @Query("SELECT ord FROM OrderDetailEntity ord " +
            "INNER JOIN ProductEntity pro on ord.productId = pro.id " +
            "INNER JOIN VoucherEntity vouc on pro.voucherId = vouc.id " +
            "WHERE vouc.code = :code")
    List<?> getCodeVoucher(String code);


}