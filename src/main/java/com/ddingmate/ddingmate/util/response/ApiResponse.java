package com.ddingmate.ddingmate.util.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

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

    public static <T> ApiResponse<T> error(HttpStatus httpStatus, T data) {
        return makeResponse(httpStatus.toString(), data);
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
