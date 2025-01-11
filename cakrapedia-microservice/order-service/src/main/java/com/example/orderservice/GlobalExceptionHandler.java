package com.example.orderservice;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<BaseResponse<?>> handle(ResponseStatusException ex){
        return ResponseEntity.status(ex.getStatusCode()).body(new BaseResponse<>(false, ex.getReason(), null));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<BaseResponse<?>> handle(ConstraintViolationException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponse<>(false, ex.getMessage(), null));
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<BaseResponse<?>> handleRuntimeException(FeignException ex) {
        log.error(ex.getLocalizedMessage());
        return ResponseEntity.status(ex.status()).body(new BaseResponse<>(false, ex.getLocalizedMessage(), null));
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BaseResponse<?>> handleRuntimeException(RuntimeException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseResponse<>(false, "Internal Server Error", null));
    }
}

