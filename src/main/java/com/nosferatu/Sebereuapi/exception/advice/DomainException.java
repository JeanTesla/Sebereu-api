package com.nosferatu.Sebereuapi.exception.advice;

import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String message;

    public DomainException(String message) {
        super();
        this.message = message;
    }

}

