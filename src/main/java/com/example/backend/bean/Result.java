package com.example.backend.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    @JsonProperty(index = 1)
    private int code;

    @JsonProperty(index = 2)
    private String msg;

    @JsonProperty(index = 3)
    private T data;

    public static <T> Result<T> success(int code, String msg, T data) {
        return new Result<>(code, msg, data);
    }

    public static <T> Result<T> success(T data) {
        return success(200, "操作成功", data);
    }

    public static <T> Result<T> success(String msg, T data) {
        return success(200, msg, data);
    }

    public static <T> Result<T> fail(int code, String msg, T data) {
        return new Result<>(code, msg, data);
    }

    public static <T> Result<T> fail(String msg) {
        return fail(400, msg, null);
    }

    public static <T> Result<T> fail(String msg, T data) {
        return fail(400, msg, data);
    }
}
