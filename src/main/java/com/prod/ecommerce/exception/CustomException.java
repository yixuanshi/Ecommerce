package com.prod.ecommerce.exception;

//import lombok.Data;
// @Data
// Do not do that, 不希望程序员随意创建异常
public class CustomException extends RuntimeException {
    private int code;
    private String message;

    public CustomException(CustomExceptionType customExceptionEnum) {
        this.code = customExceptionEnum.getCode();
        this.message = customExceptionEnum.getDesc();
    }

    public CustomException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}

