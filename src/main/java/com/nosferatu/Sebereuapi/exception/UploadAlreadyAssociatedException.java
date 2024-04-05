package com.nosferatu.Sebereuapi.exception;

import com.nosferatu.Sebereuapi.exception.advice.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UploadAlreadyAssociatedException extends DomainException {

    private static final long serialVersionUID = 1L;

    public UploadAlreadyAssociatedException() {
        super("This upload is already associated with another contribution");
    }
}
