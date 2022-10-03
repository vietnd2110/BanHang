package com.example.udpm14sellcomputerpartsbackend.contants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {
    CUSTOMER(0),
    STAFF(1),
    ADMIN(2);

    private final int value;

}
