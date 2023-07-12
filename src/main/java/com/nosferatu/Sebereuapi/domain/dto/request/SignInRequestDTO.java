package com.nosferatu.Sebereuapi.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignInRequestDTO {

    private String email;
    private String password;
}
