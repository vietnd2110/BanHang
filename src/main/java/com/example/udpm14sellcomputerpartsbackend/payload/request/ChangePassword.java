package com.example.udpm14sellcomputerpartsbackend.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangePassword {
    @NotBlank
    private String username;
    @NotBlank
    private String passOld;
    @NotBlank
    private String passNew;
    @NotBlank
    private String passConfirm;
}
