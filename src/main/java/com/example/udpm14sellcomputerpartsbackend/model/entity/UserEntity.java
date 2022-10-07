package com.example.udpm14sellcomputerpartsbackend.model.entity;

import com.example.udpm14sellcomputerpartsbackend.contants.RoleEnum;
import com.example.udpm14sellcomputerpartsbackend.contants.UserStatusEnum;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "username" , unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "image")
    private String image;

    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDateTime createDate;

    private UserStatusEnum status;

    @Column(name = "address")
    private String address;

    private RoleEnum role;


}