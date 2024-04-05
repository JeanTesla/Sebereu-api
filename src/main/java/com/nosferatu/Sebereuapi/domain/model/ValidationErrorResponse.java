package com.nosferatu.Sebereuapi.domain.model;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class ValidationErrorResponse {

    private int status;

    private String message;

    private List<ValidationErrorFields> fields;

    public ValidationErrorResponse(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
        this.fields = null;
    }

    public ValidationErrorResponse(HttpStatus status, String message, List<ValidationErrorFields> Fields) {
        this.status = status.value();
        this.message = message;
        this.fields = Fields;
    }
}