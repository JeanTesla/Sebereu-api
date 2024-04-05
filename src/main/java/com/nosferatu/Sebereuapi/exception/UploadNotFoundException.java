package com.nosferatu.Sebereuapi.exception;

import com.nosferatu.Sebereuapi.exception.advice.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UploadNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public UploadNotFoundException() {
        super("Upload not found");
    }
}
