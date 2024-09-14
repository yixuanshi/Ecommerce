package com.prod.ecommerce.exception;

public enum CustomExceptionType {
    USER_INPUT_ERROR(400,"User input error"),
    SYSTEM_ERROR(500,"System error"),
    OTHER_ERROR(999,"Unknown error");

    private String desc;
    private int code;

    CustomExceptionType(int code, String desc) {
        this.desc = desc;
        this.code = code;
    }

    public String getDesc() {
        return this.desc;
    }

    public int getCode() {
        return this.code;
    }
}
