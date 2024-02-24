package com.ddingmate.ddingmate.util.exception;

import com.ddingmate.ddingmate.util.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse handleNoSuchElementException(final NoSuchElementException e) {
        ErrorResponse errorResponse = ErrorResponse.builder(e, HttpStatus.NOT_FOUND, e.getMessage()).build();

        return ApiResponse.error(HttpStatus.NOT_FOUND, errorResponse.getBody());
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleIOException(final IOException e) {
        ErrorResponse errorResponse = ErrorResponse.builder(e, HttpStatus.BAD_REQUEST, e.getMessage()).build();
        return ApiResponse.error(HttpStatus.BAD_REQUEST, errorResponse.getBody());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleIllegalArgumentException(final IllegalArgumentException e) {
        ErrorResponse errorResponse = ErrorResponse.builder(e, HttpStatus.BAD_REQUEST, e.getMessage()).build();
        return ApiResponse.error(HttpStatus.BAD_REQUEST, errorResponse.getBody());
    }

    @ExceptionHandler(SecurityException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResponse handleSecurityException(final SecurityException e) {
        ErrorResponse errorResponse = ErrorResponse.builder(e, HttpStatus.UNAUTHORIZED, e.getMessage()).build();
        return ApiResponse.error(HttpStatus.UNAUTHORIZED, errorResponse.getBody());
    }
}
