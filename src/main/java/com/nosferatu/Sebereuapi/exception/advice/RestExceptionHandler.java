package com.nosferatu.Sebereuapi.exception.advice;

import com.nosferatu.Sebereuapi.domain.model.ValidationErrorFields;
import com.nosferatu.Sebereuapi.domain.model.ValidationErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public final ResponseEntity<Object> handleDomainException(DomainException e) {

        HttpStatus status = e.getClass().getAnnotation(ResponseStatus.class).value(); // Get status of @ResponseStatus(HttpStatus)

        ErrorResponse errorResponse = new ErrorResponse(status, e.getMessage());

        return new ResponseEntity<>(errorResponse, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<ValidationErrorFields> listFieldsResponse = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            ValidationErrorFields fields = new ValidationErrorFields();
            fields.setField(error.getField());
            fields.setMessage(error.getDefaultMessage());
            listFieldsResponse.add(fields);
        });

        ValidationErrorResponse errorResponse =
                new ValidationErrorResponse(
                        status,
                        "Invalid fields",
                        listFieldsResponse);

        return new ResponseEntity<>(errorResponse, status);
    }
}
