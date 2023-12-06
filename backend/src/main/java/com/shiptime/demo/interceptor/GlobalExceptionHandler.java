package com.shiptime.demo.interceptor;

import com.google.gson.Gson;
import com.shiptime.demo.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLOutput;

@ControllerAdvice
public class GlobalExceptionHandler {

    Gson gson = new Gson();
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleApplicationException(Exception ex) {
        System.out.println(ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gson.toJson(ex.getMessage()));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleApplicationException(BusinessException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gson.toJson(ex.getMessage()));
    }
}
