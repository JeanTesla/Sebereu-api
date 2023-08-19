package com.nosferatu.Sebereuapi.domain.dto.request;

import com.nosferatu.Sebereuapi.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignUpRequestDTO {

    private String email;

    private String name;

    private String password;

    private String passwordRepeat;

    public User toEntity(){
        return User.builder()
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .build();
    }
}