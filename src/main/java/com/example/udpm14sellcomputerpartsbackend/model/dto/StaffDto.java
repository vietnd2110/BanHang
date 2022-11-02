package com.example.udpm14sellcomputerpartsbackend.model.dto;

import com.example.udpm14sellcomputerpartsbackend.contants.RoleEnum;
import com.example.udpm14sellcomputerpartsbackend.contants.StatusEnum;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class StaffDto {

    private Long id;

    @NotBlank(message = "Fullname is not empty")
    @Size(min = 1,max = 50,message = "Fullname length from 1 to 50")
    private String fullname;

    @NotNull(message = "Username is not empty")
    @Size(min = 1,max = 20,message = "Username length from 1 to 20")
    private String username;

    @NotNull(message = "Password is not empty")
    @Size(min = 1,max = 50,message = "Password length from 1 to 50")
    private String password;

    @NotBlank(message = "Email is not empty")
    @Email(message = "Email invalidate")
    private String email;

    @NotBlank(message = "Phone is not empty")
    @Size(max = 10,message = "Phone max 10")
    private String phone;

    private StatusEnum status;

    @NotBlank(message = "Address is not empty")
    @Size(min = 1,max = 150,message = "Address length from 1 to 150")
    private String address;

    private RoleEnum role;
}
