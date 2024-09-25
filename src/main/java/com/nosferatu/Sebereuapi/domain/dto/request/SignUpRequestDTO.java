package com.nosferatu.Sebereuapi.domain.dto.request;

import com.nosferatu.Sebereuapi.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignUpRequestDTO {

    private String userName;

    private String email;

    private String firstName;

    private String lastName;

    private String password;

    private String passwordRepeat;

    public User toEntity(){
        return User.builder()
                .userName(this.userName)
                .email(this.email)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .password(this.password)
                .build();
    }
}