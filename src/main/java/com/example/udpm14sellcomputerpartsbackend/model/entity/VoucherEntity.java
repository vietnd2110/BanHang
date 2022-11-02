package com.example.udpm14sellcomputerpartsbackend.model.entity;

import com.example.udpm14sellcomputerpartsbackend.contants.StatusEnum;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vouchers")
@Data
public class VoucherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "value")
    private String value;

    @Column(name = "detail")
    private String detail;

    private StatusEnum status;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;


}
