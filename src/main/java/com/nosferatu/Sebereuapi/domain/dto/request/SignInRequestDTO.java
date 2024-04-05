package com.nosferatu.Sebereuapi.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class SignInRequestDTO {

    @NotBlank(message = "Name is mandatory")
    @NotNull(message = "teste")
    private String email;
    private String password;
}
