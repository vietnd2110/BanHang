package com.example.udpm14sellcomputerpartsbackend.contants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {
    INACTIVE(0),
    ACTIVE(1),
    DELETED(2);

    private final int value;
}
