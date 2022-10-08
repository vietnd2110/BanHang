package com.example.udpm14sellcomputerpartsbackend.payload.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SampleResponse {
    private Boolean status;
    private String message;
    private Object data;

}
