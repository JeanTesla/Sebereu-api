package com.nosferatu.Sebereuapi.domain.dto.response;

import com.nosferatu.Sebereuapi.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class SignInResponseDTO {
    private String email;

    public static SignInResponseDTO fromUserEntity(User user){
        return SignInResponseDTO.builder()
                .email(user.getEmail())
                .build();
    }
}
