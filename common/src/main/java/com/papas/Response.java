package com.papas;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.papas.Constant.OK;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Data
public class Response<T> {
    private int code;
    private String status;
    private T data;

    public Response(int code, String status) {
        this.code = code;
        this.status = status;
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(200, OK, data);
    }

    public static Response<Void> justOk() {
        return new Response<>(200, OK);
    }
}
