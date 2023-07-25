package com.nosferatu.Sebereuapi.domain.dto.response;

import com.nosferatu.Sebereuapi.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
public class SignInResponseDTO {

    private UUID userId;
    private String email;

    public static SignInResponseDTO fromUserEntity(User user){
        return SignInResponseDTO.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .build();
    }
}
