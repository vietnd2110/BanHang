package com.example.udpm14sellcomputerpartsbackend.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserRegister {

    @NotBlank
    private String username;

    @NotBlank
    private String fullname;

    @NotBlank
    private String email;

    @NotBlank
    private String password;


}
