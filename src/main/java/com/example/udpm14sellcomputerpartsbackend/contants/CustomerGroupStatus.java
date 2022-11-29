package com.example.udpm14sellcomputerpartsbackend.contants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomerGroupStatus {

    KHONGGIOIHANKHACHANG(0),
    NHOMKHACHANG(1),
    TUYCHONKHACHANG(2);

    private final int value;
}
