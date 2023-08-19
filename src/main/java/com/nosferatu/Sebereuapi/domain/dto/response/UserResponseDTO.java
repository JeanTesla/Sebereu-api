package com.nosferatu.Sebereuapi.domain.dto.response;

import com.nosferatu.Sebereuapi.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
public class UserResponseDTO {

    private String name;
    private String email;

    public static UserResponseDTO fromUserEntity(User user){
        return UserResponseDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
