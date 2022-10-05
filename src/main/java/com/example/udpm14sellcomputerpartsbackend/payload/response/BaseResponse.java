package com.example.udpm14sellcomputerpartsbackend.payload.response;

import com.example.udpm14sellcomputerpartsbackend.ultil.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    private String type;
    private String code;
    private String message;
    private Object data;

    public BaseResponse(String type) {
        this.type = type;
    }

    public BaseResponse(String code, String message) {
        this.message = message;
        this.code = code;
    }

    public BaseResponse withData(Object data) {
        this.data = data;
        return this;
    }

    public static BaseResponse success(String code) {
        return new BaseResponse(Constants.RESPONSE_TYPE.SUCCESS, code);
    }

    public static BaseResponse success(String code, String message) {
        return new BaseResponse(Constants.RESPONSE_TYPE.SUCCESS, code, message, null);
    }

    public static BaseResponse error(String data) {
        return new BaseResponse(Constants.RESPONSE_TYPE.ERROR, data);
    }

    public static BaseResponse warning(String code) {
        return new BaseResponse(Constants.RESPONSE_TYPE.WARNING, code);
    }

    public static BaseResponse invalidPermission() {
        return new BaseResponse(Constants.RESPONSE_TYPE.ERROR, "invalidPermission");
    }

    public static BaseResponse confirm(String code, String callback, Object data) {
        return new BaseResponse(Constants.RESPONSE_TYPE.CONFIRM, code, callback, data);
    }

    public static BaseResponse confirm(String code, String callback) {
        return new BaseResponse(Constants.RESPONSE_TYPE.CONFIRM, code, callback, null);
    }

    public static BaseResponse custom(String code, String message) {
        return new BaseResponse(Constants.RESPONSE_TYPE.ERROR, code, message, null);
    }

}
