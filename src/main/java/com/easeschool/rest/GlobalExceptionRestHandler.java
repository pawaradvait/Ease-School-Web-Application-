package com.easeschool.rest;

import com.easeschool.model.Response;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
@Order(1)
public class GlobalExceptionRestHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> exceptionHandler(Exception e) {
        Response response = new Response();
        response.setMessage(e.getMessage());
        response.setStatusCode("500");
     return ResponseEntity.status(500).body(response);
    }
}
