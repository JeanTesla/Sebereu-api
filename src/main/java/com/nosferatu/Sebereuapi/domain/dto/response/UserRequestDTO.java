package com.nosferatu.Sebereuapi.domain.dto.response;

import com.nosferatu.Sebereuapi.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class UserRequestDTO {

    private String userName;

    private String firstName;

    private String lastName;

    private String email;

    private String city;

    private String address;

    private String state;

    private String postalCode;

    private String aboutMe;

    private String quickMessage;

    public User toUserEntity(User user) {
        user.setUserName(this.getUserName());
        user.setFirstName(this.getFirstName());
        user.setLastName(this.getLastName());
        user.setEmail(this.getEmail());
        user.setAddress(this.getAddress());
        user.setState(this.getState());
        user.setCity(this.getCity());
        user.setPostalCode(this.getPostalCode());
        user.setAboutMe(this.getAboutMe());
        user.setQuickMessage(this.getQuickMessage());
        return user;
    }
}
