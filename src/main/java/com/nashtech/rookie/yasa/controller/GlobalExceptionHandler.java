package com.nashtech.rookie.yasa.controller;

import com.nashtech.rookie.yasa.dto.response.ErrorResponse;
import com.nashtech.rookie.yasa.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler({
                NotFoundException.class,
                ProductNotFoundException.class,
                RatingNotFoundException.class,
                UserNotFoundException.class,
                CartNotFoundException.class,
                CategoryNotFoundException.class,
                OrderNotFoundException.class,
        })
        @ResponseBody
        ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            RuntimeException exception, WebRequest request) {
        var error = ErrorResponse.builder().code(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
