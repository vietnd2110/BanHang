package com.example.udpm14sellcomputerpartsbackend.contants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductStatusEnum {
    ACTIVE(1),
    INACTIVE(0),
    DELETED(2);

    private final int value;

}
