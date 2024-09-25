package com.nosferatu.Sebereuapi.exception;

import com.nosferatu.Sebereuapi.exception.advice.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserNameAlreadyExistsException extends DomainException {

    private static final long serialVersionUID = 1L;

    public UserNameAlreadyExistsException() {
        super("This username is already associated with another user");
    }
}
