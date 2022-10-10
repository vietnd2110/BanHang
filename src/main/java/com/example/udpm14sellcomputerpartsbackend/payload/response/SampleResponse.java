package com.example.udpm14sellcomputerpartsbackend.payload.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SampleResponse {
    private Boolean success;
    private String message;
    private Object data;

}
