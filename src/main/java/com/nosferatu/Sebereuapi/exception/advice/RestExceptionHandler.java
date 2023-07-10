package com.nosferatu.Sebereuapi.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public final ResponseEntity<Object> handleDomainException(DomainException e) {

        HttpStatus status = e.getClass().getAnnotation(ResponseStatus.class).value(); // Get status of @ResponseStatus(HttpStatus)

        ErrorResponse errorResponse = new ErrorResponse(status, e.getMessage());

        return new ResponseEntity<>(errorResponse, status);
    }
}
