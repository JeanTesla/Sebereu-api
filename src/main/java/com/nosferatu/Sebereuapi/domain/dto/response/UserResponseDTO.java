package com.nosferatu.Sebereuapi.domain.dto.response;

import com.nosferatu.Sebereuapi.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@AllArgsConstructor
@Builder
@Getter
public class UserResponseDTO {

    private String userName;

    private String firstName;

    private String lastName;

    private String email;

    private String city;

    private String address;

    private String country;

    private String state;

    private String postalCode;

    private String aboutMe;

    private String quickMessage;

    @CreationTimestamp
    private Timestamp createdAt;

    public static UserResponseDTO fromUserEntity(User user){
        return UserResponseDTO.builder()
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .address(user.getAddress())
                .state(user.getState())
                .city(user.getCity())
                .country(user.getCountry())
                .postalCode(user.getPostalCode())
                .aboutMe(user.getAboutMe())
                .quickMessage(user.getQuickMessage())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
