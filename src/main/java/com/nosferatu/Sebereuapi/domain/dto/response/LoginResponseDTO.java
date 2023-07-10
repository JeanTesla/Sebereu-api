package com.nosferatu.Sebereuapi.domain.dto.response;

import com.nosferatu.Sebereuapi.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class LoginResponseDTO {
    private String email;

    public static LoginResponseDTO fromUserEntity(User user){
        return LoginResponseDTO.builder()
                .email(user.getEmail())
                .build();
    }
}
