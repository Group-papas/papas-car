package com.papas;

public enum Errors {

    NOT_FOUND_EXCEPTION(404),
    BAD_REQUEST(400),
    INTERNAL_SERVER_ERROR(500);
    ;

    private final int code;

    Errors(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
