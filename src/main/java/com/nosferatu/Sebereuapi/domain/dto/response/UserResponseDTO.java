package com.nosferatu.Sebereuapi.domain.dto.response;

import com.nosferatu.Sebereuapi.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class UserResponseDTO {

    private String name;

    private String last_name;

    private String email;

    private String city;

    private String country;

    private String postalCode;

    private String aboutMe;

    public static UserResponseDTO fromUserEntity(User user){
        return UserResponseDTO.builder()
                .name(user.getName())
                .last_name(user.getLast_name())
                .email(user.getEmail())
                .city(user.getCity())
                .country(user.getCountry())
                .postalCode(user.getPostalCode())
                .aboutMe(user.getAboutMe())
                .build();
    }
}
