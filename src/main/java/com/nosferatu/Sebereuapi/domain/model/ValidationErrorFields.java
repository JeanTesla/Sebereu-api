package com.nosferatu.Sebereuapi.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationErrorFields {

    private String field;

    private String message;
}