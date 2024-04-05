package com.nosferatu.Sebereuapi.domain.dto.response;

import com.nosferatu.Sebereuapi.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
public class SignUpResponseDTO {

    private UUID userId;

    private String email;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public static SignUpResponseDTO fromUserEntity(User user) {
        return SignUpResponseDTO.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
