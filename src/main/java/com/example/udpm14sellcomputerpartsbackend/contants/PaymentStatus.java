package com.example.udpm14sellcomputerpartsbackend.contants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentStatus {
    CHUATHANHTOAN(0),
    DATHANHTOAN(1),
    HUY(3);

    private final int value;
}
