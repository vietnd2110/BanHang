package com.example.udpm14sellcomputerpartsbackend.model.entity;

import com.example.udpm14sellcomputerpartsbackend.contants.OrderStatusEnum;
import com.example.udpm14sellcomputerpartsbackend.contants.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer shipping;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "province")
    private String province;

    @Column(name = "district")
    private String district;

    @Column(name = "ward")
    private String ward;

    @Column(name = "phone")
    private String phone;

    private String email;
//    @Column(name = "quantity")
//    private Integer quantity;
    @Column(name = "description")
    private String description;

    @Column(name = "reason")
    private String reason;

    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDateTime createDate;

    @Column(name = "update_date")
    @UpdateTimestamp
    private LocalDateTime updateDate;

    private OrderStatusEnum status;

    private PaymentStatus paymentStatus;

    @Column(name = "name_staff")
    private String nameStaff;

    @Column(name = "grand_total")
    private Long grandTotal;

    @Column(name = "discount")
    private Double discount;

    @JsonProperty("account_id")
    private Long accountId;

    @JsonProperty("staff_id")
    private Long staffId;

    @JsonProperty("payment_id")
    private Long paymentId;


}
