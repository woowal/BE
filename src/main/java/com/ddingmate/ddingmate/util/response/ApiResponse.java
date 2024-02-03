package com.ddingmate.ddingmate.util.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApiResponse<T> {

    private LocalDateTime localDateTime;
    private String message;
    private T data; // == body

    public static <T> ApiResponse<T> create(T data) {
        return makeResponse("created", data);
    }

    public static <T> ApiResponse<T> create(String message, T data) {
        return makeResponse(message, data);
    }

    public static <T> ApiResponse<T> ok() {
        return makeResponse("OK", null);
    }

    public static <T> ApiResponse<T> ok(T data) {
        return makeResponse("OK", data);
    }

    private static <T> ApiResponse<T> makeResponse(String message, T data) {
        return (ApiResponse<T>)
                ApiResponse.builder()
                        .localDateTime(LocalDateTime.now())
                        .message(message)
                        .data(data)
                        .build();
    }

}
