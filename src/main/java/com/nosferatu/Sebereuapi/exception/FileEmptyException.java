package com.nosferatu.Sebereuapi.exception;

import com.nosferatu.Sebereuapi.exception.advice.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FileEmptyException extends DomainException {

    private static final long serialVersionUID = 1L;

    public FileEmptyException() {
        super("The file cannot be empty");
    }
}
